package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrp.dto.request.GetShortDetailRequestDto;
import com.hrp.dto.request.CreateEmployeeRequestDto;
import com.hrp.dto.request.UpdateCompanyManagerRequestDto;
import com.hrp.dto.response.CompanyManagerFindAllResponseDto;
import com.hrp.dto.response.GetShortDetailResponseDto;
import com.hrp.exception.CompanyManagerException;
import com.hrp.exception.EErrorType;
import com.hrp.mapper.ICompanyManagerMapper;
import com.hrp.rabbitmq.consumer.RegisterConsumer;
import com.hrp.rabbitmq.model.EmailEmployeeModel;
import com.hrp.rabbitmq.model.ModelSendToCompanyManager;
import com.hrp.rabbitmq.model.RegisterEmployeeModel;
import com.hrp.rabbitmq.producer.EmailProducer;
import com.hrp.rabbitmq.producer.ProducerDirectService;
import com.hrp.repository.ICompanyManagerRepository;
import com.hrp.repository.entity.CompanyManager;
import com.hrp.repository.enums.ERole;
import com.hrp.utility.CodeGenerator;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import com.hrp.utility.StaticValues;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
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

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("5 sn geçti");
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
                .authId(StaticValues.authId)
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
        producerDirectService.sendEmployeeMS(ICompanyManagerMapper.INSTANCE.toModelSendToEmployeeMs(dto));
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
}