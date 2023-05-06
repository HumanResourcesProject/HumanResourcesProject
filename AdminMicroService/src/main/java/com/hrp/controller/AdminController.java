package com.hrp.controller;

import com.hrp.dto.request.CreateAdminRequestDto;
import com.hrp.dto.request.UpdateAdminRequestDto;
import com.hrp.dto.request.UpdateAdminRequestDtoBuse;
import com.hrp.dto.response.BaseAdminResponseDto;
import com.hrp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

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

    @PostMapping("/createadmin")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createAdmin(@Valid CreateAdminRequestDto dto){
        System.out.println("create admin metodu");
        return ResponseEntity.ok(adminService.createAdmin(dto));
    }

    @GetMapping("/getadmin")
    public ResponseEntity<BaseAdminResponseDto> findMe(Long id){
        System.out.println("find me metodu");
        return ResponseEntity.ok(adminService.findMe(id));
    }

    @PostMapping("/imagescloud")
    @CrossOrigin("*")
    public ResponseEntity<String> uploadImageCloud(@RequestParam("file") MultipartFile file, @RequestParam ("id") Long id) throws IOException {
        System.out.println("upload image cloud metodu");
        return ResponseEntity.ok(adminService.uploadImageCloud(file, id));
    }

    @PutMapping("/updateadmin")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateAdmin (@RequestBody UpdateAdminRequestDto dto){
        System.out.println("update admin metodu");
        return ResponseEntity.ok(adminService.updateAdmin(dto));
    }
    @PutMapping("/updateadminbuse")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateAdminBuse(@RequestBody UpdateAdminRequestDtoBuse dto){
        System.out.println("update admin metodu");
        return ResponseEntity.ok(adminService.updateAdminBuse(dto));
    }
    @GetMapping("/getalladmin")
    @CrossOrigin("*")
    public ResponseEntity<Iterable<BaseAdminResponseDto>> getAll(){
        System.out.println("get all metodu ");
        return ResponseEntity.ok(adminService.findAllAdmin());
    }

    @GetMapping("/apideneme")
    @CrossOrigin("*")
    public ResponseEntity <String> getAllapigateway(){
        System.out.println("api gateway metodu");
        String deneme = "api gateway icin deneme yazisi görüyorsan calisiyor";
        return ResponseEntity.ok(deneme);
    }


}
