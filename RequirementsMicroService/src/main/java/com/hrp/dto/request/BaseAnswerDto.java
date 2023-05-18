package com.hrp.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseAnswerDto {
    private String token;
    private Long requirementId;
}
