package com.hrp.controller.employee;

import com.hrp.dto.request.BaseRequestDto;
import com.hrp.dto.response.BaseAdvancePaymentResponseDto;
import com.hrp.service.AdvancePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requirements/employee")
@RequiredArgsConstructor
public class AdvancePaymentEmployeeController {
    private final AdvancePaymentService advancePaymentService;

    @PostMapping("/findallmyadvances")
    @CrossOrigin("*")
    public ResponseEntity<List<BaseAdvancePaymentResponseDto>> findAllMyAdvances(@RequestBody BaseRequestDto dto){
        return ResponseEntity.ok(advancePaymentService.findAllMyAdvancePaymentForEmployee(dto));
    }


    @GetMapping("/apideneme")
    public ResponseEntity<String> apideneme(){
        return ResponseEntity.ok("api denemesi");
    }




}
