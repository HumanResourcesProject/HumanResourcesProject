package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrp.dto.request.CreateCompanyManagerRequestDto;
import com.hrp.dto.request.GetShortDetailRequestDto;
import com.hrp.dto.request.UpdateCompanyManagerRequestDto;
import com.hrp.dto.response.CompanyManagerFindAllResponseDto;
import com.hrp.dto.response.GetShortDetailResponseDto;
import com.hrp.exception.CompanyManagerException;
import com.hrp.exception.EErrorType;
import com.hrp.mapper.ICompanyManagerMapper;
import com.hrp.repository.ICompanyManagerRepository;
import com.hrp.repository.entity.CompanyManager;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.passay.IllegalCharacterRule.ERROR_CODE;

@Service
public class CompanyManagerService extends ServiceManagerImpl<CompanyManager, Long>{
    private final ICompanyManagerRepository companyManagerRepository;
    private final JwtTokenManager jwtTokenManager;
    public CompanyManagerService(ICompanyManagerRepository companyManagerRepository, JwtTokenManager jwtTokenManager) {
        super(companyManagerRepository);
        this.companyManagerRepository=companyManagerRepository;
        this.jwtTokenManager = jwtTokenManager;
    }

    public List<CompanyManagerFindAllResponseDto> findAllManager() {
        return findAll().stream().
                map(x-> ICompanyManagerMapper.INSTANCE
                .toCompanyManagerFindAllResponseDto(x)).
                collect(Collectors.toList());
    }

    public Boolean createCompanyManager(CreateCompanyManagerRequestDto dto) {
        String password = generatePassayPassword();
        System.out.println(dto.toString());
        save(CompanyManager.builder()
                .address(dto.getAddress())
                .identityNumber(dto.getIdentityNumber())
//                .company(dto.getCompany())
                .department(dto.getDepartment())
                .dateOfBirth(dto.getDateOfBirth())
                .name((dto.getName()))
                .surname(dto.getSurname())
                .middleName(dto.getMiddleName())
                .job(dto.getJob())
                .jobStart(dto.getJobStart())
                .email(dto.getEmail())
                .phone(dto.getPhone())
                .password(password)
//                .avatar(toTurnStringAvatar(dto.getAvatar()))
                .build());
        return true;
    }

    /**
     * External Lib to generate strong random password.
     * @return
     */
    public String generatePassayPassword() {
        PasswordGenerator gen = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        CharacterData specialChars = new CharacterData() {
            public String getErrorCode() {
                return ERROR_CODE;
            }

            public String getCharacters() {
                return "!@#$%^&*()_+";
            }
        };
        CharacterRule splCharRule = new CharacterRule(specialChars);
        splCharRule.setNumberOfCharacters(2);

        String password = gen.generatePassword(10, splCharRule, lowerCaseRule,
                upperCaseRule, digitRule);
        return password;
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

    public GetShortDetailResponseDto getShortDetail(GetShortDetailRequestDto dto) {
        Optional<Long> id = jwtTokenManager.validToken(dto.getToken());
        if(id.isEmpty()) throw new CompanyManagerException(EErrorType.TOKEN_NOT_FOUND);
        Optional<CompanyManager> companyManager  = findById(id.get());
        if(companyManager.isEmpty()) throw new CompanyManagerException(EErrorType.COMPANY_MANAGER_NOT_FOUND);

        return GetShortDetailResponseDto.builder()
                .name(companyManager.get().getName())
                .surname(companyManager.get().getSurname())
                .avatar(companyManager.get().getAvatar())
                .build();
    }
}
