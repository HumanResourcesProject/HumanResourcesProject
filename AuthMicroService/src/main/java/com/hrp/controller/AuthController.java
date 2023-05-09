package com.hrp.controller;


import com.hrp.dto.request.ActivateRequestDto;
import com.hrp.dto.request.AuthLoginDto;
import com.hrp.dto.request.ChangePasswordDto;
import com.hrp.dto.response.AuthLoginResponse;
import com.hrp.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @CrossOrigin("*")
    public ResponseEntity<AuthLoginResponse> authLogin(@RequestBody AuthLoginDto dto){
        return ResponseEntity.ok((authService.authLogin(dto)));
    }

    @PostMapping("/activatestatus")
    public  ResponseEntity<Boolean> activateStatus2(@RequestBody ActivateRequestDto dto){
        return   ResponseEntity.ok(authService.activateStatus(dto));
    }

    @PostMapping("/changepassword")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordDto dto){
        return ResponseEntity.ok(authService.changePassword(dto));
    }


}
