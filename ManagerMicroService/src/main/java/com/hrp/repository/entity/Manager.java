package com.hrp.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tbl_manager")
@ToString
public class Manager extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long authId;
    private String name;
    private String middleName;
    private String surname;
    private String birthDate;
    private String birthPlace;
    private String identityNumber;
    private String occupation;
    private String department;
    private String email;
    private String address;
    private String phone;
    private String company;
    private String jobStart;
    private String jobEnd;
    private String avatar;
}
