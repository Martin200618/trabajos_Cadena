package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crud_basic.model.Orders;

public interface IOrders extends JpaRepository<Orders,Integer>{
}