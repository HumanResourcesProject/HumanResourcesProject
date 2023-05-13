package com.hrp.mapper;

import com.hrp.dto.request.CreateCompanyRequestDto;
import com.hrp.dto.response.BaseCompanyResponseDto;
import com.hrp.repository.entity.Company;
import org.springframework.stereotype.Component;

@Component
public class ManuelCompanyMapper implements IManuelCompanyMapper{

    @Override
    public Company toCompany(CreateCompanyRequestDto dto) {
        Company company = new Company();
        company.setName(dto.getName());
        company.setUnvan(dto.getUnvan());
        company.setPhone(dto.getPhone());
        company.setEmail(dto.getEmail());
        company.setAddress(dto.getAddress());
        company.setCalisanSayisi(dto.getCalisanSayisi());
        company.setKurulusYili(dto.getKurulusYili());
        company.setVergiDairesi(dto.getVergiDairesi());
        company.setVergiNo(dto.getVergiNo());
        company.setMersisNo(dto.getMersisNo());
        return company;

    }

    @Override
    public BaseCompanyResponseDto toCompanyResponseDto(Company company) {
        BaseCompanyResponseDto baseCompanyResponseDto = new BaseCompanyResponseDto();
        baseCompanyResponseDto.setName(company.getName());
        baseCompanyResponseDto.setUnvan(company.getUnvan());
        baseCompanyResponseDto.setPhone(company.getPhone());
        baseCompanyResponseDto.setEmail(company.getEmail());
        baseCompanyResponseDto.setAddress(company.getAddress());
        baseCompanyResponseDto.setCalisanSayisi(company.getCalisanSayisi());
        baseCompanyResponseDto.setKurulusYili(company.getKurulusYili());
        baseCompanyResponseDto.setLogo(company.getLogo());
        return baseCompanyResponseDto;
    }

}
