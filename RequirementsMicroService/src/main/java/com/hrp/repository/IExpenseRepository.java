package com.hrp.repository;

import com.hrp.repository.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IExpenseRepository extends JpaRepository<Expense,Long> {
    Optional<List<Expense>> findOptionalByEmployeeId(String employeeId);
    Optional<List<Expense>> findOptionalByAuthId(Long authId);
    Optional<List<Expense>> findOptionalByManagerId(Long managerId);

}
