package com.hrp.mapper;


import com.hrp.rabbitmq.model.*;
import com.hrp.repository.entity.AdvancedPayment;
import com.hrp.repository.entity.Leave;
import com.hrp.repository.entity.Spending;
import com.hrp.repository.entity.enums.LeaveType;
import com.hrp.repository.entity.enums.SpendingCurrency;
import com.hrp.repository.entity.enums.SpendingType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Locale;

@Component
public class ManuelRequirementsMapper implements IManuelRequirementsMapper {


    @Override
    public AdvancedPayment toAdvancePayment(ModelEmployeeAdvancePaymentRequest model) {
        AdvancedPayment advancedPayment= new AdvancedPayment();
        advancedPayment.setAmount(model.getAmount());
        advancedPayment.setEmployeeId(model.getEmployeeId());
        advancedPayment.setCompany(model.getCompany());
        advancedPayment.setCurrency(SpendingCurrency.valueOf(model.getCurrency().trim().toUpperCase(Locale.UK)));
        advancedPayment.setComment(model.getComment());
        advancedPayment.setStatus(0);
        advancedPayment.setAdvancedPaymentDate(model.getAdvancedPaymentDate());
        advancedPayment.setRequestDate(LocalDateTime.now().toString());
        return advancedPayment;
    }

    @Override
    public Leave toLeave(ModelEmployeeLeave model) {
        Leave leave= new Leave();
        leave.setType(LeaveType.valueOf(model.getType().trim().toUpperCase(Locale.UK)));
        leave.setEmployeeId(model.getEmployeeId());
        leave.setCompany(model.getCompany());
        leave.setAmountOfDay(model.getAmountOfDay());
        leave.setStatus(0);
        leave.setFinishDate(model.getFinishDate());
        leave.setStartDate(model.getStartDate());
        leave.setRequestDate(LocalDateTime.now().toString());
        return leave;
    }

    @Override
    public Spending toSpending(ModelEmployeeExpense model) {
        Spending spending = new Spending();
        spending.setType(SpendingType.valueOf(model.getType().trim().toUpperCase(Locale.UK)));
        spending.setCurrency(SpendingCurrency.valueOf(model.getCurrency().trim().toUpperCase()));
        spending.setAmount(model.getAmount());
        spending.setEmployeeId(model.getEmployeeId());
        spending.setCompany(model.getCompany());
        spending.setStatus(0);
        spending.setSpendingDate(model.getSpendingDate());
        spending.setInvoiceUrl(model.getInvoiceUrl());
        spending.setCompany(model.getCompany());
        return spending;
    }

    @Override
    public ModelTurnAllLeaveRequest toModelTurnAllLeaveRequest(Leave leave) {
       ModelTurnAllLeaveRequest request = new ModelTurnAllLeaveRequest();
       request.setRequestDate(leave.getRequestDate());
       request.setStatus(leave.getStatus());
       request.setType(String.valueOf(leave.getType()));
       request.setFinishDate(leave.getFinishDate());
       request.setStartDate(leave.getStartDate());
       request.setApprovalDate(leave.getApprovalDate());
       request.setEmployeeId(leave.getEmployeeId());
       request.setCompany(leave.getCompany());
       request.setAmountOfDay(leave.getAmountOfDay());
       request.setManagerId(leave.getManagerId());
       return request;
    }

    @Override
    public ModelTurnAllExpenseRequest toModelTurnAllExpenseRequest(Spending spending) {
        ModelTurnAllExpenseRequest request = new ModelTurnAllExpenseRequest();
        request.setAmount(spending.getAmount());
        request.setApprovalDate(spending.getApprovalDate());
        request.setCompany(spending.getCompany());
        request.setType(String.valueOf(spending.getType()));
        request.setStatus(spending.getStatus());
        request.setCurrency(String.valueOf(spending.getCurrency()));
        request.setManagerId(spending.getManagerId());
        request.setEmployeeId(spending.getEmployeeId());
        request.setInvoiceUrl(spending.getInvoiceUrl());
        request.setSpendingDate(spending.getSpendingDate());
        request.setRequestDate(spending.getRequestDate());
        return request;
    }

    @Override
    public ModelTurnAllAdvancePaymentRequest toModelTurnAllAdvancePaymentRequest(AdvancedPayment advancedPayment) {
        ModelTurnAllAdvancePaymentRequest request = new ModelTurnAllAdvancePaymentRequest();
        request.setAdvancedPaymentDate(advancedPayment.getAdvancedPaymentDate());
        request.setRequestDate(advancedPayment.getRequestDate());
        request.setCompany(advancedPayment.getCompany());
        request.setAmount(advancedPayment.getAmount());
        request.setComment(advancedPayment.getComment());
        request.setEmployeeId(advancedPayment.getEmployeeId());
        request.setManagerId(advancedPayment.getManagerId());
        request.setStatus(advancedPayment.getStatus());
        request.setCurrency(String.valueOf(advancedPayment.getCurrency()));
        request.setApprovalDate(advancedPayment.getApprovalDate());
        return request;
    }
}
