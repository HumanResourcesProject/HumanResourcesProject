package com.hrp.mapper;

import com.hrp.dto.request.CreateCompanyManagerRequestDto;
import com.hrp.dto.request.UpdateCompanyManagerRequestDto;
import com.hrp.dto.response.CompanyManagerFindAllResponseDto;
import com.hrp.repository.entity.CompanyManager;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICompanyManagerMapper {
    ICompanyManagerMapper INSTANCE =  Mappers.getMapper(ICompanyManagerMapper.class);

    @Mapping(source = "id",target = "companyManagerId")
    CompanyManagerFindAllResponseDto toCompanyManagerFindAllResponseDto(final CompanyManager companyManager);

    CompanyManager toCompanyManager(final CreateCompanyManagerRequestDto dto);

}
