package com.hrp.rabbitmq.model;

import com.hrp.repository.entity.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelRegisterManager implements Serializable {
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
}
