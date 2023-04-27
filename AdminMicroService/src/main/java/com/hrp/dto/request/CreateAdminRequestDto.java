package com.hrp.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateAdminRequestDto {
    private String username;
    private String name;
    private String surname;
    private String email;
    private String password;
}
