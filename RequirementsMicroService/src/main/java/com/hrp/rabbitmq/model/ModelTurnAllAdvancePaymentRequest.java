package com.hrp.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelTurnAllAdvancePaymentRequest implements Serializable {
    private String employeeId;
    private Long managerId;
    private String company;
    private Long amount;
    private String currency;
    private String advancedPaymentDate;
    private String requestDate;
    private String approvalDate;
    private String comment;
    private int status;
}
