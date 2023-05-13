package com.hrp.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(value= "tbl_employee")
public class Employee extends BaseEntity{

    @Id
    private String id;
    private Long authId;
    private String identityNumber;
    private String name;
    private String middleName;
    private String surname;
    private String birthDate;
    private String birthPlace;
    private String jobStart;
    private String Occupation;
    private String Department;
    private String email;
    private String phone;
    private String address;
    private String company;
    private LocalDateTime jobEnd;
    private String avatar;



}
