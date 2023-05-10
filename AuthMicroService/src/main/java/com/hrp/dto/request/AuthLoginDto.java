package com.hrp.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthLoginDto {
    private String email;
    private String password;
    private String role;
}
