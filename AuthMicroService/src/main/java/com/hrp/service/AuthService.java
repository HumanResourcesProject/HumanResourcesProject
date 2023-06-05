package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrp.dto.request.*;
import com.hrp.dto.response.AuthLoginResponse;
import com.hrp.exception.AuthException;
import com.hrp.exception.EErrorType;
import com.hrp.mapper.IManuelMapper;
import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.rabbitmq.model.ModelRegisterManager;
import com.hrp.rabbitmq.producer.DirectProducer;
import com.hrp.repository.IAuthRepository;
import com.hrp.repository.entity.Auth;
import com.hrp.repository.entity.enums.ERole;
import com.hrp.utility.CodeGenerator;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Email;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AuthService extends ServiceManagerImpl<Auth,Long> {
    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final DirectProducer directProducer;
    private final IManuelMapper iManuelMapper;

    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager, DirectProducer directProducer, IManuelMapper iManuelMapper) {
        super(authRepository);
        this.authRepository=authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.directProducer = directProducer;
        this.iManuelMapper = iManuelMapper;
    }


    public AuthLoginResponse authLogin(AuthLoginDto dto){
        Optional<Auth> auth =  authRepository.findOptionalByEmailAndPassword(dto.getEmail()
                ,dto.getPassword());
        if (auth.isEmpty()) {throw new AuthException(EErrorType.USER_NOT_FOUNT);}
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        if (token.isEmpty()) {throw new AuthException(EErrorType.INVALID_TOKEN);}
        return AuthLoginResponse.builder()
                .token(token.get())
                .role(auth.get().getRole())
                .build();
    }

    public Boolean changePassword(ChangePasswordDto dto){
        Optional<Long> id= jwtTokenManager.validToken(dto.getToken());
        if (id.isEmpty()) {throw new AuthException(EErrorType.INVALID_TOKEN);}
        Optional<Auth> auth= findById(id.get());
        if (auth.isEmpty() ) { throw new AuthException(EErrorType.USER_NOT_FOUNT);}
        if (auth.get().getPassword() != dto.getOldpassword()) {throw new AuthException(EErrorType.PASSWORD_NOT_MATCH);}
        auth.get().setPassword(dto.getNewpassword());
        update(auth.get());
        return true;
    }

    public Boolean registerAdmin(RegisterAdminRequestDto dto) {
        Optional<Long> adminId = jwtTokenManager.validToken(dto.getToken());
        if (adminId.isEmpty()) {throw new AuthException(EErrorType.INVALID_TOKEN);}
        if (authRepository.findOptionalByEmail(dto.getEmail()).isPresent()) {
            throw new AuthException(EErrorType.EMAIL_ALREADY_HAVE);}
        Auth auth=Auth.builder()
                .email(dto.getEmail())
                .role(ERole.ADMIN)
                .password(CodeGenerator.generateCode())
                .build();
        save(auth);
        ModelRegisterAdmin modelRegisterAdmin=iManuelMapper.authToModelRegisterAdmin(auth,dto);
            modelRegisterAdmin.setAvatar(uploadImageCloudWithoutToken(dto.getAvatar()));
            directProducer.sendRegisterAdmin(modelRegisterAdmin);

        return true;
    }
    public Boolean registerManager(RegisterManagerRequestDto dto) {

        Optional<Long> adminId = jwtTokenManager.validToken(dto.getToken());
        if (adminId.isEmpty()) {throw new AuthException(EErrorType.INVALID_TOKEN);}
        Auth auth = Auth.builder()
                .email(dto.getEmail())
                .role(ERole.MANAGER)
                .password(CodeGenerator.generateCode())
                .build();
        save(auth);
        ModelRegisterManager modelRegisterManager=iManuelMapper.authToModelRegisterManager(auth,dto);
        modelRegisterManager.setAvatar(uploadImageCloudWithoutToken(dto.getAvatar()));
        directProducer.sendRegisterManager(modelRegisterManager);
        return true;
    }
    public Boolean registerEmployee(RegisterEmployeeRequestDto dto) {
        Optional<Long> managerId = jwtTokenManager.validToken(dto.getToken());
        if (managerId.isEmpty()) throw new AuthException(EErrorType.INVALID_TOKEN);
        Auth auth=Auth.builder()
                .email(dto.getEmail())
                .role(ERole.EMPLOYEE)
                .password(CodeGenerator.generateCode())
                .build();
        save(auth);
        ModelRegisterEmployee modelRegisterEmployee =iManuelMapper.authToModelRegisterEmployee(auth,dto,managerId.get());
        modelRegisterEmployee.setAvatar(uploadImageCloudWithoutToken(dto.getAvatar()));
        System.out.println(modelRegisterEmployee+"      hopppaaa");
        System.out.println(dto.toString()+"      hopppaaa");
        modelRegisterEmployee.setSalary(dto.getSalary());
        directProducer.sendRegisterEmployee(modelRegisterEmployee);
        return true;
    }
    public String uploadImageCloudWithoutToken(MultipartFile file) {
        Map<String,String> config = new HashMap<>();
        config.put("cloud_name", "doqksh0xh");
        config.put("api_key", "871216635594134");
        config.put("api_secret", "6b3zcRZyWKeuiW6qIq4XvWnhVno");
        Cloudinary cloudinary = new Cloudinary(config);
        try{
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            return url;
        }catch (Exception e){
            e.getMessage();
            return null;
        }
    }



    public boolean forgotPassword(AuthLoginDto dto) {
        Optional<Auth> auth= authRepository.findOptionalByEmail(dto.getEmail());
        if (auth.isEmpty()) {throw new AuthException(EErrorType.USER_NOT_FOUNT);}
        auth.get().setPassword(CodeGenerator.generateCode());
        update(auth.get());
        return true;}
}
