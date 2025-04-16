package com.sena.crud_basic.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sena.crud_basic.model.team_user;

public interface ITeam_user extends JpaRepository <team_user,Integer> {

}