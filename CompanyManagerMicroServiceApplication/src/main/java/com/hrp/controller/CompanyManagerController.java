package com.hrp.controller;

import com.hrp.dto.request.CreateCompanyManagerRequestDto;
import com.hrp.dto.request.CreateEmployeeRequestDto;
import com.hrp.dto.request.GetShortDetailRequestDto;
import com.hrp.dto.request.UpdateCompanyManagerRequestDto;
import com.hrp.dto.response.CompanyManagerFindAllResponseDto;
import com.hrp.dto.response.GetShortDetailResponseDto;
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


    @GetMapping("/findall")
    @CrossOrigin("*")
    public ResponseEntity<List<CompanyManagerFindAllResponseDto>> findAll(){
        System.out.println("find all");
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
    @PostMapping("/getshortdetail")
    @CrossOrigin("*")
    public ResponseEntity<GetShortDetailResponseDto> getShortDetail (@RequestBody GetShortDetailRequestDto dto){
        System.out.println(dto.getToken());
        return ResponseEntity.ok(companyManagerService.getShortDetail(dto));
    }
}