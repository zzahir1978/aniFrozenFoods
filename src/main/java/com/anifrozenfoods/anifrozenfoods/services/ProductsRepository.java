package com.anifrozenfoods.anifrozenfoods.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anifrozenfoods.anifrozenfoods.models.Product;

public interface ProductsRepository extends JpaRepository<Product, Integer> {

}
