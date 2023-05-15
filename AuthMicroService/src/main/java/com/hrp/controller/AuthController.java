package com.hrp.controller;


import com.hrp.dto.request.*;
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

    @PostMapping("/changepassword")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> changePassword(@RequestBody ChangePasswordDto dto){
        return ResponseEntity.ok(authService.changePassword(dto));
    }

    // register admin
    @PostMapping("/registeradmin")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> registerAdmin( RegisterAdminRequestDto dto){
        return ResponseEntity.ok(authService.registerAdmin(dto));
    }

    // register Manager
    @PostMapping("/registermanager")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> registerManager(@RequestBody  RegisterManagerRequestDto dto){
        return ResponseEntity.ok(authService.registerManager(dto));
    }

    // register employee
    @PostMapping("/registeremployee")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> registerEmployee(@RequestBody RegisterEmployeeRequestDto dto){
        return ResponseEntity.ok(authService.registerEmployee(dto));
            }




}
