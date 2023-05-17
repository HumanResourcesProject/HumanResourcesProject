package com.hrp.mapper;

import com.hrp.dto.request.UpdateManagerRequestDto;
import com.hrp.dto.response.BaseManagerResponseDto;
import com.hrp.dto.response.EmployeeRequestAndResponseDto;
import com.hrp.rabbitmq.model.ModelBaseEmployee;
import com.hrp.rabbitmq.model.ModelRegisterManager;
import com.hrp.repository.entity.Manager;
import org.springframework.stereotype.Component;

@Component
public class ManuelManagerMapper implements IManuelManagerMapper {

    public Manager toManager(ModelRegisterManager model){
        return Manager.builder()
                .authId(model.getAuthId())
                .identityNumber(model.getIdentityNumber())
                .name(model.getName())
                .middleName(model.getMiddleName())
                .surname(model.getSurname())
                .birthDate(model.getBirthDate())
                .birthPlace(model.getBirthPlace())
                .jobStart(model.getJobStart())
                .occupation(model.getOccupation())
                .department(model.getDepartment())
                .email(model.getEmail())
                .phone(model.getPhone())
                .address(model.getAddress())
                .company(model.getCompany())
                .build();

    }

    @Override
    public BaseManagerResponseDto toBaseManagerResponseDto(Manager manager) {
        BaseManagerResponseDto baseManagerResponseDto= new BaseManagerResponseDto();
        baseManagerResponseDto.setName(manager.getName());
        baseManagerResponseDto.setMiddleName(manager.getMiddleName());
        baseManagerResponseDto.setSurname(manager.getSurname());
        baseManagerResponseDto.setBirthDate(manager.getBirthDate());
        baseManagerResponseDto.setEmail(manager.getEmail());
        baseManagerResponseDto.setAddress(manager.getAddress());
        baseManagerResponseDto.setPhone(manager.getPhone());
        baseManagerResponseDto.setCompany(manager.getCompany());
        baseManagerResponseDto.setOccupation(manager.getOccupation());
        baseManagerResponseDto.setDepartment(manager.getDepartment());
        baseManagerResponseDto.setJobStart(manager.getJobStart());
        baseManagerResponseDto.setAvatar(manager.getAvatar());
        return baseManagerResponseDto;
    }

    @Override
    public Manager toManager(Manager manager,UpdateManagerRequestDto dto) {
        manager.setEmail(dto.getEmail());
        manager.setAddress(dto.getAddress());
        manager.setPhone(dto.getPhone());
        manager.setCompany(dto.getCompany());
        return manager;
    }

    @Override
    public ModelBaseEmployee toModelBaseEmployee(Manager manager) {
        ModelBaseEmployee model= new ModelBaseEmployee();
        model.setAuthId(manager.getAuthId());
        model.setManagerId(manager.getId());
        model.setCompany(manager.getCompany());
        return model;
    }

    @Override
    public EmployeeRequestAndResponseDto toEmployeeDto(ModelBaseEmployee model) {
        EmployeeRequestAndResponseDto dto = new EmployeeRequestAndResponseDto();
        dto.setAuthId(model.getAuthId());
        dto.setManagerId(model.getManagerId());
        dto.setCompany(model.getCompany());
        dto.setIdentityNumber(model.getIdentityNumber());
        dto.setName(model.getName());
        dto.setMiddleName(model.getMiddleName());
        dto.setSurname(model.getSurname());
        dto.setBirthDate(model.getBirthDate());
        dto.setBirthPlace(model.getBirthPlace());
        dto.setJobStart(model.getJobStart());
        dto.setOccupation(model.getOccupation());
        dto.setDepartment(model.getDepartment());
        dto.setEmail(model.getEmail());
        dto.setPhone(model.getPhone());
        dto.setAddress(model.getAddress());
        dto.setAvatar(model.getAvatar());
        return dto;
    }
}
