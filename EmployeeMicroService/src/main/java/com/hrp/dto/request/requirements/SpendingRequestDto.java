package com.hrp.dto.request.requirements;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SpendingRequestDto {
    /**
     * burasi hazir ama sadece burasi ne controller ne servis ne de rabbit
     * burasi haric hicbir yer hazir degil
     */
    private String token;
    private Long amount; // harcama tutari
    private String currency; // USD, EUR, RUB
    private String type; // FOOD, CLOTHES, TRANSPORT etc.
    private String spendingDate; //harcama tarihi
    private MultipartFile invoiceUrl; // fatura url

}
