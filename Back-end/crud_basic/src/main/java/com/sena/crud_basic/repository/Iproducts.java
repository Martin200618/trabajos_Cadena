package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crud_basic.model.products;

public interface Iproducts extends JpaRepository<products,Integer>{
}
