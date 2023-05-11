package com.hrp.rabbitmq.model;

import com.hrp.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegisterEmployeeModel implements Serializable {
    private String email;
    private String password;
    private ERole role;
}
