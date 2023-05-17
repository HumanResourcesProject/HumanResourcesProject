package com.hrp.utility;

import com.hrp.rabbitmq.model.ModelTurnAllAdvancePaymentRequest;
import com.hrp.rabbitmq.model.ModelTurnAllExpenseRequest;
import com.hrp.rabbitmq.model.ModelTurnAllLeaveRequest;

import java.util.List;

public class StaticValues {
    public static List<ModelTurnAllLeaveRequest> findAllLeave;
    public static List<ModelTurnAllExpenseRequest> findAllExpense;
    public static List<ModelTurnAllAdvancePaymentRequest> findAllAdvancePayment;
}
