package com.hrp.dto.response;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseAdminResponseDto {
    private String name;
    private String surname;
    private String email;
    private String password;
    private String avatar;
    private String phone;
    private String address;
}
