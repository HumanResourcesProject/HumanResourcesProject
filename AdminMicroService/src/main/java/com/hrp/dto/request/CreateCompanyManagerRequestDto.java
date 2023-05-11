package com.hrp.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

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
    private String birthDate;
    private String email;
    private String address;
    private String phone;
    private String company;
    private String job;
    private String birthPlace;
    private String jobStart;
    //private MultipartFile avatar;
}
