package com.hrp.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private String avatar;
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
    private Long salary;
}
