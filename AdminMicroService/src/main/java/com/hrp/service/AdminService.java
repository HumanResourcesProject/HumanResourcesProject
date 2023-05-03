package com.hrp.service;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.hrp.dto.request.CreateAdminRequestDto;
import com.hrp.dto.request.UpdateAdminRequestDto;
import com.hrp.dto.response.BaseAdminResponseDto;
import com.hrp.exception.AdminException;
import com.hrp.exception.EErrorType;
import com.hrp.repository.IAdminRepository;
import com.hrp.repository.entity.Admin;
import com.hrp.utility.ServiceManagerImpl;
//import io.imagekit.sdk.ImageKit;
//import io.imagekit.sdk.config.Configuration;
//import io.imagekit.sdk.models.FileCreateRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class AdminService extends ServiceManagerImpl<Admin, Long> {
    private final IAdminRepository adminRepository;
    public AdminService(IAdminRepository adminRepository) {
        super(adminRepository);
        this.adminRepository = adminRepository;

    }


    public Boolean createAdmin(CreateAdminRequestDto dto)  {

        if (dto.getName()==null || dto.getSurname()==null
                || dto.getEmail()==null || dto.getPassword()==null){
            throw new AdminException(EErrorType.BAD_REQUEST_ERROR,"zorunlu alanları doldurunuz");
        }

        save(Admin.builder()
                .avatar(uploadImageCloudMft(dto.getAvatar()))
                .phone(dto.getPhone())
                .address(dto.getAddress())
                .email(dto.getEmail())
                .name(dto.getName())
                .surname(dto.getSurname())
                .password(dto.getPassword())
                .build());
        return true;
    }

    public BaseAdminResponseDto findMe(Long adminId){
        if (adminId==null){
            throw new AdminException(EErrorType.BAD_REQUEST_ERROR,"id null geldi");
        }
        Admin admin = adminRepository.findById(adminId).get();
        return BaseAdminResponseDto.builder()
                .email(admin.getEmail())
                .name(admin.getName())
                .surname(admin.getSurname())
                .avatar(admin.getAvatar())
                .phone(admin.getPhone())
                .address(admin.getAddress())
                .build();
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
            return url;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Boolean updateAdminMft(UpdateAdminRequestDto dto) {
        Optional<Admin> admin = adminRepository.findById(dto.getId());
        if (admin.isEmpty()){
            System.out.println("Kullanici bulunamadi");
            return false;
        }
        admin.get().setAvatar(uploadImageCloudMft(dto.getAvatar()));
        admin.get().setAddress(dto.getAddress());
        admin.get().setPhone(dto.getPhone());
        update(admin.get());
        return true;
    }


    // update admin oguz
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

    // findalladmin

    public Iterable<BaseAdminResponseDto> findAllAdmin() {
        List<BaseAdminResponseDto> baseAdminResponseDtos = new ArrayList<>();
        for (Admin admin : adminRepository.findAll()) {
            baseAdminResponseDtos.add(BaseAdminResponseDto.builder()
                            .phone(admin.getPhone())
                            .avatar(admin.getAvatar())
                            .address(admin.getAddress())
                    .email(admin.getEmail())
                    .name(admin.getName())
                    .surname(admin.getSurname())
                    .build());
        }
        return baseAdminResponseDtos;
    }

}