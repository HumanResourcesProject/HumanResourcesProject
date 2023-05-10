package com.hrp.mapper;


import com.hrp.dto.request.RegisterAdminRequestDto;
import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);

    RegisterAdminRequestDto toRegisterAdminRequestDto(final ModelRegisterAdmin model);
    Auth toAuth(final RegisterAdminRequestDto dto);
}
