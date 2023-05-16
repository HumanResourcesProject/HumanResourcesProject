package com.hrp.mapper;


import com.hrp.rabbitmq.model.ModelEmployeeAdvancePaymentRequest;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import com.hrp.repository.entity.AdvancedPayment;
import com.hrp.repository.entity.Leave;

public interface IManuelRequirementsMapper {
    AdvancedPayment toAdvancePayment(ModelEmployeeAdvancePaymentRequest model);
    Leave toLeave(ModelEmployeeLeave model);

}
