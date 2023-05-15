package com.hrp.config.security;

import com.hrp.exception.AdminException;
import com.hrp.exception.EErrorType;
import com.hrp.repository.entity.Admin;
import com.hrp.repository.entity.enums.AdminRole;
import com.hrp.service.AdminAuthService;
import com.hrp.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Service
public class JwtUserDetail implements UserDetailsService {
    @Autowired
    AdminService adminService;
    @Autowired
    AdminAuthService adminAuthService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }

    public UserDetails getUserDetailObject(Long authid){
        System.out.println("gelen auth id : "+authid);
        Optional<Admin> admin = adminService.findByAuthId(authid);
        System.out.println("adminin id si: "+admin.get().getId());
        List<AdminRole> adminRoles = adminAuthService.findMyAuthList(admin.get().getId());

        System.out.println("****** 40 *******");
        if (admin.isEmpty()) throw new AdminException(EErrorType.BAD_REQUEST_ERROR, "jwt user detail ici patladi");
        System.out.println("****** 42 *******");
        if (adminRoles.isEmpty()) throw new AdminException(EErrorType.BAD_REQUEST_ERROR, "jwt user detail ici patladi");
        List<GrantedAuthority> authorities= new ArrayList<>();
        System.out.println("****** 45 *******");
        for (AdminRole adminRole: adminRoles ){
            authorities.add(new SimpleGrantedAuthority(adminRole.name()));
        }
        System.out.println("auth listesi son hali userdetail icindeki  = " + authorities.toString());
        System.out.println("admin name son hali userdetail icindeki  = " + admin.get().getName());

        return User.builder()
                .username(admin.get().getName())
                .password("")
                .authorities(authorities)
                .build();
    }
}
