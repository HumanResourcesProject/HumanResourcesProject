package com.hrp.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelTurnAllLeaveRequest implements Serializable {

    private String employeeId;
    private Long managerId;
    private String company;
    private String type;
    private String requestDate;
    private String startDate;
    private String finishDate;
    private String approvalDate;
    private int status;
    private int amountOfDay;


}
