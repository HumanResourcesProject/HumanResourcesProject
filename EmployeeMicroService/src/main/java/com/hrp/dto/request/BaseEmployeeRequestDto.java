package com.hrp.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseEmployeeRequestDto {
    private String token;
    private String role;
}
