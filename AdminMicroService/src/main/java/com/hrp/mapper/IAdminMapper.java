package com.hrp.mapper;

import com.hrp.dto.request.CreateAdminRequestDto;
import com.hrp.dto.request.CreateCompanyManagerRequestDto;
import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.rabbitmq.model.ModelRegisterCompanyManager;
import com.hrp.rabbitmq.model.ModelSendToCompanyManager;
import com.hrp.repository.entity.Admin;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IAdminMapper {

        IAdminMapper INSTANCE= Mappers.getMapper(IAdminMapper.class);


        @Mapping(target="avatar", ignore = true)
        ModelSendToCompanyManager toModelSendToCompanyManager(final CreateCompanyManagerRequestDto dto);

    }
