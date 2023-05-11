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
public class EmailAdminModel implements Serializable {
    private String email;
    private String activationCode;

}
