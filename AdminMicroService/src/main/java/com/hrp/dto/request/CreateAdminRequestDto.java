package com.hrp.dto.request;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateAdminRequestDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String phone;
    private String address;
    private MultipartFile avatar;

}
