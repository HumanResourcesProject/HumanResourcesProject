package com.hrp.mapper;

import com.hrp.dto.request.CreateCompanyRequestDto;
import com.hrp.dto.response.BaseCompanyResponseDto;
import com.hrp.repository.entity.Company;

public interface IManuelCompanyMapper {
    Company toCompany(CreateCompanyRequestDto dto);
    BaseCompanyResponseDto toCompanyResponseDto(Company company);
}
