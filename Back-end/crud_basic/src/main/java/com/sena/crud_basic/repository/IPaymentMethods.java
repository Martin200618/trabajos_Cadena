package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.sena.crud_basic.model.paymentMethods;
public interface IPaymentMethods extends JpaRepository<paymentMethods,Integer>{
}