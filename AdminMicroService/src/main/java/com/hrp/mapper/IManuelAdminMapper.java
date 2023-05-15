package com.hrp.mapper;

import com.hrp.dto.response.BaseAdminResponseDto;
import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.repository.entity.Admin;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


public interface IManuelAdminMapper {
    Admin toAdmin(ModelRegisterAdmin model);
    BaseAdminResponseDto toBaseResponseDto(Admin admin);

}
