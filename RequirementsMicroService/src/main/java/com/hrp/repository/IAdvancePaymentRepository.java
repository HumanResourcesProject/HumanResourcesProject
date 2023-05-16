package com.hrp.repository;

import com.hrp.repository.entity.AdvancedPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdvancePaymentRepository extends JpaRepository<AdvancedPayment,Long> {
}
