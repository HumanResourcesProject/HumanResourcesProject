package com.hrp.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateManagerRequestDto {
    private String token;
    private String password;
    private String email;
    private String address;
    private String phone;
    private String company;
    private String jobEnd;
    private String avatar;
}
