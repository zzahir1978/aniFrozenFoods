package com.anifrozenfoods.anifrozenfoods.services;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anifrozenfoods.anifrozenfoods.models.PhoneBook;

public interface PhoneBooksRepository extends JpaRepository<PhoneBook, Integer> {

}
