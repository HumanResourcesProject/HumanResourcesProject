package com.hrp.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ModelRegisterEmployee implements Serializable {
    private Long authId;
    private String identityNumber;
    private Long managerId;
    private String name;
    private String middleName;
    private String surname;
    private String birthDate;
    private String birthPlace;
    private String jobStart;
    private String occupation;
    private String department;
    private String email;
    private String phone;
    private String address;
    private String company;
}
