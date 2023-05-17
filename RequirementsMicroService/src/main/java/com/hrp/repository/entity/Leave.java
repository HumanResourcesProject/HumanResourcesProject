package com.hrp.repository.entity;

import com.hrp.repository.entity.enums.LeaveType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_leave")
public class Leave extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeId;
    private Long authId;
    private Long managerId;
    private String company;
    @Enumerated(EnumType.STRING)
    private LeaveType type;
    private String requestDate;
    private String startDate;
    private String finishDate;
    private String approvalDate;
    private int status;
    private int amountOfDay;

}
