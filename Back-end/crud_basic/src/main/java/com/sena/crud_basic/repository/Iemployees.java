package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.employees;
public interface Iemployees extends JpaRepository <employees,Integer>{
}