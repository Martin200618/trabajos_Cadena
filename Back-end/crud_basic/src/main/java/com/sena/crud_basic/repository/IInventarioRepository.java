package com.sena.crud_basic.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sena.crud_basic.model.Albumes;
import com.sena.crud_basic.model.Inventario;
import com.sena.crud_basic.model.Proveedores;

@Repository
public interface IInventarioRepository extends JpaRepository<Inventario, Integer> {
    // Métodos adicionales, por ejemplo:
    Optional<Albumes> findByID(String nombre);
    Optional<Proveedores> findByNombre(String nombre);
}