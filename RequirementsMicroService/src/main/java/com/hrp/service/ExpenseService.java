package com.hrp.service;

import com.hrp.dto.request.BaseAnswerDto;
import com.hrp.dto.request.BaseRequestDto;
import com.hrp.dto.response.BaseExpenseResponseDto;
import com.hrp.exception.EErrorType;
import com.hrp.exception.RequirementsMicroException;
import com.hrp.mapper.IExpenseMapper;
import com.hrp.rabbitmq.model.ModelEmployeeExpense;
import com.hrp.repository.IExpenseRepository;
import com.hrp.repository.entity.Expense;
import com.hrp.utility.JwtTokenManager;
import com.hrp.utility.ServiceManagerImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        if (authId.isEmpty()){
            throw new RequirementsMicroException(EErrorType.INVALID_TOKEN);
        }
        Optional<List<Expense>> expenses = expenseRepository.findOptionalByAuthId(authId.get());
        if (expenses.isEmpty()){
            throw new RequirementsMicroException(EErrorType.REQUIREMENTS_NOT_FOUND);
        }
        List<BaseExpenseResponseDto> dtos = new ArrayList<>();
        for (Expense expense: expenses.get()){
            dtos.add(expenseMapper.toResponseDto(expense));
        }
        return dtos;
    }

    public List<BaseExpenseResponseDto> findAllMyExpensesForManager(BaseRequestDto dto) {
        Optional<Long> authId=  jwtTokenManager.validToken(dto.getToken());
        if (authId.isEmpty()){
            throw new RequirementsMicroException(EErrorType.INVALID_TOKEN);
        }
        Optional<List<Expense>> expenses = expenseRepository.findOptionalByManagerId(authId.get());
        if (expenses.isEmpty()){
            throw new RequirementsMicroException(EErrorType.REQUIREMENTS_NOT_FOUND);
        }
        List<BaseExpenseResponseDto> dtos = new ArrayList<>();
        for (Expense expense: expenses.get()){
            dtos.add(expenseMapper.toResponseDto(expense));
        }
        return dtos;
    }

    public List<BaseExpenseResponseDto> findAllMyExpensesPendingForManager(BaseRequestDto dto) {
        Optional<Long> authId=  jwtTokenManager.validToken(dto.getToken());
        if (authId.isEmpty()){
            throw new RequirementsMicroException(EErrorType.INVALID_TOKEN);
        }
        Optional<List<Expense>> expenses = expenseRepository.findOptionalByManagerId(authId.get());
        if (expenses.isEmpty()){
            throw new RequirementsMicroException(EErrorType.REQUIREMENTS_NOT_FOUND);
        }
        List<BaseExpenseResponseDto> dtos = new ArrayList<>();
        for (Expense expense: expenses.get()){
            dtos.add(expenseMapper.toResponseDto(expense));
        }
        return dtos.stream().filter(x->x.getStatus()=="Pending").toList();
    }

    public boolean approveExpense(BaseAnswerDto dto) {
        Optional<Expense> expense = expenseRepository.findById(dto.getRequirementId());
        if (expense.isEmpty()){
            throw new RequirementsMicroException(EErrorType.REQUIREMENTS_NOT_FOUND);
        }
        expense.get().setStatus(1);
        expense.get().setApprovalDate(LocalDateTime.now().toString());
        update(expense.get());
        return true;
    }

    //rejectExpense
    public Boolean rejectExpense(BaseAnswerDto dto) {
        Optional<Expense> expense = expenseRepository.findById(dto.getRequirementId());
        if (expense.isEmpty()){
            throw new RequirementsMicroException(EErrorType.REQUIREMENTS_NOT_FOUND);
        }
        expense.get().setStatus(2);
        expense.get().setApprovalDate(LocalDateTime.now().toString());
        update(expense.get());
        return true;
    }



}
