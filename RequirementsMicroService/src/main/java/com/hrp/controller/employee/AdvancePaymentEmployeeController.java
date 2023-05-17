package com.hrp.controller.employee;

import com.hrp.dto.request.BaseRequestDto;
import com.hrp.dto.response.BaseAdvancePaymentResponseDto;
import com.hrp.service.AdvancePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class AdvancePaymentEmployeeController {
    private final AdvancePaymentService advancePaymentService;

    @PostMapping("/findallmyadvances")
    @CrossOrigin("*")
    public List<BaseAdvancePaymentResponseDto> findAllMyAdvances(@RequestBody BaseRequestDto dto){
        return advancePaymentService.findAllMyAdvancePaymentForEmployee(dto);
    }



}
