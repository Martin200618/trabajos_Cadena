package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.crud_basic.model.Artistas;

@Repository
public interface IArtistasRepository extends JpaRepository<Artistas, Integer> {
    // Puedes agregar métodos personalizados si los necesitas, como:
    // Optional<Artistas> findByNombre(String nombre);
}