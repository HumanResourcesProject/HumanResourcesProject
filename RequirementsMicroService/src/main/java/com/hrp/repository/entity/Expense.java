package com.hrp.repository.entity;

import com.hrp.repository.entity.enums.ExpenseCurrency;
import com.hrp.repository.entity.enums.ExpenseType;
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
@Table(name = "tbl_expense")
public class Expense extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeId;
    private Long managerId;
    private Long authId;
    private String employeeName;
    private String employeeSurname;

    private String company; // sirket
    private Long amount; // tutar
    @Enumerated(EnumType.STRING)
    private ExpenseCurrency currency; // para birimi.dolar/TL/euro/dinar vs
    @Enumerated(EnumType.STRING)
    private ExpenseType type; //Gida,yol vs.
    private int status; // durum. bekliyor/onaylandı/reddedildi
    private String spendingDate; // harcama tarihi
    private String requestDate; // talep tarihi
    private String approvalDate; // cevap tarihi
    private String invoiceUrl; // fatura url'si
}
