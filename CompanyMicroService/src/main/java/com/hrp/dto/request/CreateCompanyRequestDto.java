package com.hrp.dto.request;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CreateCompanyRequestDto {
    private String name;
    private String unvan;
    private String phone;
    private String email;
    private String address;
    private String calisanSayisi;
    private String kurulusYili;
    private String vergiDairesi;
    private String vergiNo;
    private String mersisNo;
    @NotNull(message = "logo bos gecilemez")
    private MultipartFile logo;

}
