package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.teams;

public interface ITeams extends JpaRepository <teams,Integer> {

}