package com.hrp.utility;

import com.hrp.repository.entity.Auth;
import com.hrp.repository.entity.enums.ERole;
import com.hrp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.LocalDate;

@Component
@RequiredArgsConstructor
public class TestAndRun {
   private final AuthService authService;

    @PostConstruct
    public void init(){
        new Thread(()->{
            run();
        }).start();
    }
    public void run (){
        if (authService.findAll().isEmpty()){
            Auth auth = new Auth();
            auth.setEmail("admin");
            auth.setPassword("admin");
            auth.setRole(ERole.ADMIN);
            auth.setCreateDate(LocalDate.now().toString());
            authService.save(auth);
        }
    }
}
