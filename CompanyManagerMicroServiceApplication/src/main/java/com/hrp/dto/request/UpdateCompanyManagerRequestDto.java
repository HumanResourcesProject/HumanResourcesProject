package com.hrp.dto.request;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateCompanyManagerRequestDto {
    private String token;
    private String password;
    private String email;
    private String address;
    private String phone;
    private String company;
    private LocalDateTime jobEnd;
    private String avatar;
}
