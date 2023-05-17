package com.hrp.service;

import com.hrp.mapper.IManuelRequirementsMapper;
import com.hrp.rabbitmq.model.ModelBaseRequirmentFindAll;
import com.hrp.rabbitmq.model.ModelEmployeeAdvancePaymentRequest;
import com.hrp.rabbitmq.model.ModelTurnAllAdvancePaymentRequest;
import com.hrp.rabbitmq.producer.DirectProducer;
import com.hrp.repository.IAdvancePaymentRepository;
import com.hrp.repository.entity.AdvancedPayment;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdvancepaymentService extends ServiceManagerImpl<AdvancedPayment, Long> {
    private final IAdvancePaymentRepository advancePaymentRepository;
    private final IManuelRequirementsMapper manuelRequirementsMapper;
    private final DirectProducer directProducer;
    public AdvancepaymentService(IAdvancePaymentRepository advancePaymentRepository, IManuelRequirementsMapper manuelRequirementsMapper, DirectProducer directProducer) {
        super(advancePaymentRepository);
        this.advancePaymentRepository = advancePaymentRepository;
        this.manuelRequirementsMapper = manuelRequirementsMapper;
        this.directProducer = directProducer;
    }

    public void createAdvancePayment(ModelEmployeeAdvancePaymentRequest model) {
        System.out.println("createAdvancePayment metodu ici");
            save(manuelRequirementsMapper.toAdvancePayment(model));

    }

    public void findAllAdvancePaymentRequestByCompany(ModelBaseRequirmentFindAll modelBaseRequirmentFindAll) {
        Optional<List<AdvancedPayment>> paymentList =advancePaymentRepository.findOptionalByCompany(modelBaseRequirmentFindAll.getCompany());
        System.out.println("paymentList..: " + paymentList.get());
        List<ModelTurnAllAdvancePaymentRequest> turnAllAdvancePaymentRequestList = new ArrayList<>();

        for (AdvancedPayment advancedPayment: paymentList.get()){
            turnAllAdvancePaymentRequestList.add(manuelRequirementsMapper.toModelTurnAllAdvancePaymentRequest(advancedPayment));
        }
        directProducer.turnAllAdvancePaymentEmployee(turnAllAdvancePaymentRequestList);
    }
}
