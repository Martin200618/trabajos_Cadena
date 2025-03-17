package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crud_basic.model.categories;
public interface Icategories extends JpaRepository<categories,Integer>{
}
