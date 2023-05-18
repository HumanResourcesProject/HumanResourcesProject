package com.hrp.controller.manager;

import com.hrp.dto.request.BaseAnswerDto;
import com.hrp.dto.request.BaseRequestDto;
import com.hrp.dto.response.BaseLeaveResponseDto;
import com.hrp.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/requirements/manager")
@RequiredArgsConstructor
public class LeaveManagerController {
    private final LeaveService leaveService;
    @PostMapping("/findallmyleaves")
    @CrossOrigin("*")
    public ResponseEntity<List<BaseLeaveResponseDto>> findAllMyLeavesForManager(@RequestBody BaseRequestDto dto){
        System.out.println("findall leave metodu"+dto.toString());
        return ResponseEntity.ok(leaveService.findAllMyLeavesForManager(dto));
    }
    @PostMapping("/findallmyleavespending")
    @CrossOrigin("*")
    public ResponseEntity<List<BaseLeaveResponseDto>> findAllMyLeavesPendingForManager(@RequestBody BaseRequestDto dto){
        System.out.println("findall leave metodu"+dto.toString());
        return ResponseEntity.ok(leaveService.findAllMyLeavesPendingForManager(dto));
    }
    @PostMapping("/findallmyleavescount")
    @CrossOrigin("*")
    public ResponseEntity<Long> findAllMyLeavesForManagerCount(@RequestBody BaseRequestDto dto){
        System.out.println("findall leave metodu"+dto.toString());
        return ResponseEntity.ok(leaveService.findAllMyLeavesPendingForManager(dto).stream().count());
    }
    @PutMapping("/approveleave")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> approveleave(BaseAnswerDto dto) {
        return ResponseEntity.ok(leaveService.approveleave(dto));
    }

    //rejeect
    @PutMapping("/rejectleave")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> rejectleave(BaseAnswerDto dto) {
        return ResponseEntity.ok(leaveService.rejectleave(dto));
    }



}
