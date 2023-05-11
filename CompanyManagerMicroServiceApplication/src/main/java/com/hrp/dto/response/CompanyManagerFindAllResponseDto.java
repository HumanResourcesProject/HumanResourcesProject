package com.hrp.dto.response;
import com.hrp.repository.entity.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class CompanyManagerFindAllResponseDto extends BaseEntity {
    private String name;
    private String middleName;
    private String surname;
    private String dateOfBirth;
    private String email;
    private String address;
    private String phone;
    private String company;
    private String job;
    private String department;
    private String jobStart;
    private String jobEnd;
    private String avatar;
}