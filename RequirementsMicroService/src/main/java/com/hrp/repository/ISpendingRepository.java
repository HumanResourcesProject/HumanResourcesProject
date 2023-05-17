package com.hrp.repository;

import com.hrp.repository.entity.Spending;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ISpendingRepository extends JpaRepository<Spending,Long> {

    Optional<List<Spending>> findOptionalByCompany(String company);
}
