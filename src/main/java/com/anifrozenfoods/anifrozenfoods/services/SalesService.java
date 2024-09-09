package com.anifrozenfoods.anifrozenfoods.services;

import org.springframework.stereotype.Service;

import com.anifrozenfoods.anifrozenfoods.models.Sale;

import java.util.List;

@Service
public class SalesService {

    private final SalesRepository salesRepository;

    public SalesService(SalesRepository salesRepository) {
        this.salesRepository = salesRepository;
    }

    public List<Sale> getAllSales() {
        return salesRepository.findAll();
    }
}
