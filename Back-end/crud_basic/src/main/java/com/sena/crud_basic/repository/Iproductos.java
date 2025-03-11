package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crud_basic.model.productos;
public interface Iproductos extends JpaRepository<productos,Integer>{
}
