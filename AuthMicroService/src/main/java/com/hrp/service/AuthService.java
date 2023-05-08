package com.hrp.service;


import com.hrp.dto.request.AuthLoginDto;
import com.hrp.dto.request.AuthRegisterRequestDto;
import com.hrp.dto.request.ChangePasswordDto;
import com.hrp.dto.response.AuthLoginResponse;
import com.hrp.exception.AuthException;
import com.hrp.exception.EErrorType;
import com.hrp.mapper.IAuthMapper;
import com.hrp.repository.IAuthRepository;
import com.hrp.repository.entity.Auth;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService extends ServiceManagerImpl<Auth,Long> {
    private final IAuthRepository authRepository;
    private final JwtTokenManager jwtTokenManager;

    public AuthService(IAuthRepository authRepository, JwtTokenManager jwtTokenManager) {
        super(authRepository);
        this.authRepository=authRepository;
        this.jwtTokenManager = jwtTokenManager;
    }



    public  Boolean register(AuthRegisterRequestDto dto) {
        if (!dto.getPassword().equals(dto.getRepassword()))
            throw new AuthException(EErrorType.AUTH_PASSWORD_ERROR);
        Auth auth= IAuthMapper.INSTANCE.toAuth(dto);
        if (authRepository.findOptionalByEmail(auth.getEmail()).stream().count()!=0){
            throw new AuthException(EErrorType.AUTH_USERNAME_ERROR);}
        save(auth);
        return true;
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
                .build();
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



}
