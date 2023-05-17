package com.hrp.service;

import com.hrp.dto.request.BaseRequestDto;
import com.hrp.dto.response.BaseExpenseResponseDto;
import com.hrp.mapper.IExpenseMapper;
import com.hrp.rabbitmq.model.ModelEmployeeExpense;
import com.hrp.repository.IExpenseRepository;
import com.hrp.repository.entity.Expense;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseService extends ServiceManagerImpl<Expense, Long> {
    private final IExpenseRepository expenseRepository;
    private final JwtTokenManager jwtTokenManager;
    private final IExpenseMapper expenseMapper;

    public ExpenseService(IExpenseRepository expenseRepository, JwtTokenManager jwtTokenManager, IExpenseMapper expenseMapper) {
        super(expenseRepository);
        this.expenseRepository = expenseRepository;

        this.jwtTokenManager = jwtTokenManager;
        this.expenseMapper = expenseMapper;
    }

    public void createExpense(ModelEmployeeExpense modelEmployeeExpense) {
        System.out.println("expense kayıt ici");
        save(expenseMapper.toExpense(modelEmployeeExpense));
        System.out.println("expense kayıt ici ve kayit sonrasi");
    }


    public List<BaseExpenseResponseDto> findAllMyExpensesForEmployee(BaseRequestDto dto) {
        Optional<Long> authId=  jwtTokenManager.validToken(dto.getToken());
        Optional<List<Expense>> expenses = expenseRepository.findOptionalByAuthId(authId.get());
        List<BaseExpenseResponseDto> dtos = new ArrayList<>();
        for (Expense expense: expenses.get()){
            dtos.add(expenseMapper.toResponseDto(expense));
        }
        return dtos;
    }
}
