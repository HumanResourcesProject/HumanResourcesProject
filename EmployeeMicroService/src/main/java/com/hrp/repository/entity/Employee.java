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
@Document(value= "employees")
public class Employee extends BaseEntity{

    @Id
    private String id;
    private String name;
    private String middleName;
    private String surname;
    private String dateOfBirth;
    private String placeOfBirth;
    private String identityNumber;
    private String status;
    private String job;
    private String department;
    private String email;
    private String address;
    private String phone;
    private String company;
    @Builder.Default
    private LocalDateTime jobStart = LocalDateTime.now();
    private LocalDateTime jobEnd;
    private String avatar;



}
