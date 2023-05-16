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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        System.out.println("aaaa"+dto.toString());
        Optional<Auth> auth =  authRepository.findOptionalByEmailAndPassword(dto.getEmail(),dto.getPassword());
        System.out.println("auth:xxx " +auth.toString());
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        System.out.println("auth:ccc " +auth.toString());
        return AuthLoginResponse.builder()
                .token(token.get())
                .role(auth.get().getRole())
                .build();
    }

    // find all auth
    public Iterable<Auth> findAllAuth(){
        List <Auth> auths = (List<Auth>) authRepository.findAll();
        return auths;

    }


    public Boolean changePassword(ChangePasswordDto dto){
        Long id= jwtTokenManager.validToken(dto.getToken()).get();
        Auth auth= findById(id).get();

        if (auth==null) throw new AuthException(EErrorType.INVALID_TOKEN);
        if (auth.getPassword()!= dto.getOldpassword()) throw new AuthException(EErrorType.AUTH_PASSWORD_ERROR,"eski sifre ve yeni sifre eslesmiyor");
        if (dto.getNewpassword()!= dto.getConfirmpassword()) throw new AuthException(EErrorType.AUTH_PASSWORD_ERROR,"sifreler eslesmiyor");
        auth.setPassword(dto.getNewpassword());
        update(auth);
        return true;
    }

    public Boolean registerAdmin(RegisterAdminRequestDto dto) {
        Auth auth=new Auth();
        auth.setEmail(dto.getEmail());
        auth.setRole(ERole.ADMIN);
        auth.setPassword(CodeGenerator.generateCode());
        save(auth);
        ModelRegisterAdmin modelRegisterAdmin=iManuelMapper.authToModelRegisterAdmin(auth,dto);
        modelRegisterAdmin.setAvatar(uploadImageCloudWithoutToken(dto.getAvatar()));
        directProducer.sendRegisterAdmin(modelRegisterAdmin);
        return true;
    }

    public Boolean registerManager(RegisterManagerRequestDto dto) {
        Auth auth = new Auth();
        auth.setEmail(dto.getEmail());
        auth.setRole(ERole.MANAGER);
        auth.setPassword(CodeGenerator.generateCode());
        save(auth);
        ModelRegisterManager modelRegisterManager=iManuelMapper.authToModelRegisterManager(auth,dto);
        directProducer.sendRegisterManager(modelRegisterManager);
        return true;
    }

    public Boolean registerEmployee(RegisterEmployeeRequestDto dto) {
        Auth auth = new Auth();
        auth.setEmail(dto.getEmail());
        auth.setRole(ERole.EMPLOYEE);
        auth.setPassword(CodeGenerator.generateCode());
        save(auth);
        ModelRegisterEmployee modelRegisterEmployee =iManuelMapper.authToModelRegisterEmployee(auth,dto);
        directProducer.sendRegisterEmployee(modelRegisterEmployee);
        return true;
    }



    public String uploadImageCloudWithoutToken(MultipartFile file) {
        Map config = new HashMap();
        config.put("cloud_name", "doqksh0xh");
        config.put("api_key", "871216635594134");
        config.put("api_secret", "6b3zcRZyWKeuiW6qIq4XvWnhVno");
        Cloudinary cloudinary = new Cloudinary(config);
        try{
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            System.out.println("\n\n\n avatar url: "+url+"\n\n\n");
            return url;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

}
