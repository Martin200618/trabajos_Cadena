package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crud_basic.model.usuarios;
public interface Iusuarios extends JpaRepository<usuarios,Integer> {
}
