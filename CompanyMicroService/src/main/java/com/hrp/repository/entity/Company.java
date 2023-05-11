package com.hrp.repository.entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Document(value= "employees")
public class Company extends BaseEntity {
    @Id
    private String id;
    private String name;
    private String unvan;
    private String phone;
    private String email;
    private String address;
    private String calisanSayisi;
    private String kurulusYili;
    private String vergiDairesi;
    private String vergiNo;
    private String mersisNo;
    private String logo;
    private String website;
}
