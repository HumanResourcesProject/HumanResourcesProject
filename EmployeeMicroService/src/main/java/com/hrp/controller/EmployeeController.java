package com.hrp.controller;

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


    @GetMapping("/findallemployee")
    @CrossOrigin("*")
    public ResponseEntity<List<BaseEmployeeResponseDto>> getAll(){
        return ResponseEntity.ok(employeeService.findAllEmployee());
    }



}
