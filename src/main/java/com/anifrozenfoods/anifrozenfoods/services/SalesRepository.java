package com.anifrozenfoods.anifrozenfoods.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anifrozenfoods.anifrozenfoods.models.Sale;

public interface SalesRepository extends JpaRepository<Sale, Integer> {

}
