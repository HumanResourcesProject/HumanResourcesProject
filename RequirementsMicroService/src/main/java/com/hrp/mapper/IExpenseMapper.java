package com.hrp.mapper;

import com.hrp.dto.response.BaseExpenseResponseDto;
import com.hrp.rabbitmq.model.ModelEmployeeExpense;
import com.hrp.repository.entity.Expense;

public interface IExpenseMapper {
    Expense toExpense(final ModelEmployeeExpense model);
    BaseExpenseResponseDto toResponseDto(final Expense expense);
}
