package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.categoriesDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.categories;
import com.sena.crud_basic.repository.Icategories;

@Service
public class categoriesService {
    @Autowired
    private Icategories data;
    public List<categories> findAll(){
        return data.findAll();
    }
    public Optional<categories> findById(int categories_id){
        return data.findById(categories_id);
    }

    public responseDTO deleteCategorie(int categories_id){
        if(!findById(categories_id).isPresent()){
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La categoria que deseas eliminar no se encuentra o ya se elimino"
            );
            return respuesta;
        }
        data.deleteById(categories_id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "La categoria fue eliminada correctamente"
        );
        return respuesta;
    }

    public responseDTO save(categoriesDTO categoriesDTO){
        converToModel(categoriesDTO);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "se guardo correctamente"
        );
        return respuesta; 
    }

    public categoriesDTO converToDTO(categories categories){
        categoriesDTO categoriesDTO = new categoriesDTO(
            categories.getName()
        );
        return categoriesDTO;
    }

    public categories converToModel(categoriesDTO categoriesDTO){
        categories categories = new categories(
            0,
            categoriesDTO.getName()
        );
        return categories;
    }
}