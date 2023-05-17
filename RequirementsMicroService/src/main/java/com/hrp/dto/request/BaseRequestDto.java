package com.hrp.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseRequestDto {
    private String token; // auth idyi bulucak
    private Long managerId;
    private String employeeId;
    private String company;

}
