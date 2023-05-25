package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrp.dto.request.*;
import com.hrp.dto.response.BaseAdminResponseDto;
import com.hrp.exception.AdminException;
import com.hrp.exception.EErrorType;
import com.hrp.mapper.IManuelAdminMapper;
import com.hrp.rabbitmq.model.ModelRegisterAdmin;
import com.hrp.repository.IAdminRepository;
import com.hrp.repository.entity.Admin;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.webjars.NotFoundException;

import java.util.*;
import java.util.logging.Logger;

@Service
public class AdminService extends ServiceManagerImpl<Admin, Long> {

    private final IAdminRepository adminRepository;
    private final JwtTokenManager jwtTokenManager;
    private final IManuelAdminMapper iManuelAdminMapper;
    private final AdminAuthService adminAuthService;

    public AdminService(IAdminRepository adminRepository, JwtTokenManager jwtTokenManager, IManuelAdminMapper iManuelAdminMapper, AdminAuthService adminAuthService) {
        super(adminRepository);
        this.adminRepository = adminRepository;
        this.jwtTokenManager = jwtTokenManager;
        this.iManuelAdminMapper = iManuelAdminMapper;
        this.adminAuthService = adminAuthService;
    }

    public void adminRegister(ModelRegisterAdmin model) {
        Admin admin = iManuelAdminMapper.toAdmin(model);
        save(admin);
        adminAuthService.createAdminAuth(admin.getId());
    }

    public List<BaseAdminResponseDto> findAllAdmin() {
        Logger.getLogger("My Message log mesajı");
        List<BaseAdminResponseDto> baseAdminResponseDtos = new ArrayList<>();
        for (Admin admin : adminRepository.findAll()) {
            baseAdminResponseDtos.add(iManuelAdminMapper.toBaseResponseDto(admin));
        }
        return baseAdminResponseDtos;
    }


    public BaseAdminResponseDto findMe(TokenDto dto) {
        Optional<Long> id = jwtTokenManager.validToken(dto.getToken());
        if (id.isEmpty()) {
            throw new NotFoundException("admin bulunamadi");
        }
        Long authId2 = id.get();

        Admin admin = adminRepository.findOptionalByAuthId(authId2).get();
        return iManuelAdminMapper.toBaseResponseDto(admin);
    }


    public String updateImage(MultipartFile file, String token) {
        Long id = jwtTokenManager.validToken(token).get();
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isEmpty()) {
            throw new AdminException(EErrorType.ADMIN_NOT_FOUND);
        }
        String url = toTurnStringAvatar(file);
        return url;
    }


    // bos gelirse db de ki alınacak
    public Boolean updateAdmin(BaseAdminRequestDto dto) {
        Long id = jwtTokenManager.validToken(dto.getToken()).get();
        Optional<Admin> admin = adminRepository.findById(id);
        admin.get().setAddress(dto.getAddress());
        admin.get().setPhone(dto.getPhone());
        String avatarUrl;
             avatarUrl= toTurnStringAvatar(dto.getAvatar());
        try {
            Thread.sleep(1400);
            admin.get().setAvatar(avatarUrl);
            update(admin.get());
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return true;
    }

    // findalladmin


    private String toTurnStringAvatar(MultipartFile file) {
        Map<String,String> config = new HashMap<>();
        config.put("cloud_name", "doqksh0xh");
        config.put("api_key", "871216635594134");
        config.put("api_secret", "6b3zcRZyWKeuiW6qIq4XvWnhVno");
        Cloudinary cloudinary = new Cloudinary(config);
        try {
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Optional<Admin> findByAuthId(Long authId) {
        return adminRepository.findOptionalByAuthId(authId);
    }

}