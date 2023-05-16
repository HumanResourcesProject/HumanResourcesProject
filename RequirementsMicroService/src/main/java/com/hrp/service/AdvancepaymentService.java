package com.hrp.service;

import com.hrp.mapper.IManuelRequirementsMapper;
import com.hrp.rabbitmq.model.ModelEmployeeAdvancePaymentRequest;
import com.hrp.repository.IAdvancePaymentRepository;
import com.hrp.repository.entity.AdvancedPayment;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

@Service
public class AdvancepaymentService extends ServiceManagerImpl<AdvancedPayment, Long> {
    private final IAdvancePaymentRepository advancePaymentRepository;
    private final IManuelRequirementsMapper manuelRequirementsMapper;
    public AdvancepaymentService(IAdvancePaymentRepository advancePaymentRepository, IManuelRequirementsMapper manuelRequirementsMapper) {
        super(advancePaymentRepository);
        this.advancePaymentRepository = advancePaymentRepository;
        this.manuelRequirementsMapper = manuelRequirementsMapper;
    }

    public void createAdvancePayment(ModelEmployeeAdvancePaymentRequest model) {
        System.out.println("createAdvancePayment metodu ici");
            save(manuelRequirementsMapper.toAdvancePayment(model));

    }
}
