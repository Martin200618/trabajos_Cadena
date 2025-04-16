package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.Categorie;

public interface ICategorie extends JpaRepository <Categorie,Integer> {

}