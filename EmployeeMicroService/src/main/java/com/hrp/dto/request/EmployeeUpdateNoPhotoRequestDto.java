package com.hrp.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeUpdateNoPhotoRequestDto {
    private String token;
    private String phone;
    private String address;
    private String avatar;
}
