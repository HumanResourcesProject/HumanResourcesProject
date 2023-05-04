package com.hrp.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UpdateAdminRequestDto {
    private Long id;
    private String phone;
    private String address;
}
