package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.Activity;

public interface IActivity extends JpaRepository<Activity,Integer>{
    
}