package com.hrp.controller.manager;

import com.hrp.dto.request.BaseRequestDto;
import com.hrp.dto.response.BaseAdvancePaymentResponseDto;
import com.hrp.service.AdvancePaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/requirements/manager")
public class AdvancePaymentManagerController {
    private final AdvancePaymentService advancePaymentService;

    @PostMapping("/findallmyadvances")
    @CrossOrigin("*")
    public ResponseEntity<List<BaseAdvancePaymentResponseDto>> findAllMyAdvancesForManager(@RequestBody BaseRequestDto dto){
        return ResponseEntity.ok(advancePaymentService.findAllMyAdvancePaymentForManager(dto));
    }

    @PostMapping("/findallmyadvancespending")
    @CrossOrigin("*")
    public ResponseEntity<List<BaseAdvancePaymentResponseDto>> findAllMyAdvancesPendingForManager(@RequestBody BaseRequestDto dto){
        return ResponseEntity.ok(advancePaymentService.findAllMyAdvancePaymentPendingForManager(dto));
    }

    @PostMapping("/findallmyadvancescount")
    @CrossOrigin("*")
    public ResponseEntity<Long> findAllMyAdvancesForManagerCount(@RequestBody BaseRequestDto dto){
        return ResponseEntity.ok(advancePaymentService.findAllMyAdvancePaymentForManager(dto).stream().count());
    }
}
