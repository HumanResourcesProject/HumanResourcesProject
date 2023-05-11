package com.hrp.controller;

import com.hrp.dto.request.*;
import com.hrp.dto.response.BaseAdminResponseDto;
import com.hrp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;

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
    @PostMapping("/createcompanymanager")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createCompanyManager(@Valid @RequestBody CreateCompanyManagerRequestDto dto){
        System.out.println("create companymanager metodu");
        System.out.println(dto.toString());
        return ResponseEntity.ok(adminService.createCompanyManager(dto));
    }

    @PostMapping("/getadmin")
    @CrossOrigin("*")
    public ResponseEntity<BaseAdminResponseDto> findMe(@RequestBody BaseRequestDto dto){
        System.out.println("find me metodu");
        return ResponseEntity.ok(adminService.findMe(dto));
    }

    @PostMapping("/imagescloud")
    @CrossOrigin("*")
    public ResponseEntity<String> uploadImageCloud(@RequestParam("file") MultipartFile file,String token) throws IOException {
        System.out.println("upload image cloud metodu");
        return ResponseEntity.ok(adminService.uploadImageCloud(file,token));
    }

    @PutMapping("/updateadmin")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateAdmin (@RequestBody BaseAdminRequestDto dto){
        System.out.println("update admin metodu");
        return ResponseEntity.ok(adminService.updateAdmin(dto));
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
    @PostMapping("/getshortdetail")
    @CrossOrigin("*")
    public ResponseEntity<BaseAdminResponseDto> getShortDetail (@RequestBody BaseAdminRequestDto dto){
        System.out.println(dto.getToken());
        return ResponseEntity.ok(adminService.getShortDetail(dto));
    }


}
