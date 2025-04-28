package com.sena.crud_basic.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.Inventario;

@Repository
public interface IInventarioRepository extends JpaRepository<Inventario, Integer> {
}