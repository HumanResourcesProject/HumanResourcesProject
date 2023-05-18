package com.hrp.mapper.Impl;

import com.hrp.dto.response.BaseExpenseResponseDto;
import com.hrp.mapper.IExpenseMapper;
import com.hrp.rabbitmq.model.ModelEmployeeExpense;
import com.hrp.repository.entity.Expense;
import com.hrp.repository.entity.enums.ExpenseCurrency;
import com.hrp.repository.entity.enums.ExpenseType;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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
        expense.setRequestDate(LocalDate.now().toString());
        expense.setInvoiceUrl(model.getInvoiceUrl());
        expense.setCompany(model.getCompany());
        expense.setEmployeeName(model.getEmployeeName());
        expense.setEmployeeSurname(model.getEmployeeSurname());
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
        // entity'den gelen statusü dtoya icin string dönüsümü yapildi.
        dto.setStatus(expense.getStatus()==0 ? "Pending" : (expense.getStatus()==1 ? "Approved" : "Rejected"));
        dto.setSpendingDate(expense.getSpendingDate());
        dto.setRequestDate(expense.getRequestDate());
        dto.setApprovalDate(expense.getApprovalDate()==null ? "No Answer Yet" : expense.getApprovalDate().split("T")[0]);
        dto.setInvoiceUrl(expense.getInvoiceUrl());
        dto.setEmployeeName(expense.getEmployeeName());
        dto.setEmployeeSurname(expense.getEmployeeSurname());
        dto.setExpenseId(expense.getId());
        return dto;

    }

}
