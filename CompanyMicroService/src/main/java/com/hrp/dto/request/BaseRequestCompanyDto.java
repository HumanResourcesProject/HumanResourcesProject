package com.hrp.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseRequestCompanyDto {
    private String token;
}
