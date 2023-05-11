package com.hrp.mapper;

import com.hrp.dto.response.GetAllEmployeeResponseDto;
import com.hrp.repository.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring",unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface IEmployeeMapper {
    IEmployeeMapper INSTANCE = Mappers.getMapper(IEmployeeMapper.class);

    GetAllEmployeeResponseDto toGetAllEmployeeResponseDto(final Employee employee);
}
