package com.hrp.dto.response;
import com.hrp.repository.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class BaseManagerResponseDto extends BaseEntity {
    private String name;
    private String middleName;
    private String surname;
    private String birthDate;
    private String email;
    private String address;
    private String phone;
    private String company;
    private String occupation;
    private String department;
    private String jobStart;
    private String jobEnd;
    private String avatar;
}