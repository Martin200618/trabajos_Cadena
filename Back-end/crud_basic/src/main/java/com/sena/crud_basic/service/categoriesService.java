package com.sena.crud_basic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.model.categories;
import com.sena.crud_basic.DTO.categoriesDTO;
import com.sena.crud_basic.repository.Icategories;

@Service
public class categoriesService {
    @Autowired
    private Icategories data;

    public void save(categoriesDTO categoriesDTO){
        categories categoriesRegister = converToModel(categoriesDTO);
        data.save(categoriesRegister);
    }

    public categoriesDTO conertToDTO(categories categories){
        categoriesDTO categoriesDTO = new categoriesDTO(
            categories.getName());
        return categoriesDTO;
    }

    public categories converToModel(categoriesDTO categoriesDTO){
        categories categories = new categories(
            0,
            categoriesDTO.getname());
        return categories;
    }
}
