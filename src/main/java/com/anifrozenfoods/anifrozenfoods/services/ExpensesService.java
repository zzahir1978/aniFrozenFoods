package com.anifrozenfoods.anifrozenfoods.services;

import org.springframework.stereotype.Service;

import com.anifrozenfoods.anifrozenfoods.models.Expense;
import java.util.List;

@Service
public class ExpensesService {

    private final ExpensesRepository expensesRepository;

    public ExpensesService(ExpensesRepository expensesRepository) {
        this.expensesRepository = expensesRepository;
    }

    public List<Expense> getAllExpenses() {
        return expensesRepository.findAll();
    }
}
