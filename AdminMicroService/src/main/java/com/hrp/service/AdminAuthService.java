package com.hrp.service;

import com.hrp.exception.AdminException;
import com.hrp.exception.EErrorType;
import com.hrp.repository.IAdminAuthRepository;
import com.hrp.repository.entity.Admin;
import com.hrp.repository.entity.AdminAuth;
import com.hrp.repository.entity.enums.AdminRole;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.data.jpa.repository.JpaRepository;
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
        if(adminId==null){
            throw new AdminException(EErrorType.USERID_NOT_EMPTY);
        }
        return adminAuthRepository.findOptionalByAdminId(adminId).get().stream().map(x->x.getAdminRole()).toList();

    }

}
