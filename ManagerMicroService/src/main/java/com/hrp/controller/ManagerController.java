package com.hrp.controller;

import com.hrp.dto.request.TokenDto;
import com.hrp.dto.request.UpdateManagerRequestDto;
import com.hrp.dto.response.AllLeaveFormResponseDto;
import com.hrp.dto.response.BaseManagerResponseDto;
import com.hrp.rabbitmq.model.ModelTurnAllAdvancePaymentRequest;
import com.hrp.rabbitmq.model.ModelTurnAllExpenseRequest;
import com.hrp.rabbitmq.model.ModelTurnAllLeaveRequest;
import com.hrp.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/manager")
public class ManagerController {
    private final ManagerService managerService;

    @PostMapping("/findall")
    @CrossOrigin("*")
    public ResponseEntity<List<BaseManagerResponseDto>> findAll(@RequestBody TokenDto dto){
        System.out.println("find all");
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
        System.out.println("find me metodu");
        return ResponseEntity.ok(managerService.findMe(dto));
    }

    @PostMapping("/findallleave")
    @CrossOrigin("*")
    public ResponseEntity<List<ModelTurnAllLeaveRequest>> findAllLeave (@RequestBody TokenDto dto){
        System.out.println("findall leave metodu");
        return ResponseEntity.ok(managerService.findAllLeave(dto));
    }

    @PostMapping("/findallexpense")
    @CrossOrigin("*")
    public ResponseEntity<List<ModelTurnAllExpenseRequest>> findAllExpense (@RequestBody TokenDto dto){
        System.out.println("findall expense metodu");
        return ResponseEntity.ok(managerService.findAllExpense(dto));
    }

    @PostMapping("/findalladvancepayment")
    @CrossOrigin("*")
    public ResponseEntity<List<ModelTurnAllAdvancePaymentRequest>> findAllAdvancePayment (@RequestBody TokenDto dto){
        System.out.println("findall advancepayment metodu");
        return ResponseEntity.ok(managerService.findAllAdvancePayment(dto));
    }

    // Genel bir findAll leave, expense, adv.
    // leave, expense, adv. findAll onaylananmais
    // leave, expense, adv. findAll onaylanmis.

    // Detay sayfası.(Later)

    // onaylama ve reddetme islemleri

}