package com.hrp.mapper.Impl;

import com.hrp.dto.response.BaseExpenseResponseDto;
import com.hrp.mapper.IExpenseMapper;
import com.hrp.rabbitmq.model.ModelEmployeeExpense;
import com.hrp.repository.entity.Expense;
import com.hrp.repository.entity.enums.ExpenseCurrency;
import com.hrp.repository.entity.enums.ExpenseType;
import org.springframework.stereotype.Component;

@Component
public class ExpenseMapper implements IExpenseMapper {
    @Override
    public Expense toExpense(ModelEmployeeExpense model) {
        Expense expense = new Expense();
        expense.setType(ExpenseType.valueOf(model.getType()));
        expense.setCurrency(ExpenseCurrency.valueOf(model.getCurrency()));
        expense.setAmount(model.getAmount());
        expense.setEmployeeId(model.getEmployeeId());
        expense.setCompany(model.getCompany());
        expense.setStatus(0);
        expense.setSpendingDate(model.getSpendingDate());
        expense.setInvoiceUrl(model.getInvoiceUrl());
        expense.setCompany(model.getCompany());
        expense.setManagerId(model.getManagerId());
        expense.setAuthId(model.getAuthId());
        return expense;
    }

    @Override
    public BaseExpenseResponseDto toResponseDto(Expense expense) {
        BaseExpenseResponseDto dto= new BaseExpenseResponseDto();
        dto.setEmployeeId(expense.getEmployeeId());
        dto.setManagerId(expense.getManagerId());
        dto.setAuthId(expense.getAuthId());
        dto.setCompany(expense.getCompany());
        dto.setAmount(expense.getAmount());
        dto.setCurrency(expense.getCurrency().name());
        dto.setType(expense.getType().name());
        dto.setStatus(expense.getStatus());
        dto.setSpendingDate(expense.getSpendingDate());
        dto.setRequestDate(expense.getRequestDate());
        dto.setApprovalDate(expense.getApprovalDate());
        dto.setInvoiceUrl(expense.getInvoiceUrl());
        return dto;

    }

}
