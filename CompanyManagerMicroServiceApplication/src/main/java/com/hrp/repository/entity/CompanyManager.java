package com.hrp.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_companymanager")
@ToString
public class CompanyManager extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String middleName;
    private String surname;
    private String password;
    private String dateOfBirth;
    private String placeOfBirth;
    private String identityNumber;
    private String job;
    private String department;
    private String email;
    private String address;
    private String phone;
    private String company;
    private String jobStart;
    private String jobEnd;
    private String avatar;
}
