package com.hrp.controller.employee;

import com.hrp.dto.request.BaseRequestDto;
import com.hrp.dto.response.BaseExpenseResponseDto;
import com.hrp.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
@RequiredArgsConstructor
public class ExpenseEmployeeController {
    private final ExpenseService expenseService;

    // findAllmyExpenses
    @PostMapping("/findallmyexpenses")
    @CrossOrigin("*")
    public ResponseEntity<List<BaseExpenseResponseDto>> findAllMyExpenses(@RequestBody BaseRequestDto dto) {
        return ResponseEntity.ok(expenseService.findAllMyExpensesForEmployee(dto));
    }
}
