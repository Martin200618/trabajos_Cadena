package com.sena.crud_basic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.model.categorias;
import com.sena.crud_basic.DTO.categoriasDTO;
import com.sena.crud_basic.repository.Icategorias;

@Service
public class categoriasService {
    @Autowired
    private Icategorias data;
    public void save(categoriasDTO categoriasDTO){
        categorias categoriasRegister = converToModel(categoriasDTO);
        data.save(categoriasRegister);
    }
    public categoriasDTO converToDTO(categorias categorias){
        categoriasDTO categoriasDTO = new categoriasDTO(
            categorias.getnombre());
        return categoriasDTO;
    }
    public categorias converToModel(categoriasDTO categoriasDTO){
        categorias categorias = new categorias(
            0,
            categoriasDTO.getnombre());
        return categorias;
    }
}
