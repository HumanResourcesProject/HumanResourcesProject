package com.hrp.controller;

import com.hrp.dto.request.BaseEmployeeRequestDto;
import com.hrp.dto.request.EmployeeUpdateRequestDto;
import com.hrp.dto.request.requirements.ExpenseRequestDto;
import com.hrp.dto.request.requirements.AdvancePaymentRequestDto;
import com.hrp.dto.request.requirements.LeaveRequestDto;
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
        return ResponseEntity.ok(employeeService.findAllEmployee(dto));
    }

    // update employee
    @PutMapping("/updateemployee")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateEmployee(EmployeeUpdateRequestDto dto){
        return ResponseEntity.ok(employeeService.updateEmployee(dto));
    }
    // find me
    @PostMapping("/findme")
    @CrossOrigin("*")
    public ResponseEntity<BaseEmployeeResponseDto> findMe(@RequestBody BaseEmployeeRequestDto dto){
        return ResponseEntity.ok(employeeService.findMe(dto));
    }

    @PostMapping("/createadvancepayment")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createAdvancePayment(@RequestBody AdvancePaymentRequestDto dto){
        return ResponseEntity.ok(employeeService.createAdvancePayment(dto));
    }

    @PostMapping("/createleave")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createLeave(@RequestBody LeaveRequestDto dto){
        return ResponseEntity.ok(employeeService.createLeave(dto));
    }

    @PostMapping("/createexpense")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createExpense(@RequestBody ExpenseRequestDto dto){
        return ResponseEntity.ok(employeeService.createExpnse(dto));
    }



}
