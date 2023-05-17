package com.hrp.mapper;


import com.hrp.rabbitmq.model.*;
import com.hrp.repository.entity.AdvancedPayment;
import com.hrp.repository.entity.Leave;
import com.hrp.repository.entity.Spending;

public interface IManuelRequirementsMapper {
    AdvancedPayment toAdvancePayment(ModelEmployeeAdvancePaymentRequest model);
    Leave toLeave(ModelEmployeeLeave model);

    Spending toSpending(ModelEmployeeExpense modelEmployeeExpense);

    ModelTurnAllLeaveRequest toModelTurnAllLeaveRequest(Leave leave);

    ModelTurnAllExpenseRequest toModelTurnAllExpenseRequest(Spending spending);

    ModelTurnAllAdvancePaymentRequest toModelTurnAllAdvancePaymentRequest(AdvancedPayment advancedPayment);
}
