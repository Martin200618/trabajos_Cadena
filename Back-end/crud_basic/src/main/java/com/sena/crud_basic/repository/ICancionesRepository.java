package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.crud_basic.model.Canciones;

@Repository
public interface ICancionesRepository extends JpaRepository<Canciones, Integer> {
    // Agrega métodos personalizados si lo necesitas, como:
    // Optional<Canciones> findByTitulo(String titulo);
}