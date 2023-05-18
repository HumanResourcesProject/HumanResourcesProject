package com.hrp.controller.manager;

import com.hrp.dto.request.BaseRequestDto;
import com.hrp.dto.response.BaseExpenseResponseDto;
import com.hrp.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/requirements/manager")
public class ExpenseManagerController {
    private final ExpenseService expenseService;

    @PostMapping("/findallmyexpenses")
    @CrossOrigin("*")
    public ResponseEntity<List<BaseExpenseResponseDto>> findAllMyExpensesForManager(@RequestBody BaseRequestDto dto) {
        return ResponseEntity.ok(expenseService.findAllMyExpensesForManager(dto));
    }

    @PostMapping("/findallmyexpensespending")
    @CrossOrigin("*")
    public ResponseEntity<List<BaseExpenseResponseDto>> findAllMyExpensesPendingForManager(@RequestBody BaseRequestDto dto) {
        return ResponseEntity.ok(expenseService.findAllMyExpensesPendingForManager(dto));
    }

    @PostMapping("/findallmyexpensescount")
    @CrossOrigin("*")
    public ResponseEntity<Long> findAllMyExpensesForManagerCount(@RequestBody BaseRequestDto dto) {
        return ResponseEntity.ok(expenseService.findAllMyExpensesPendingForManager(dto).stream().count());
    }
}
