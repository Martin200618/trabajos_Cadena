package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.work;

public interface IWork extends JpaRepository <work,Integer> {

}