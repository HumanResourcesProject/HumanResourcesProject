package com.hrp.dto.request;

import lombok.*;
import okhttp3.RequestBody;

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
