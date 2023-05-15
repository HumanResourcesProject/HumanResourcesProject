package com.hrp.mapper;

import com.hrp.dto.response.BaseAdminResponseDto;
import com.hrp.repository.entity.Admin;


public interface IManuelAdminMapper {
    Admin toAdmin(ModelRegisterAdmin model);
    BaseAdminResponseDto toBaseResponseDto(Admin admin);

}
