package com.hrp.controller;

import com.hrp.dto.request.TokenDto;
import com.hrp.dto.request.UpdateManagerRequestDto;
import com.hrp.dto.response.BaseManagerResponseDto;
import com.hrp.dto.response.EmployeeRequestAndResponseDto;
import com.hrp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping("/findall")
    @CrossOrigin("*")
    public ResponseEntity<List<BaseManagerResponseDto>> findAll(@RequestBody TokenDto dto){
        return ResponseEntity.ok(managerService.findAllManager(dto));
    }

    @PutMapping("/updatemanager")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateCompanyManager(UpdateManagerRequestDto dto){
        return ResponseEntity.ok(managerService.updateManager(dto));
    }
    @PostMapping("/deletemanager")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> deleteCompanyManager (TokenDto dto){
        return ResponseEntity.ok(managerService.deleteManager(dto));
    }

    @PostMapping("/getfindme")
    @CrossOrigin("*")
    public ResponseEntity<BaseManagerResponseDto> findMe(@RequestBody TokenDto dto){
        return ResponseEntity.ok(managerService.findMe(dto));
    }
    // findall employee
    @PostMapping("/findallmyemployee")
    @CrossOrigin("*")
    public ResponseEntity<List<EmployeeRequestAndResponseDto>> findAllMyEmployee(@RequestBody TokenDto dto){
        return ResponseEntity.ok(managerService.findAllMyEmployee(dto));
    }
    @PostMapping("/findallmyemployeecount")
    @CrossOrigin("*")
    public ResponseEntity<Long> findAllMyEmployeeCount(@RequestBody TokenDto dto){
        return ResponseEntity.ok(managerService.findAllMyEmployee(dto).stream().count());
    }
    @GetMapping("/apideneme")
    public ResponseEntity<String> apideneme(){
        return ResponseEntity.ok("api denemesi");
    }



    // onaylama ve reddetme islemleri

}