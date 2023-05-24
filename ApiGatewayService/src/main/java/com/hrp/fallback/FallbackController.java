package com.hrp.fallback;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;
@RestController
@RequestMapping("")
public class FallbackController {
    @GetMapping("/basefallback")
    public ResponseEntity<?> fallbackadmin() {
        try {
            Resource resource = new ClassPathResource("public/index.html");
            String html = StreamUtils.copyToString(resource.getInputStream(), Charset.defaultCharset());
            return ResponseEntity.ok().contentType(MediaType.TEXT_HTML).body(html);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error retrieving fallback page");
        }
    }
}
