package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.inventory;

public interface Iinventory extends JpaRepository<inventory,Integer>{
}
