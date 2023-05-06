package com.hrp.mapper;


import com.hrp.dto.request.AuthLoginDto;
import com.hrp.dto.request.AuthRegisterRequestDto;
import com.hrp.repository.entity.Auth;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAuthMapper {
    IAuthMapper INSTANCE= Mappers.getMapper(IAuthMapper.class);

    Auth toAuth(final AuthRegisterRequestDto dto);


    Auth toAuth(final AuthLoginDto dto);

}
