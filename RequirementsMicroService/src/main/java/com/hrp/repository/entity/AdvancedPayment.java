package com.hrp.repository.entity;

import com.hrp.repository.entity.enums.ExpenseCurrency;
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
@Table(name = "tbl_advancedpayment")
public class AdvancedPayment extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeId;
    private Long authId;
    private Long managerId;
    private String employeeName;
    private String employeeSurname;
    private String company;
    private Long amount;
    @Enumerated(EnumType.STRING)
    private ExpenseCurrency currency;
    private String advancedPaymentDate;
    private String requestDate;
    private String approvalDate;
    private String comment;
    private int status;
}
