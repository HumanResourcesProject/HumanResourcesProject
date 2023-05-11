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
public class CreateAdminRequestDto {
    @NotNull(message = "name bos gecilemez")
    private String name;
    @NotNull(message = "surname bos gecilemez")
    private String surname;
    @NotNull
    @Pattern(regexp = "\\b[\\w.%-]+@gmail\\.com\\b", message = "Geçerli bir Gmail adresi girin.")
    private String email;
    @Pattern(regexp = "^[0-9]*$", message = "Telefon numarası sadece sayı içermelidir.")
    @NotNull(message = "telefon bos gecilemez")
    private String phone;
    @NotNull(message = "address bos gecilemez")
    private String address;
    @NotNull(message = "avatar bos gecilemez")
    private MultipartFile avatar;

}
