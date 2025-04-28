package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.crud_basic.model.Generos;

@Repository
public interface IGenerosRepository extends JpaRepository<Generos, Integer> {
    // Métodos personalizados si es necesario
    // Optional<Generos> findByNombre(String nombre);
}