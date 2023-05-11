package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrp.dto.request.CreateCompanyRequestDto;
import com.hrp.dto.response.BaseCompanyResponseDto;
import com.hrp.exception.CompanyException;
import com.hrp.exception.EErrorType;
import com.hrp.mapper.ICompanyMapper;
import com.hrp.repository.ICompanyRepository;
import com.hrp.repository.entity.Company;
import com.hrp.utility.CodeGenerator;
import com.hrp.utility.ServiceManagerImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.List;

@Service
public class CompanyService extends ServiceManagerImpl<Company, Long> {
    private final ICompanyRepository companyRepository;
    public CompanyService(ICompanyRepository companyRepository) {
        super(companyRepository);
        this.companyRepository = companyRepository;
    }

    @Transactional
    public Boolean createCompany(CreateCompanyRequestDto dto)  {
        if (StringUtils.isEmpty(dto.getName())
                || StringUtils.isEmpty(dto.getEmail()) ){
            throw new CompanyException(EErrorType.PASSWORD_NOT_EMPTY);
        }
        String avatarUrl = uploadImageCloudMft(dto.getLogo());
        Company company = ICompanyMapper.INSTANCE.toCompany(dto);


        save(company.builder()
                .logo(avatarUrl)
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .email(dto.getEmail())
                .name(dto.getName())
                .calisanSayisi(dto.getCalisanSayisi())
                .mersisNo(dto.getMersisNo())
                .vergiDairesi(dto.getVergiDairesi())
                .vergiNo(dto.getVergiNo())
                .unvan(dto.getUnvan())
                .kurulusYili(dto.getKurulusYili())
                .build());
        return true;
    }

    public String uploadImageCloudMft(MultipartFile file) {

        Map config = new HashMap();
        config.put("cloud_name", "doqksh0xh");
        config.put("api_key", "871216635594134");
        config.put("api_secret", "6b3zcRZyWKeuiW6qIq4XvWnhVno");
        Cloudinary cloudinary = new Cloudinary(config);
        try{
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            System.out.println(url+" --------------------------");
            return url;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    //find all
    public List<BaseCompanyResponseDto> findAllDto(){
        Optional<List<BaseCompanyResponseDto>> companiesDto = Optional.of( new ArrayList<>());
        for (Company company : findAll()){
            companiesDto.get().add(ICompanyMapper.INSTANCE.baseCompanyResponseDto(company));
        }
        return companiesDto.get();
    }






}