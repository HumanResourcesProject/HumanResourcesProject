package com.hrp.controller;

import com.hrp.dto.request.TokenDto;
import com.hrp.dto.request.UpdateManagerRequestDto;
import com.hrp.dto.response.BaseManagerResponseDto;
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


    @PostMapping("/updateimage")
    @CrossOrigin("*")
    public ResponseEntity<String> uploadImageCloud(@RequestParam("file") MultipartFile file, String token) throws IOException {
        System.out.println("upload image cloud metodu");
        return ResponseEntity.ok(managerService.updateImage(file,token));
    }

}