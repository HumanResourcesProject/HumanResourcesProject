package com.hrp.controller;


import com.hrp.dto.request.*;
import com.hrp.dto.response.AuthLoginResponse;
import com.hrp.exception.AuthException;
import com.hrp.exception.EErrorType;
import com.hrp.service.AuthService;
import com.hrp.utility.RegisterErrorCheck;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    @CrossOrigin("*")
    public ResponseEntity<AuthLoginResponse> authLogin(@RequestBody @Valid AuthLoginDto dto) {
        if (dto.getEmail() == null || dto.getEmail().trim().isEmpty())
        {throw new AuthException(EErrorType.E_MAIL_NOT_EMPTY);}
        if (dto.getPassword() == null || dto.getPassword().trim().isEmpty())
        {throw new AuthException(EErrorType.PASSWORD_NOT_EMPTY);}
        return ResponseEntity.ok((authService.authLogin(dto)));
    }

    @PostMapping("/changepassword")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordDto dto) {
        if (dto.getNewpassword() != dto.getConfirmpassword()) {throw new AuthException(EErrorType.PASSWORD_NOT_MATCH);}
        return ResponseEntity.ok(authService.changePassword(dto));
    }

    @PostMapping("/forgotpassword")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> forgotPassword(@RequestBody AuthLoginDto dto) {
        return ResponseEntity.ok(authService.forgotPassword(dto));
    }

    // register admin
    @PostMapping("/registeradmin")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> registerAdmin(RegisterAdminRequestDto dto) {

        System.out.println("kontroller öncesi");
        RegisterErrorCheck.registerErrorInController(dto.getEmail(), dto.getName(), dto.getSurname(), dto.getPhone());
        return ResponseEntity.ok(authService.registerAdmin(dto));
    }

    // register Manager
    @PostMapping("/registermanager")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> registerManager(@RequestBody RegisterManagerRequestDto dto) {
        RegisterErrorCheck.registerErrorInController(dto.getEmail(), dto.getName(), dto.getSurname(), dto.getPhone());
        return ResponseEntity.ok(authService.registerManager(dto));
    }

    // register employee
    @PostMapping("/registeremployee")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> registerEmployee(@RequestBody RegisterEmployeeRequestDto dto) {
        RegisterErrorCheck.registerErrorInController(dto.getEmail(), dto.getName(), dto.getSurname(), dto.getPhone());
        return ResponseEntity.ok(authService.registerEmployee(dto));
    }

    @GetMapping("/getall")
    @CrossOrigin("*")
    public ResponseEntity<String> testDeneme(){
        return ResponseEntity.ok("deneme basarili");
    }


}
