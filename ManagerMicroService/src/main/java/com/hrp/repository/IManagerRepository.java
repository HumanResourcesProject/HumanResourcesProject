package com.hrp.repository;

import com.hrp.repository.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IManagerRepository extends JpaRepository<Manager, Long> {
    Optional<Manager> findOptionalById(Long id);
    Optional<Manager> findOptionalByAuthId(Long id);
    Optional<List<Manager>> findOptionalByCompany(String company);
}
