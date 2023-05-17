package com.hrp.rabbitmq.model;

import lombok.*;

import java.io.Serializable;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModelEmployeeExpense implements Serializable {
    private String employeeId;
    private Long amount;
    private String currency;
    private String type;
    private String invoiceUrl;
    private String spendingDate; // harcama tarihi
    private String requestDate;
    private String company;
    private String comment;
    private Long managerId;
    private Long authId;


}
