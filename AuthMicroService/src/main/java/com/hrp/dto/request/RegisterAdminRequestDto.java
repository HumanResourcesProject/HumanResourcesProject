package com.hrp.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterAdminRequestDto {

    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private MultipartFile avatar;
}
