package com.hrp.fallback;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("")
public class FallbackController {
    @GetMapping("/fallbackadmin")
    public ResponseEntity<String> fallbackadmin(){
        return ResponseEntity.ok("Admin Service is temporarily disabled");
    }
}
