package com.hrp.dto.response;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseExpenseResponseDto {
    private Long expenseId;
    private String employeeId;
    private Long managerId;
    private Long authId;
    private String company;
    private Long amount;
    private String currency;
    private String type;
    private String status;
    private String spendingDate;
    private String requestDate;
    private String approvalDate;
    private String invoiceUrl;
    private String employeeName;
    private String employeeSurname;
}
