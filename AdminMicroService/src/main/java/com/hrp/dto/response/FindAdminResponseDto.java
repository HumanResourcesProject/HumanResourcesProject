package com.hrp.dto.response;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FindAdminResponseDto {
    private String name;
    private String surname;
    private String email;
    private String avatar;
    private String phone;
    private String address;

}
