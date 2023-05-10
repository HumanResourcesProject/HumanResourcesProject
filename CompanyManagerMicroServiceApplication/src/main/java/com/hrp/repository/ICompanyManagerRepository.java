package com.hrp.repository;

import com.hrp.repository.entity.CompanyManager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICompanyManagerRepository extends JpaRepository<CompanyManager, Long> {
    Optional<CompanyManager> findOptionalById(Long id);
}
