package com.hrp.rabbitmq.model;

import com.hrp.repository.entity.enums.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelTurnAllLeaveRequest implements Serializable {

    private String employeeId;
    private String type;
    private String requestDate;
    private String startDate;
    private String finishDate;
    private String approvalDate;
    private int status;


}
