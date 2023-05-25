package com.hrp.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateManagerNoPhotoRequestDto {
    private String token;
    private String password;
    private String email;
    private String address;
    private String phone;
    private String company;
    private String jobEnd;
    private String avatar;
}
