package com.hrp.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ModelEmployeeLeave implements Serializable {
    private String employeeId;
    private String type;
    private String startDate;
    private String finishDate;
    private String company;
    private int amountOfDay;
    private Long managerId;
    private Long authId;



}
