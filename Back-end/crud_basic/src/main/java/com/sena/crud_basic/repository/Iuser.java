package com.sena.crud_basic.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.sena.crud_basic.model.user;

@Repository
public interface Iuser extends JpaRepository<user,Integer>{
    Optional<user>findByEmail(String email);
}
