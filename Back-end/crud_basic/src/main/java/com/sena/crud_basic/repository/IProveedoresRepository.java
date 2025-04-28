package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.crud_basic.model.Proveedores;

@Repository
public interface IProveedoresRepository extends JpaRepository<Proveedores, Integer> {
    // Métodos adicionales, si es necesario.
}