package com.hrp.mapper;

import com.hrp.dto.request.RegisterAdminRequestDto;
import com.hrp.dto.request.RegisterEmployeeRequestDto;
import com.hrp.dto.request.RegisterManagerRequestDto;
import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.rabbitmq.model.ModelRegisterEmployee;
import com.hrp.rabbitmq.model.ModelRegisterManager;
import com.hrp.repository.entity.Auth;
import org.springframework.stereotype.Component;
@Component
public class ManuelMapper implements IManuelMapper {
    public ModelRegisterAdmin authToModelRegisterAdmin(Auth auth, RegisterAdminRequestDto dto) {
        ModelRegisterAdmin modelRegisterAdmin = new ModelRegisterAdmin();
        modelRegisterAdmin.setAuthId(auth.getId());
        modelRegisterAdmin.setName(dto.getName());
        modelRegisterAdmin.setSurname(dto.getSurname());
        modelRegisterAdmin.setEmail(auth.getEmail());
        modelRegisterAdmin.setPhone(dto.getPhone());
        modelRegisterAdmin.setAddress(dto.getAddress());
        return modelRegisterAdmin;
    }
    public ModelRegisterManager  authToModelRegisterManager(Auth auth, RegisterManagerRequestDto dto) {
        ModelRegisterManager modelRegisterManager = new ModelRegisterManager();
       modelRegisterManager.setAuthId(auth.getId());
        modelRegisterManager.setName(dto.getName());
        modelRegisterManager.setSurname(dto.getSurname());
        modelRegisterManager.setEmail(auth.getEmail());
        modelRegisterManager.setPhone(dto.getPhone());
        modelRegisterManager.setAddress(dto.getAddress());
        modelRegisterManager.setCompany(dto.getCompany());
        modelRegisterManager.setDepartment(dto.getDepartment());
        modelRegisterManager.setBirthDate(dto.getBirthDate());
        modelRegisterManager.setBirthPlace(dto.getBirthPlace());
        modelRegisterManager.setIdentityNumber(dto.getIdentityNumber());
        modelRegisterManager.setMiddleName(dto.getMiddleName());
        modelRegisterManager.setOccupation(dto.getOccupation());
        modelRegisterManager.setJobStart(dto.getJobStart());
        return modelRegisterManager;
    }
    public ModelRegisterEmployee authToModelRegisterEmployee(Auth auth, RegisterEmployeeRequestDto dto) {
        ModelRegisterEmployee modelRegisterEmployee = new ModelRegisterEmployee();
        modelRegisterEmployee.setAuthId(auth.getId());
        modelRegisterEmployee.setName(dto.getName());
        modelRegisterEmployee.setSurname(dto.getSurname());
        modelRegisterEmployee.setEmail(auth.getEmail());
        modelRegisterEmployee.setPhone(dto.getPhone());
        modelRegisterEmployee.setAddress(dto.getAddress());
        modelRegisterEmployee.setCompany(dto.getCompany());
        modelRegisterEmployee.setDepartment(dto.getDepartment());
        modelRegisterEmployee.setBirthDate(dto.getBirthDate());
        modelRegisterEmployee.setBirthPlace(dto.getBirthPlace());
        modelRegisterEmployee.setIdentityNumber(dto.getIdentityNumber());
        modelRegisterEmployee.setMiddleName(dto.getMiddleName());
        modelRegisterEmployee.setOccupation(dto.getOccupation());
        modelRegisterEmployee.setJobStart(dto.getJobStart());
        return modelRegisterEmployee;
    }
}
