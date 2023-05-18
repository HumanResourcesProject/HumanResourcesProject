package com.hrp.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseAdvancePaymentResponseDto {
    private Long advancePaymentId;
    private String employeeId;
    private Long authId;
    private Long managerId;
    private String company;
    private Long amount;
    private String currency;
    private String advancedPaymentDate;
    private String requestDate;
    private String approvalDate;
    private String comment;
    private String status;
    private String employeeName;
    private String employeeSurname;
}
