package com.hrp.dto.request.requirements;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AdvancePaymentRequestDto {
    private String token;
    private Long amount;
    private String currency; // TL,EURO,DOLAR VS
    private String comment;
    private String advancedPaymentDate;


}
