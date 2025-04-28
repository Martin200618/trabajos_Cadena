package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.crud_basic.model.Empleados;

@Repository
public interface IEmpleadosRepository extends JpaRepository<Empleados, Integer> {
    // Métodos adicionales si necesitas búsquedas específicas:
    // Optional<Empleados> findByEmail(String email);
}