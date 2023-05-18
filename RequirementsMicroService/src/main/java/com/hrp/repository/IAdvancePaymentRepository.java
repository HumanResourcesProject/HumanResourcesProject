package com.hrp.repository;

import com.hrp.repository.entity.AdvancedPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAdvancePaymentRepository extends JpaRepository<AdvancedPayment,Long> {
    Optional<List<AdvancedPayment>> findOptionalByAuthId(Long authId);
    Optional<List<AdvancedPayment>> findOptionalByManagerId(Long managerId);

}
