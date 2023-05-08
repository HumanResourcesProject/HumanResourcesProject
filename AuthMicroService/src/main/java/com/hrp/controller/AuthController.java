package com.hrp.controller;


import com.hrp.dto.request.AuthLoginDto;
import com.hrp.dto.request.AuthRegisterRequestDto;
import com.hrp.dto.request.ChangePasswordDto;
import com.hrp.dto.response.AuthLoginResponse;
import com.hrp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> register(@RequestBody @Valid AuthRegisterRequestDto dto) throws InterruptedException {
        return ResponseEntity.ok( authService.register(dto));

    }
    @PostMapping("/login")
    @CrossOrigin("*")
    public ResponseEntity<AuthLoginResponse> authLogin(@RequestBody AuthLoginDto dto){
        System.out.println(dto.getEmail()+ " "+dto.getPassword());
        return ResponseEntity.ok((authService.authLogin(dto)));
    }

    @PostMapping("/changepassword")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordDto dto){
        System.out.println("aaaxxaa");
        return ResponseEntity.ok(authService.changePassword(dto));
    }


}
