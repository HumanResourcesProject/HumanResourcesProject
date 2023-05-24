package com.hrp.controller;

import com.hrp.dto.request.*;
import com.hrp.dto.response.BaseAdminResponseDto;
import com.hrp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.servlet.annotation.HttpMethodConstraint;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

//Dropbox
//App key: 3qvpw8qwqq2uyp6
//        App secret
//        4p6las2r45z1mi4
//sl.BdUQl8Fp8wPKfcRM-G13tIIqKQ-IIRz6v2yXTy69LiomEJiGa5UrOu_ewMT1mm73qaqzBCXd5Vm2bgb17LDWFn5mv5w9TP7neFxDgQiBe9XPWBTVQyEY-E_grDSIcTzuVmRCc80
@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
@CrossOrigin("*")
public class AdminController {
    private final AdminService adminService;


    //@RolesAllowed("ADMINLEVEL1")
    //@HttpMethodConstraint(value = "POST",rolesAllowed = "ADMINLEVEL1")
    //@PreAuthorize("hasAuthority('ADMINLEVEL1')")
    //@CrossOrigin("*")
    @PostMapping("/getfindme")
   // @PreAuthorize("hasAuthority('ADMINLEVEL1')")
    @CrossOrigin("*")
    @HttpMethodConstraint(value = "POST")
    public ResponseEntity<BaseAdminResponseDto> getFindMe(@RequestBody TokenDto dto){
        Logger.getLogger("logger namei burası");
        return ResponseEntity.ok(adminService.findMe(dto));
    }

    @PostMapping("/updateimage")
   // @PreAuthorize("hasAuthority('ADMINLEVEL1')")
    @CrossOrigin("*")
    public ResponseEntity<String> updateImage(@RequestParam("file") MultipartFile file,String token) throws IOException {
        return ResponseEntity.ok(adminService.updateImage(file,token));
    }

    @PutMapping("/updateadmin")
  //  @PreAuthorize("hasAuthority('ADMINLEVEL1')")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateAdmin (@RequestBody BaseAdminRequestDto dto){
        return ResponseEntity.ok(adminService.updateAdmin(dto));
    }

    @GetMapping("/getalladmin")
   // @PreAuthorize("hasAuthority('ADMINLEVEL1')")
    @HttpMethodConstraint(value = "GET")
    public ResponseEntity<List<BaseAdminResponseDto>> getAll(){
        return ResponseEntity.ok(adminService.findAllAdmin());
    }
    @GetMapping("/apideneme")
    public ResponseEntity<String> apideneme(){
        return ResponseEntity.ok("api denemesi");
    }



    @GetMapping("/securitydeneme1")
   // @PreAuthorize("hasAuthority('ADMINLEVEL1')")
    @CrossOrigin("*")
    public ResponseEntity<String> securityDeneme1(){
        return ResponseEntity.ok("security denemesi 1. metodu");
    }

    @GetMapping("/securitydeneme2")
  //  @PreAuthorize("hasAuthority('ADMINLEVEL2')")
    @CrossOrigin("*")
    public ResponseEntity<String> securityDeneme2(){
        return ResponseEntity.ok("security denemesi 2. metodu");
    }


}
