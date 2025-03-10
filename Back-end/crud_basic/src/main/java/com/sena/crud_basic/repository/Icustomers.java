package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.customers;

public interface Icustomers extends JpaRepository <customers, Integer> {   
}