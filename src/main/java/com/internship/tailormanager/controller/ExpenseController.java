package com.internship.tailormanager.controller;
;
import com.internship.tailormanager.dto.ExpenseDto;
import com.internship.tailormanager.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/api/user/expense")
    public ResponseEntity<ExpenseDto> saveExpense(@Valid @RequestBody ExpenseDto expenseDto){
        ExpenseDto savedExpenseDto = expenseService.saveExpense(expenseDto);
        return new ResponseEntity<ExpenseDto>(savedExpenseDto, HttpStatus.OK);
    }

    @GetMapping("/api/user/expense/{id}")
    public ExpenseDto getExpense(@NotNull @PathVariable("id") Long id) {
        return expenseService.getExpenseById(id);
    }

    @GetMapping("/api/user/expense")
    public Page<ExpenseDto> getAllExpense(@RequestParam("page") int page) {
        Page<ExpenseDto> expenseDtos = expenseService.getAllActiveExpenses(page);
        return expenseDtos;
    }

    @Transactional
    @PutMapping("/api/user/expense/{id}")
    public ResponseEntity<ExpenseDto> updateExpense(@NotNull @PathVariable("id") Long id,@RequestBody ExpenseDto expenseDto){
        ExpenseDto savedExpenseDto= expenseService.updateExpense(id,expenseDto);
        return new ResponseEntity<ExpenseDto>(savedExpenseDto, HttpStatus.OK);
    }

    @Transactional
    @PutMapping("/api/user/expense/delete/{id}")
    public ResponseEntity<ExpenseDto> deleteExpense(@NotNull @PathVariable("id") Long id) {
        ExpenseDto expenseDto = expenseService.deleteExpense(id);
        return new ResponseEntity<ExpenseDto>(expenseDto, HttpStatus.OK);
    }
}
