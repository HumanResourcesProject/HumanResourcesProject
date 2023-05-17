package com.hrp.mapper;


import com.hrp.dto.response.BaseAdvancePaymentResponseDto;
import com.hrp.rabbitmq.model.*;
import com.hrp.repository.entity.AdvancedPayment;

public interface IAdvancePaymentMapper {
    AdvancedPayment toAdvancePayment(final ModelEmployeeAdvancePaymentRequest model);
    BaseAdvancePaymentResponseDto toBaseAdvancePaymentResponse(final AdvancedPayment advancedPayment);
}
