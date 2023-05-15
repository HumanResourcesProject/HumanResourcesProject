package com.hrp.mapper;

import com.hrp.dto.response.BaseEmployeeResponseDto;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.repository.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class ManuelEmployeeMapper implements IManuelEmployeeMapper {

    public Employee modelToEmployee(ModelRegisterEmployee model){
        return Employee.builder()
                .authId(model.getAuthId())
                .identityNumber(model.getIdentityNumber())
                .name(model.getName())
                .middleName(model.getMiddleName())
                .surname(model.getSurname())
                .birthDate(model.getBirthDate())
                .birthPlace(model.getBirthPlace())
                .jobStart(model.getJobStart())
                .Occupation(model.getOccupation())
                .Department(model.getDepartment())
                .email(model.getEmail())
                .phone(model.getPhone())
                .address(model.getAddress())
                .company(model.getCompany())
                .build();
    }
//    String name;
//    String middleName;
//    String surname;
//    String birthDate;
//    String birthPlace;
//    String identityNumber;
//    String Occupation;
//    String department;
//    String email;
//    String address;
//    String phone;
//    String company;
//    String avatar;
    @Override
    public BaseEmployeeResponseDto toBaseEmployeeDto(Employee employee) {
        BaseEmployeeResponseDto baseEmployeeResponseDto = new BaseEmployeeResponseDto();
        baseEmployeeResponseDto.setName(employee.getName());
        baseEmployeeResponseDto.setMiddleName(employee.getMiddleName());
        baseEmployeeResponseDto.setSurname(employee.getSurname());
        baseEmployeeResponseDto.setBirthDate(employee.getBirthDate());
        baseEmployeeResponseDto.setBirthPlace(employee.getBirthPlace());
        baseEmployeeResponseDto.setIdentityNumber(employee.getIdentityNumber());
        baseEmployeeResponseDto.setOccupation(employee.getOccupation());
        baseEmployeeResponseDto.setDepartment(employee.getDepartment());
        baseEmployeeResponseDto.setEmail(employee.getEmail());
        baseEmployeeResponseDto.setAddress(employee.getAddress());
        baseEmployeeResponseDto.setPhone(employee.getPhone());
        baseEmployeeResponseDto.setCompany(employee.getCompany());
        baseEmployeeResponseDto.setAvatar(employee.getAvatar());
        return baseEmployeeResponseDto;
    }
}
