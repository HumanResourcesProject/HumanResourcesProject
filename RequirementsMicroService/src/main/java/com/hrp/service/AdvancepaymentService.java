package com.hrp.service;

import com.hrp.rabbitmq.model.ModelEmployeePermissionRequest;
import com.hrp.repository.IAdvancedPaymentRepository;
import com.hrp.repository.entity.AdvancedPayment;
import com.hrp.repository.entity.Permission;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public class AdvancepaymentService extends ServiceManagerImpl<AdvancedPayment, Long> {
    private final IAdvancedPaymentRepository advancedPaymentRepository;
    public AdvancepaymentService(IAdvancedPaymentRepository advancedPaymentRepository) {
        super(advancedPaymentRepository);
        this.advancedPaymentRepository = advancedPaymentRepository;
    }

    public void createAdvancePayment(ModelEmployeePermissionRequest model) {
        save(AdvancedPayment.builder()
                .employeeId(model.getAuthId())
                .amount(model.getSalary())
                .company(model.getCompany())
                .build());
    }
}
