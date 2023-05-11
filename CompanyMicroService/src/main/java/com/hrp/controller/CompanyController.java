package com.hrp.controller;

import com.hrp.dto.request.BaseRequestCompanyDto;
import com.hrp.dto.request.CreateCompanyRequestDto;
import com.hrp.dto.response.BaseCompanyResponseDto;
import com.hrp.service.CompanyService;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/company")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping("/createcompany")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createCompany(CreateCompanyRequestDto dto){
        System.out.println("create company metodu");
        return ResponseEntity.ok(companyService.createCompany(dto));
    }

    //findall
    @PostMapping("/findall")
    @CrossOrigin("*")
    public ResponseEntity<List<BaseCompanyResponseDto>> findAll(@RequestBody BaseRequestCompanyDto dto){
        System.out.println("find all company");
        System.out.println(dto.toString());
        return ResponseEntity.ok(companyService.findAllDto());
    }



}
