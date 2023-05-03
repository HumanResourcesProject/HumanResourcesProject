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
public class UpdateAdminRequestDto {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private String address;
    private MultipartFile avatar;

}
