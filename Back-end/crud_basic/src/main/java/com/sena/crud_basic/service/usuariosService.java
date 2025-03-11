package com.sena.crud_basic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.model.usuarios;
import com.sena.crud_basic.DTO.usuariosDTO;
import com.sena.crud_basic.repository.Iusuarios;

@Service
public class usuariosService {
    @Autowired
    private Iusuarios data;

    public void save(usuariosDTO usuariosDTO){
        usuarios usuariosRegister = converToModel(usuariosDTO);
        data.save(usuariosRegister);
    }

    public usuariosDTO converToDTO(usuarios usuarios){
        usuariosDTO usuariosDTO = new usuariosDTO(
            usuarios.getnombre(),
            usuarios.getemail(),
            usuarios.getcontrasena(),
            usuarios.getdireccion(),
            usuarios.gettelefono());
        return usuariosDTO;
    }

    public usuarios converToModel(usuariosDTO usuariosDTO){
        usuarios usuarios = new usuarios(
            0,
            usuariosDTO.getNombre(),
            usuariosDTO.getEmail(),
            usuariosDTO.getContrasena(),
            usuariosDTO.getDireccion(),
            usuariosDTO.getTelefono(),
            null);
        return usuarios;
    }
}