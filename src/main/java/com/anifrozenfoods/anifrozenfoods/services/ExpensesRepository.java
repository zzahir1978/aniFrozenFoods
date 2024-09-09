package com.anifrozenfoods.anifrozenfoods.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anifrozenfoods.anifrozenfoods.models.Expense;

public interface ExpensesRepository extends JpaRepository<Expense, Integer> {

}
