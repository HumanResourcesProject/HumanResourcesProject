package com.hrp.mapper;

import com.hrp.dto.response.BaseAdminResponseDto;
import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.repository.entity.Admin;
import org.springframework.stereotype.Component;

@Component
public class ManuelAdminMapper implements IManuelAdminMapper {
    public Admin toAdmin(ModelRegisterAdmin model){
        Admin admin = new Admin();
        admin.setEmail(model.getEmail());
        admin.setName(model.getName());
        admin.setPhone(model.getPhone());
        admin.setAddress(model.getAddress());
        admin.setSurname(model.getSurname());
        admin.setAuthId(model.getAuthId());
        admin.setAvatar(model.getAvatar());
        return admin;
    }

    @Override
    public BaseAdminResponseDto toBaseResponseDto(Admin admin) {
        BaseAdminResponseDto dto = new BaseAdminResponseDto();
        dto.setAddress(admin.getAddress());
        dto.setAvatar(admin.getAvatar());
        dto.setEmail(admin.getEmail());
        dto.setName(admin.getName());
        dto.setPhone(admin.getPhone());
        dto.setSurname(admin.getSurname());
        return dto;
    }


}
