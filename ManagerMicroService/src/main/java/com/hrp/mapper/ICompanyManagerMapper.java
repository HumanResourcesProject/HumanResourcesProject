package com.hrp.mapper;

import com.hrp.repository.entity.Manager;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ICompanyManagerMapper {
    ICompanyManagerMapper INSTANCE =  Mappers.getMapper(ICompanyManagerMapper.class);


//    CompanyManager toCompanyManager(final CreateCompanyManagerRequestDto dto);

}
