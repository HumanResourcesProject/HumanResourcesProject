package com.hrp.controller;

import com.hrp.dto.request.BaseEmployeeRequestDto;
import com.hrp.dto.request.AdvancePaymentRequestDto;
import com.hrp.dto.response.BaseEmployeeResponseDto;
import com.hrp.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;


    @PostMapping("/findallemployee")
    @CrossOrigin("*")
    public ResponseEntity<List<BaseEmployeeResponseDto>> findAll(@RequestBody BaseEmployeeRequestDto dto){
        return ResponseEntity.ok(employeeService.findAll(dto));
    }

    @PostMapping("/createadvancedpayment")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createPermission(@RequestBody AdvancePaymentRequestDto dto){
        return ResponseEntity.ok(employeeService.createPermission(dto));
    }



}
