package com.hrp.mapper;

import com.hrp.dto.request.UpdateManagerRequestDto;
import com.hrp.dto.response.BaseManagerResponseDto;
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
        baseManagerResponseDto.setJobEnd(manager.getJobEnd());
        baseManagerResponseDto.setAvatar(manager.getAvatar());
        return baseManagerResponseDto;
    }

    @Override
    public Manager toManager(Manager manager,UpdateManagerRequestDto dto) {
        manager.setEmail(dto.getEmail());
        manager.setAddress(dto.getAddress());
        manager.setPhone(dto.getPhone());
        manager.setCompany(dto.getCompany());
        manager.setJobEnd(dto.getJobEnd());
        manager.setAvatar(dto.getAvatar());
        return manager;
    }
}
