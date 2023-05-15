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
@Table(name = "tbl_payment")
public class Spending extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long amount;
    private SpendingCurrency currency; // para birimi.dolar/TL/euro/dinar vs
    private SpendingType type; //Gida,yol vs.
    private int state; // durum. bekliyor/onaylandı/reddedildi
    private String requestDate; // talep tarihi
    private String approvalDate; // cevap tarihi
    private String invoiceUrl; // fatura url'si
}
