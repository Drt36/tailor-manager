package com.internship.tailormanager.service;

import com.internship.tailormanager.dto.ExpenseDto;
import com.internship.tailormanager.enums.Status;
import com.internship.tailormanager.mapper.ExpenseMapper;
import com.internship.tailormanager.model.Expense;
import com.internship.tailormanager.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class ExpenseService {
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private ExpenseMapper expenseMapper;

    public ExpenseDto saveExpense(ExpenseDto expenseDto) {
        Expense expense=expenseMapper.dtoToModel(expenseDto);
        expense.setStatus(Status.ACTIVE);
        return expenseMapper.modelToDto(expenseRepository.save(expense));
    }

    public ExpenseDto getExpenseById(Long id) {
        Expense expense = expenseRepository.getExpenseByExpenseIdAndStatus(id,Status.ACTIVE);
        return expenseMapper.modelToDto(expense);
    }

    public Page<ExpenseDto> getAllActiveExpenses(int page) {
        Pageable pageable = PageRequest.of(page, 2);
        Page<Expense> expenses = expenseRepository.findExpenseByStatus(Status.ACTIVE, pageable);

        Page<ExpenseDto> expenseDtos = expenses.map(expense -> expenseMapper.modelToDto(expense));
        return expenseDtos;
    }

    public ExpenseDto updateExpense(Long id, ExpenseDto expenseDto){
        Expense expense = expenseRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        expense.setTitle(expenseDto.getTitle());
        expense.setAmount(expenseDto.getAmount());
        expense.setDescription(expenseDto.getDescription());

        Expense savedExpense = expenseRepository.save(expense);

        return expenseMapper.modelToDto(savedExpense);
    }

    public ExpenseDto deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        expense.setStatus(Status.REMOVED);

        return expenseMapper.modelToDto(expenseRepository.save(expense));
    }
}
