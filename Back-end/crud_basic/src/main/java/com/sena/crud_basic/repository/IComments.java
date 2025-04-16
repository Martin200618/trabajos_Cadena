package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.Comments;

public interface IComments extends JpaRepository <Comments,Integer> {

}