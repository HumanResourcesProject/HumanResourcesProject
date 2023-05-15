package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrp.dto.request.*;
import com.hrp.dto.response.BaseAdminResponseDto;
import com.hrp.mapper.IManuelAdminMapper;
import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.repository.IPermissionRepository;
import com.hrp.repository.entity.Admin;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class AdminService extends ServiceManagerImpl<Admin, Long> {

    private final IPermissionRepository adminRepository;
    private final JwtTokenManager jwtTokenManager;
    private final IManuelAdminMapper iManuelAdminMapper;
    private final AdminAuthService adminAuthService;
    public AdminService(IPermissionRepository adminRepository, JwtTokenManager jwtTokenManager, IManuelAdminMapper iManuelAdminMapper, AdminAuthService adminAuthService) {
        super(adminRepository);
        this.adminRepository = adminRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.iManuelAdminMapper = iManuelAdminMapper;
        this.adminAuthService = adminAuthService;
    }

    public void adminRegister(ModelRegisterAdmin model){
        Admin admin = iManuelAdminMapper.toAdmin(model);
        save(admin);
        System.out.println("admin register ici admin idsi: "+admin.getId());
        adminAuthService.createAdminAuth(admin.getId());
    }



    public BaseAdminResponseDto findMe(TokenDto dto){
        Long id = jwtTokenManager.validToken(dto.getToken()).get();
        Admin admin = adminRepository.findById(id).get();
        return iManuelAdminMapper.toBaseResponseDto(admin);
    }


    public String updateImage(MultipartFile file, String token) {
        Long id = jwtTokenManager.validToken(token).get();
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isEmpty()){
            System.out.println("Kullanici bulunamadi");
        }
       String url = toTurnStringAvatar(file);
        return url;
    }


    // bos gelirse db de ki alınacak
    public Boolean updateAdmin(BaseAdminRequestDto dto) {
        System.out.println("dto ici update... "+ dto.toString());
        Long id = jwtTokenManager.validToken(dto.getToken()).get();
        Optional<Admin> admin = adminRepository.findById(id);
        admin.get().setAddress(dto.getAddress());
        admin.get().setPhone(dto.getPhone());
        update(admin.get());
        return true;
    }

    // findalladmin
    public List<BaseAdminResponseDto> findAllAdmin() {
        System.out.println("findall admin service");
        List<BaseAdminResponseDto> baseAdminResponseDtos = new ArrayList<>();
        for (Admin admin : adminRepository.findAll()) {
            baseAdminResponseDtos.add(iManuelAdminMapper.toBaseResponseDto(admin));
        }
        return baseAdminResponseDtos;
    }


    private String toTurnStringAvatar(MultipartFile file) {
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

    public Optional<Admin> findByAuthId(Long authId) {
      return adminRepository.findOptionalByAuthId(authId);
    }

}