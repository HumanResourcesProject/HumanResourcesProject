package com.hrp.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelEmployeeAdvancePaymentRequest implements Serializable {
    private String employeeId;
    private Long amount;
    private String company;
    private String comment;
    private String currency;


}
