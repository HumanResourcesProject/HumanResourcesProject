package com.hrp.dto.response;

import com.hrp.repository.entity.enums.LeaveType;
import lombok.*;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BaseLeaveResponseDto {
    private Long leaveId;
    private String employeeId;
    private Long authId;
    private Long managerId;
    private String company;
    private String type;
    private String requestDate;
    private String startDate;
    private String finishDate;
    private String approvalDate;
    private String status;
    private int amountOfDay;
    private String employeeName;
    private String employeeSurname;
}
