package com.hrp.dto.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdvancePaymentRequestDto {
    private String identityNumber;
    private Long amount;
    private String requestDate;

}
