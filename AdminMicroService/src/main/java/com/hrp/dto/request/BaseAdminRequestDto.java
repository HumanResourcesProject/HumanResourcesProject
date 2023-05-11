package com.hrp.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseAdminRequestDto {
    private String token;
    private String phone;
    private String address;
    private String email;


}
