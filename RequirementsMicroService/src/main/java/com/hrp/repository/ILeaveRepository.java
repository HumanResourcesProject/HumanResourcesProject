package com.hrp.repository;

import com.hrp.repository.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ILeaveRepository extends JpaRepository<Leave,Long>{

    Optional<List<Leave>> findOptionalByCompany(String company);
    Optional<List<Leave>> findOptionalByEmployeeId(String employeeId);
    Optional<List<Leave>> findOptionalByManagerId(Long managerId);
    Optional<List<Leave>> findOptionalByAuthId(Long authId);
    Optional<List <Leave>> findOptionalByAuthIdOrderByCreateDate(Long authId);
 }
