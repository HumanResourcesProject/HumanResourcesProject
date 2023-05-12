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
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
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
    @PostMapping("/getmanager")
    @CrossOrigin("*")
    public ResponseEntity<CompanyManagerFindAllResponseDto> findMe(@RequestBody GetShortDetailRequestDto dto){
        System.out.println("find me metodu");
        return ResponseEntity.ok(companyManagerService.findMe(dto));
    }

    @PostMapping("/imagescloud")
    @CrossOrigin("*")
    public ResponseEntity<String> uploadImageCloud(@RequestParam("file") MultipartFile file, String token) throws IOException {
        System.out.println("upload image cloud metodu");
        return ResponseEntity.ok(companyManagerService.uploadImageCloud(file,token));
    }
}