package com.hrp.dto.response;

import lombok.*;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeRequestAndResponseDto {
    private Long authId;
    private Long managerId;
    private String identityNumber;
    private String name;
    private String middleName;
    private String surname;
    private String birthDate;
    private String birthPlace;
    private String jobStart;
    private String occupation;
    private String department;
    private String email;
    private String phone;
    private String address;
    private String company;
    private String jobEnd;
    private String avatar;
    private Long salary;
}
