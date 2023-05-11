package com.hrp.dto.response;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseCompanyResponseDto {
    private String name;
    private String unvan;
    private String phone;
    private String email;
    private String address;
    private String calisanSayisi;
    private String kurulusYili;
    private String logo;
}
