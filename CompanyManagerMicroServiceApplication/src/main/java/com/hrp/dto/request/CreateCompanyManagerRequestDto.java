package com.hrp.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateCompanyManagerRequestDto {
    private String identityNumber;
    private String name;
    private String middleName;
    private String surname;
    private String dateOfBirth;
    private String email;
    private String address;
    private String phone;
    private String company;
    private String job;
    private String department;
    private String jobStart;
    private MultipartFile avatar;
}
