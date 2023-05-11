package com.hrp.mapper;

import com.hrp.dto.request.CreateCompanyRequestDto;
import com.hrp.dto.response.BaseCompanyResponseDto;
import com.hrp.repository.entity.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICompanyMapper {

        ICompanyMapper INSTANCE= Mappers.getMapper(ICompanyMapper.class);

        @Mapping(target="logo", ignore = true)
        Company toCompany(final CreateCompanyRequestDto dto);
    BaseCompanyResponseDto baseCompanyResponseDto (final Company company);

    }
