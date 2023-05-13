package com.hrp.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterEmployeeRequestDto {
    private String identityNumber;
    private String name;
    private String middleName;
    private String surname;
    private String birthDate;
    private String birthPlace;
    private String jobStart;
    private String Occupation;
    private String Department;
    private String email;
    private String phone;
    private String address;
    private String company;
}
