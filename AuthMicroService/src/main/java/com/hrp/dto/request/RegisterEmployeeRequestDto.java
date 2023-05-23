package com.hrp.dto.request;

import lombok.*;
import org.intellij.lang.annotations.RegExp;

import javax.validation.constraints.NotNull;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RegisterEmployeeRequestDto {
    private String token;
    private String identityNumber;
    private String name;
    private String middleName;

    private String surname;
    @NotNull(message = "Doğum tarihi boş olamaz.")
    private String birthDate;
    @NotNull(message = "Doğum yeri boş olamaz.")
    private String birthPlace;
    private String jobStart;
    private String occupation;
    private String department;
    private String email;
    private String phone;
    private String address;
    private String company;
}
