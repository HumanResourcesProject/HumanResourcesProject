package com.hrp.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ExpenseRequestDto {
    private String token;
    private Long amount;
    private String spendingDate; // harcama tarihi
    private String requestDate;
    private String currency;
    private String type;
    private String comment;
    private String invoiceUrl; // fatura url'si
//    MultipartFile faturaninFotografi;
}
