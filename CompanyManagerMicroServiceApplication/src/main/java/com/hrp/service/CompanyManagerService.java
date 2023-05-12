package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrp.dto.request.CreateCompanyManagerRequestDto;
import com.hrp.dto.request.GetShortDetailRequestDto;
import com.hrp.dto.request.CreateEmployeeRequestDto;
import com.hrp.dto.request.UpdateCompanyManagerRequestDto;
import com.hrp.dto.response.CompanyManagerFindAllResponseDto;
import com.hrp.dto.response.GetShortDetailResponseDto;
import com.hrp.exception.CompanyManagerException;
import com.hrp.exception.EErrorType;
import com.hrp.mapper.ICompanyManagerMapper;
import com.hrp.rabbitmq.model.EmailEmployeeModel;
import com.hrp.rabbitmq.model.ModelSendToCompanyManager;
import com.hrp.rabbitmq.model.ModelSendToEmployeeMs;
import com.hrp.rabbitmq.model.RegisterEmployeeModel;
import com.hrp.rabbitmq.producer.EmailProducer;
import com.hrp.rabbitmq.producer.ProducerDirectService;
import com.hrp.repository.ICompanyManagerRepository;
import com.hrp.repository.entity.CompanyManager;
import com.hrp.repository.enums.ERole;
import com.hrp.utility.CodeGenerator;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompanyManagerService extends ServiceManagerImpl<CompanyManager, Long>{
    private final ICompanyManagerRepository companyManagerRepository;
    private final ProducerDirectService producerDirectService;
    private final JwtTokenManager jwtTokenManager;

    private final EmailProducer emailProducer;
    public CompanyManagerService(ICompanyManagerRepository companyManagerRepository, ProducerDirectService producerDirectService, JwtTokenManager jwtTokenManager, EmailProducer emailProducer) {
        super(companyManagerRepository);
        this.companyManagerRepository=companyManagerRepository;
        this.producerDirectService = producerDirectService;
        this.jwtTokenManager = jwtTokenManager;
        this.emailProducer = emailProducer;
    }

    public List<CompanyManagerFindAllResponseDto> findAllManager() {
        return findAll().stream().
                map(x-> ICompanyManagerMapper.INSTANCE
                        .toCompanyManagerFindAllResponseDto(x)).
                collect(Collectors.toList());
    }

    public Boolean createCompanyManager(ModelSendToCompanyManager model) {
        System.out.println(model.toString());
        save(CompanyManager.builder()
                .address(model.getAddress())
                .identityNumber(model.getIdentityNumber())
                .company(model.getCompany())
                .dateOfBirth(model.getBirthDate())
                .name((model.getName()))
                .surname(model.getSurname())
                .middleName(model.getMiddleName())
                .job(model.getJob())
                .jobStart(model.getJobStart())
                .email(model.getEmail())
                .phone(model.getPhone())
                .avatar(toTurnStringAvatar(model.getAvatar()))
                .build());
        return true;
    }

    private String toTurnStringAvatar(MultipartFile avatar) {
        Map config = new HashMap();
        config.put("cloud_name", "doqksh0xh");
        config.put("api_key", "871216635594134");
        config.put("api_secret", "6b3zcRZyWKeuiW6qIq4XvWnhVno");
        Cloudinary cloudinary = new Cloudinary(config);
        try{
            Map<?, ?> result = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            System.out.println(url+" --------------------------");
            return url;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }


    public Boolean updateCompanyManager(UpdateCompanyManagerRequestDto dto) {
        Optional<Long> companyManagerId=jwtTokenManager.validToken(dto.getToken());
        if (companyManagerId.isEmpty()){
            throw new CompanyManagerException(EErrorType.INVALID_TOKEN);
        }
        Optional<CompanyManager> companyManager = companyManagerRepository.findOptionalById(companyManagerId.get());
        if (companyManagerId.isEmpty()){
            throw new CompanyManagerException(EErrorType.COMPANY_MANAGER_NOT_FOUND);
        }
        companyManager.get().setPassword(dto.getPassword());
        companyManager.get().setPhone(dto.getPhone());
        companyManager.get().setJobEnd(dto.getJobEnd());
        companyManager.get().setCompany(dto.getCompany());
        companyManager.get().setAddress(dto.getAddress());
        companyManager.get().setAvatar(dto.getAvatar());
        companyManager.get().setEmail(dto.getEmail());
        update(companyManager.get());
        return true;
    }

    public Boolean deleteCompanyManager(String token) {
        Optional<Long> companyManagerId=jwtTokenManager.validToken(token);
        if (companyManagerId.isEmpty()){
            throw new CompanyManagerException(EErrorType.INVALID_TOKEN);
        }

        Optional<CompanyManager> companyManager = companyManagerRepository.findOptionalById(companyManagerId.get());
        if (companyManagerId.isEmpty()){
            throw new CompanyManagerException(EErrorType.COMPANY_MANAGER_NOT_FOUND);
        }
        deleteById(companyManagerId.get());
        return true;
    }
    @Transactional
    public Boolean createEmployee(CreateEmployeeRequestDto dto) {
        if (StringUtils.isEmpty(dto.getName()) || StringUtils.isEmpty(dto.getSurname())
                || StringUtils.isEmpty(dto.getEmail()) ){
            throw new CompanyManagerException(EErrorType.USER_NOT_EMPTY);
        }
        String passGenerator = CodeGenerator.generateCode();
        String avatarUrl = toTurnStringAvatar(dto.getAvatar());

        //Emaile gönderilen
        emailProducer.sendEmployeeMail(EmailEmployeeModel.builder()
                .email(dto.getEmail())
                .activationCode(passGenerator)
                .build());
        //Auth a gönderilen
        producerDirectService.sendNewEmployeeToAuth(RegisterEmployeeModel.builder()
                .email(dto.getEmail())
                .password(passGenerator)
                .role(ERole.EMPLOYEE)
                .build());
        // EmployeeMs' ye gonderilen
        producerDirectService.sendEmployeeMS(ModelSendToEmployeeMs.builder()
                .address(dto.getAddress())
                .identityNumber(dto.getIdentityNumber())
                .company(dto.getCompany())
                .dateOfBirth(dto.getDateOfBirth())
                .placeOfBirth(dto.getPlaceOfBirth())
                .name((dto.getName()))
                .surname(dto.getSurname())
                .middleName(dto.getMiddleName())
                .job(dto.getJob())
                .jobStart(dto.getJobStart())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .avatar(avatarUrl)
                .build());
        return true;
    }

    public GetShortDetailResponseDto getShortDetail(GetShortDetailRequestDto dto) {
       // Optional<Long> id = jwtTokenManager.validToken(dto.getToken());
        Long id = 1L;

        //if(id.isEmpty()) throw new CompanyManagerException(EErrorType.TOKEN_NOT_FOUND);
        Optional<CompanyManager> companyManager  = findById(id);
        if(companyManager.isEmpty()) throw new CompanyManagerException(EErrorType.COMPANY_MANAGER_NOT_FOUND);

        return GetShortDetailResponseDto.builder()
                .name(companyManager.get().getName())
                .surname(companyManager.get().getSurname())
                .avatar(companyManager.get().getAvatar())
                .build();
    }

    public CompanyManagerFindAllResponseDto findMe(GetShortDetailRequestDto dto) {
        Long id = jwtTokenManager.validToken(dto.getToken()).get();
        //Burdaki id auth id olacak
        CompanyManager companyManager = companyManagerRepository.findById(id).get();
        return CompanyManagerFindAllResponseDto.builder()
                .name(companyManager.getName())
                .middleName(companyManager.getMiddleName())
                .surname(companyManager.getSurname())
                .dateOfBirth(companyManager.getDateOfBirth())
                .email(companyManager.getEmail())
                .avatar(companyManager.getAvatar())
                .phone(companyManager.getPhone())
                .address(companyManager.getAddress())
                .company(companyManager.getCompany())
                .job(companyManager.getJob())
                .department(companyManager.getDepartment())
                .jobStart(companyManager.getJobStart())
                .jobEnd(companyManager.getJobEnd())
                .build();
    }

    public String uploadImageCloud(MultipartFile file, String token) {
        Long id = jwtTokenManager.validToken(token).get();

        Optional<CompanyManager> admin = companyManagerRepository.findById(id);
        if (admin.isEmpty()){
            System.out.println("Kullanici bulunamadi");
        }
        Map config = new HashMap();
        config.put("cloud_name", "doqksh0xh");
        config.put("api_key", "871216635594134");
        config.put("api_secret", "6b3zcRZyWKeuiW6qIq4XvWnhVno");
        Cloudinary cloudinary = new Cloudinary(config);
        try{
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            admin.get().setAvatar(url);
            update(admin.get());
            System.out.println(url);
            return url;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
}