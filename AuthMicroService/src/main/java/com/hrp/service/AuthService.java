package com.hrp.service;

import com.hrp.dto.request.*;
import com.hrp.dto.response.AuthLoginResponse;
import com.hrp.exception.AuthException;
import com.hrp.exception.EErrorType;
import com.hrp.mapper.IAuthMapper;
import com.hrp.rabbitmq.model.ModelAuthId;
import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.rabbitmq.model.ModelRegisterCompanyManager;
import com.hrp.rabbitmq.producer.DirectProducer;
import com.hrp.repository.IAuthRepository;
import com.hrp.repository.entity.Auth;
import com.hrp.repository.entity.enums.ERole;
import com.hrp.repository.entity.enums.EStatus;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManagerImpl<Auth,Long> {
    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;
    private final DirectProducer directProducer;

    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager, DirectProducer directProducer) {
        super(authRepository);
        this.authRepository=authRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.directProducer = directProducer;
    }


    public AuthLoginResponse authLogin(AuthLoginDto dto){
        Optional<Auth> auth =  authRepository.findOptionalByEmailAndPassword(dto.getEmail(),dto.getPassword());
        if(auth.isEmpty())
            throw new AuthException(EErrorType.AUTH_PARAMETER_ERROR,"HATA123");
        Optional<String> token = jwtTokenManager.createToken(auth.get().getId());
        if(token.isEmpty())
            throw new AuthException(EErrorType.INVALID_TOKEN);

        return AuthLoginResponse.builder()
                .token(token.get())
                .role(auth.get().getRole().name())  //Hata olursa buradan olabilir, response'ta dönüş şekline bi bakalım.
                .build();
    }


    public Boolean activateStatus(ActivateRequestDto dto) {
        Optional<Auth> auth=findById(dto.getId());
        if (auth.isEmpty()){
            throw new AuthException(EErrorType.USER_NOT_BE_FOUND);
        }
        if (dto.getActivationCode().equals(auth.get().getPassword())){
            auth.get().setStatus(EStatus.ACTIVE);
            save(auth.get());
            //employeeManager.activateStatus(dto.getId());
            //companyManagerManager.activateStatus(dto.getId());
            return  true;
        }else{
            throw new AuthException(EErrorType.ACTIVATE_CODE_ERROR);

        }
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

    public void registerAdmin(ModelRegisterAdmin model) {
        Auth auth = IAuthMapper.INSTANCE.toAuth(model);
        try {
            auth.setRole(ERole.ADMIN);
            save(auth);
            ModelAuthId modelAuthId= new ModelAuthId();
            modelAuthId.setAuthId(auth.getId());
            directProducer.sendAuthIdForAdmin(modelAuthId);
        }catch (Exception e){
            e.printStackTrace();
            throw  new AuthException(EErrorType.AUTH_NOT_CREATED);
        }

    }

    public void registerCompanyManager(ModelRegisterCompanyManager model) {
        Auth auth = IAuthMapper.INSTANCE.toAuth(model);
        try {
            auth.setRole(ERole.COMPANY_MANAGER);
            save(auth);
            ModelAuthId modelAuthId= new ModelAuthId();
            modelAuthId.setAuthId(auth.getId());
            directProducer.sendAuthIdForEmployee(modelAuthId);
        }catch (Exception e){
            e.printStackTrace();
            throw  new AuthException(EErrorType.AUTH_NOT_CREATED);
        }
    }
}
