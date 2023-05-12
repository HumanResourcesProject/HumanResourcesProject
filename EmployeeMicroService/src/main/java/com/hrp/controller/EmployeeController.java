package com.hrp.controller;

import com.hrp.dto.response.GetAllEmployeeResponseDto;
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


    @GetMapping("/getallemployee")
    @CrossOrigin("*")
    public ResponseEntity<List<GetAllEmployeeResponseDto>> getAll(){
        return ResponseEntity.ok(employeeService.findAllEmployee());
    }
//    @PostMapping("/createemployee")
//    @CrossOrigin("*")
//    public ResponseEntity<Boolean> createEmployee(){
//        return ResponseEntity.ok(true);
//    }



}
