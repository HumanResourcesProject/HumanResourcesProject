package com.hrp.mapper;


import com.hrp.rabbitmq.model.ModelTurnAllLeaveRequest;
import com.hrp.rabbitmq.model.ModelEmployeeAdvancePaymentRequest;
import com.hrp.rabbitmq.model.ModelEmployeeExpense;
import com.hrp.rabbitmq.model.ModelEmployeeLeave;
import com.hrp.repository.entity.AdvancedPayment;
import com.hrp.repository.entity.Leave;
import com.hrp.repository.entity.Spending;
import com.hrp.repository.entity.enums.LeaveType;
import com.hrp.repository.entity.enums.SpendingCurrency;
import com.hrp.repository.entity.enums.SpendingType;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ManuelRequirementsMapper implements IManuelRequirementsMapper {


    @Override
    public AdvancedPayment toAdvancePayment(ModelEmployeeAdvancePaymentRequest model) {
        AdvancedPayment advancedPayment= new AdvancedPayment();
        advancedPayment.setAmount(model.getAmount());

        advancedPayment.setEmployeeId(model.getEmployeeId());

        advancedPayment.setCompany(model.getCompany());


        advancedPayment.setCurrency(SpendingCurrency.valueOf(model.getCurrency().trim().toUpperCase()));
        advancedPayment.setComment(model.getComment());
        advancedPayment.setStatus(1);
        advancedPayment.setRequestDate(LocalDateTime.now().toString());
        return advancedPayment;
    }

    @Override
    public Leave toLeave(ModelEmployeeLeave model) {
        Leave leave= new Leave();
        leave.setType(LeaveType.valueOf(model.getType().trim().toUpperCase()));
        leave.setEmployeeId(model.getEmployeeId());
        leave.setCompany(model.getCompany());
        leave.setStatus(1);
        leave.setFinishDate(model.getFinishDate());
        leave.setStartDate(model.getStartDate());
        leave.setRequestDate(LocalDateTime.now().toString());
        return leave;
    }

    @Override
    public Spending toSpending(ModelEmployeeExpense model) {
        Spending spending = new Spending();
        spending.setType(SpendingType.valueOf(model.getType().trim().toUpperCase()));
        spending.setCurrency(SpendingCurrency.valueOf(model.getCurrency().trim().toUpperCase()));
        spending.setAmount(model.getAmount());
        spending.setEmployeeId(model.getEmployeeId());
        spending.setCompany(model.getCompany());
        spending.setStatus(1);
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

       return request;
    }
}
