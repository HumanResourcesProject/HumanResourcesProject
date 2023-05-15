package com.hrp.repository;

import com.hrp.repository.entity.AdminAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAdminAuthRepository extends JpaRepository<AdminAuth,Long> {
    Optional<List<AdminAuth>> findOptionalByAdminId(Long adminId);
}
