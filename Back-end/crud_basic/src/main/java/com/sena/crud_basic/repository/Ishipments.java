package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crud_basic.model.shipments;
public interface Ishipments extends JpaRepository<shipments,Integer>{
}
