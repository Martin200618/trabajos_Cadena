package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.crud_basic.model.Ventas;

@Repository
public interface IVentasRepository extends JpaRepository<Ventas, Integer> {
    // Métodos personalizados, como:
    // List<Ventas> findByFechaBetween(Date startDate, Date endDate);
}