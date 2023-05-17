package com.hrp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BaseEmployeeResponseDto {
    String name;
    String middleName;
    String surname;
    Long managerId;
    String birthDate;
    String birthPlace;
    Long salary;
    String identityNumber;
    String Occupation;
    String department;
    String email;
    String address;
    String phone;
    String company;
    String avatar;
}
