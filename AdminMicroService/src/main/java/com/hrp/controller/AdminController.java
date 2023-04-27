package com.hrp.controller;

import com.hrp.dto.request.CreateAdminRequestDto;
import com.hrp.dto.response.FindAdminResponseDto;
import com.hrp.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {
    private final AdminService adminService;

    @PostMapping("/createadmin")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> createAdmin(CreateAdminRequestDto dto){
        return ResponseEntity.ok(adminService.createAdmin(dto));
    }

    public ResponseEntity<FindAdminResponseDto> findMe(Long id){
        return ResponseEntity.ok(adminService.findMe(id));
    }


}
