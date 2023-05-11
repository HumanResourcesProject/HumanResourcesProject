package com.hrp.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetAllEmployeeResponseDto {
    String name;
    String middleName;
    String surname;
    String dateOfBirth;
    String placeOfBirth;
    String identityNumber;
    String job;
    String department;
    String email;
    String address;
    String phone;
    String company;
    String avatar;
}
