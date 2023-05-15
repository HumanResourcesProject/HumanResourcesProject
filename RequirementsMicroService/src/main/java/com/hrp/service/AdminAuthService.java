package com.hrp.service;

import com.hrp.repository.IAdminAuthRepository;
import com.hrp.repository.entity.Admin;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminAuthService extends ServiceManagerImpl<AdminAuth,Long> {
    private final IAdminAuthRepository adminAuthRepository;


    public AdminAuthService(IAdminAuthRepository adminAuthRepository) {
        super(adminAuthRepository);
        this.adminAuthRepository = adminAuthRepository;
    }
    public void createAdminAuth(Long adminId){
        save(AdminAuth.builder()
                .adminId(adminId)
                .adminRole(AdminRole.ADMINLEVEL1)
                .build());
    }
    public List<AdminRole> findMyAuthList(Long adminId){
        return adminAuthRepository.findOptionalByAdminId(adminId).get().stream().map(x->x.getAdminRole()).toList();

    }

}
