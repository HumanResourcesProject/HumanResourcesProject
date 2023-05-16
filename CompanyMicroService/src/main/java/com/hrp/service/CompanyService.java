package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrp.dto.request.CreateCompanyRequestDto;
import com.hrp.dto.request.TokenDto;
import com.hrp.dto.response.BaseCompanyResponseDto;
import com.hrp.mapper.IManuelCompanyMapper;
import com.hrp.repository.ICompanyRepository;
import com.hrp.repository.entity.Company;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.List;

@Service
public class CompanyService extends ServiceManagerImpl<Company, Long> {
    private final ICompanyRepository companyRepository;
    private final IManuelCompanyMapper iManuelCompanyMapper;
    private final JwtTokenManager  jwtTokenService;
    public CompanyService(ICompanyRepository companyRepository, IManuelCompanyMapper iManuelCompanyMapper, JwtTokenManager jwtTokenService) {
        super(companyRepository);
        this.companyRepository = companyRepository;
        this.iManuelCompanyMapper = iManuelCompanyMapper;
        this.jwtTokenService = jwtTokenService;
    }

    @Transactional
    public Boolean createCompany(CreateCompanyRequestDto dto)  {
        String avatarUrl = toTurnStringAvatar(dto.getLogo());
        Company company= iManuelCompanyMapper.toCompany(dto);
        company.setLogo(avatarUrl);
        save(company);
        return true;
    }

    //find all
    public List<BaseCompanyResponseDto> findAllDto(TokenDto dto){
        Optional<Long> id = jwtTokenService.validToken(dto.getToken());
        Optional<List<BaseCompanyResponseDto>> companiesDto = Optional.of( new ArrayList<>());
        for (Company company : findAll()){
            companiesDto.get().add(iManuelCompanyMapper.toCompanyResponseDto(company));
        }
        return companiesDto.get();
    }


    private String toTurnStringAvatar(MultipartFile avatar) {
        Map config = new HashMap();
        config.put("cloud_name", "doqksh0xh");
        config.put("api_key", "871216635594134");
        config.put("api_secret", "6b3zcRZyWKeuiW6qIq4XvWnhVno");
        Cloudinary cloudinary = new Cloudinary(config);
        try{
            Map<?, ?> result = cloudinary.uploader().upload(avatar.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            System.out.println(url+" --------------------------");
            return url;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }




}