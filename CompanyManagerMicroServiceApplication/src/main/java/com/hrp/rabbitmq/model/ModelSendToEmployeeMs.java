package com.hrp.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelSendToEmployeeMs implements Serializable {
    private String identityNumber;
    private String name;
    private String middleName;
    private String surname;
    private String dateOfBirth;
    private String placeOfBirth;
    private String email;
    private String address;
    private String phone;
    private String company;
    private String job;
    private String department;
    private String jobStart;
    private String  avatar;
}
