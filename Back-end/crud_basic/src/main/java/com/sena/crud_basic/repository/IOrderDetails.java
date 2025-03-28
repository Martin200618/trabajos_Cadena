package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.orderDetails;

public interface IOrderDetails extends JpaRepository<orderDetails, Integer> {
}
