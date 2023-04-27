package com.hrp.service;

import com.hrp.dto.request.CreateAdminRequestDto;
import com.hrp.dto.response.FindAdminResponseDto;
import com.hrp.exception.AdminException;
import com.hrp.exception.EErrorType;
import com.hrp.repository.IAdminRepository;
import com.hrp.repository.entity.Admin;
import com.hrp.utility.ServiceManagerImpl;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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



}
