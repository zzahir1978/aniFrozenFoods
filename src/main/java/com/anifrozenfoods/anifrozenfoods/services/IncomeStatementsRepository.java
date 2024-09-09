package com.anifrozenfoods.anifrozenfoods.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anifrozenfoods.anifrozenfoods.models.IncomeStatement;

public interface IncomeStatementsRepository extends JpaRepository<IncomeStatement, Integer> {

}
