package com.hrp.repository.entity;

import com.hrp.repository.entity.enums.ERole;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name =  "tblauth")
@ToString
public class Auth extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private ERole role;

}
