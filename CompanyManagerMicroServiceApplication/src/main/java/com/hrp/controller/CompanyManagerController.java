package com.hrp.controller;

import com.hrp.dto.request.CreateCompanyManagerRequestDto;
import com.hrp.dto.request.CreateEmployeeRequestDto;
import com.hrp.dto.request.UpdateCompanyManagerRequestDto;
import com.hrp.dto.response.CompanyManagerFindAllResponseDto;
import com.hrp.service.CompanyManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/companymanager")
public class CompanyManagerController {
    private final CompanyManagerService companyManagerService;

//    @PostMapping("/createcompanymanager")
//    @CrossOrigin("*")
//    public ResponseEntity<Boolean> createCompanyManager(CreateCompanyManagerRequestDto dto){
//        return ResponseEntity.ok(companyManagerService.createCompanyManager(dto));
//    }

    @PostMapping("/createemployee")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createEmployee(CreateEmployeeRequestDto dto){
        return ResponseEntity.ok(companyManagerService.createEmployee(dto));
    }


    @GetMapping("/getallmanager")
    @CrossOrigin("*")
    public ResponseEntity<List<CompanyManagerFindAllResponseDto>> findAll(){
        return ResponseEntity.ok(companyManagerService.findAllManager());
    }

    @PutMapping("/updatecompanymanager")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateCompanyManager(UpdateCompanyManagerRequestDto dto){
        return ResponseEntity.ok(companyManagerService.updateCompanyManager(dto));
    }
    @PostMapping("/deletecompanymanager")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> deleteCompanyManager (String token){
        return ResponseEntity.ok(companyManagerService.deleteCompanyManager(token));
    }
}
