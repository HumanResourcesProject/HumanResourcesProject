package com.hrp.rabbitmq.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ModelRegisterAdmin implements Serializable {
    private Long authId;
    private String name;
    private String surname;
    private String email;
    private String phone;
    private String address;
    private String avatar;
}
