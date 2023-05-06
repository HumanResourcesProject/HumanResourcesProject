package com.hrp.repository;
import com.hrp.repository.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IAdminRepository extends JpaRepository<Admin,Long>{


    Optional<Admin> findOptionalByEmail(String email);
}
