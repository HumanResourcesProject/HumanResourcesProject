package com.hrp.controller.employee;

import com.hrp.dto.request.BaseRequestDto;
import com.hrp.dto.response.BaseLeaveResponseDto;
import com.hrp.service.LeaveService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/requirements/employee")
public class LeaveEmployeeController {
    private final LeaveService leaveService;

    @PostMapping("/findallmyleaves")
    @CrossOrigin("*")
    public ResponseEntity<List<BaseLeaveResponseDto>> findAllMyLeaves(@RequestBody BaseRequestDto dto){
        System.out.println("findall leave metodu"+dto.toString());
        return ResponseEntity.ok(leaveService.findAllMyLeavesForEmployee(dto));
    }
}
