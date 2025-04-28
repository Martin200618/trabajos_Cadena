package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.crud_basic.model.Albumes;

@Repository
public interface IAlbumesRepository extends JpaRepository<Albumes, Integer> {
    // Agrega métodos personalizados si lo necesitas, como:
    // Optional<Albumes> findByTitulo(String titulo);
}