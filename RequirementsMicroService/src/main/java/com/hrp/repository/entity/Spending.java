package com.hrp.repository.entity;

import com.hrp.repository.entity.enums.SpendingCurrency;
import com.hrp.repository.entity.enums.SpendingType;
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
@Table(name = "tbl_spending")
public class Spending extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String employeeId;
    private Long managerId;
    private String company; // sirket
    private Long amount; // tutar
    @Enumerated(EnumType.STRING)
    private SpendingCurrency currency; // para birimi.dolar/TL/euro/dinar vs
    @Enumerated(EnumType.STRING)
    private SpendingType type; //Gida,yol vs.
    private int status; // durum. bekliyor/onaylandı/reddedildi
    private String spendingDate; // harcama tarihi
    private String requestDate; // talep tarihi
    private String approvalDate; // cevap tarihi
    private String invoiceUrl; // fatura url'si
}
