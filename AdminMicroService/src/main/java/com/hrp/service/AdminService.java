package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.dropbox.core.DbxRequestConfig;
import com.dropbox.core.v2.DbxClientV2;
import com.dropbox.core.v2.files.FileMetadata;
import com.dropbox.core.v2.files.WriteMode;
import com.hrp.dto.request.CreateAdminRequestDto;
import com.hrp.dto.request.UpdateAdminRequestDto;
import com.hrp.dto.response.FindAdminResponseDto;
import com.hrp.exception.AdminException;
import com.hrp.exception.EErrorType;
import com.hrp.repository.IAdminRepository;
import com.hrp.repository.entity.Admin;
import com.hrp.utility.ServiceManagerImpl;
//import io.imagekit.sdk.ImageKit;
//import io.imagekit.sdk.config.Configuration;
//import io.imagekit.sdk.models.FileCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AdminService extends ServiceManagerImpl<Admin, Long> {
    private final IAdminRepository adminRepository;
    public AdminService(IAdminRepository adminRepository) {
        super(adminRepository);
        this.adminRepository = adminRepository;

    }


    public Boolean createAdmin(CreateAdminRequestDto dto)  {

        save(Admin.builder()
                .email(dto.getEmail())
                .name(dto.getName())
                .surname(dto.getSurname())
                .username(dto.getUsername())
                .password(dto.getPassword())
                .build());
        return true;

    }

    public FindAdminResponseDto findMe(Long adminId){
        if (adminId==null){
            throw new AdminException(EErrorType.BAD_REQUEST_ERROR,"id null geldi");
        }
        Admin admin = adminRepository.findById(adminId).get();
        return FindAdminResponseDto.builder()
                .email(admin.getEmail())
                .username(admin.getUsername())
                .name(admin.getName())
                .surname(admin.getSurname())
                .build();
    }

    public String uploadImageCloud(MultipartFile file, Long id) {
        Optional<Admin> admin = adminRepository.findById(id);
        if (admin.isEmpty()){
            System.out.println("Kullanici bulunamadi");
        }

        Map config = new HashMap();
        config.put("cloud_name", "doqksh0xh");
        config.put("api_key", "871216635594134");
        config.put("api_secret", "6b3zcRZyWKeuiW6qIq4XvWnhVno");
        Cloudinary cloudinary = new Cloudinary(config);
        try{
            Map<?, ?> result = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
            String url = (String) result.get("url");
            admin.get().setAvatar(url);
            update(admin.get());
            System.out.println(url);
            return url;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Boolean updateAdmin(UpdateAdminRequestDto dto) {
        Optional<Admin> admin = adminRepository.findById(dto.getId());
        if (admin.isEmpty()){
            System.out.println("Kullanici bulunamadi");
            return false;
        }
        admin.get().setAddress(dto.getAddress());
        admin.get().setPhone(dto.getPhone());
        update(admin.get());
        return true;
    }
}