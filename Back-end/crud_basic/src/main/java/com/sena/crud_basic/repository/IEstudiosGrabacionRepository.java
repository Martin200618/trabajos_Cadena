package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.crud_basic.model.EstudiosGrabacion;

@Repository
public interface IEstudiosGrabacionRepository extends JpaRepository<EstudiosGrabacion, Integer> {
    // Métodos personalizados pueden añadirse aquí.
}