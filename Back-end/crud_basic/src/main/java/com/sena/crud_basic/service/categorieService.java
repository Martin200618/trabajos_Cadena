package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.categorieDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Categorie;
import com.sena.crud_basic.repository.ICategorie;

@Service
public class categorieService {
    @Autowired
    private ICategorie data;

    public List<Categorie> findAll(){
        return data.findAll();
    }

    public Optional<Categorie> findById(int categorie_id){
        return data.findById(categorie_id);
    }

    public responseDTO save(categorieDTO categorieDTO){
        if (categorieDTO == null || categorieDTO.getNombre() == null) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Faltan campos requeridos");
        }

        Categorie newcategorie = new Categorie();
        newcategorie.setDescripcion(categorieDTO.getDescripcion());
        newcategorie.setNombre(categorieDTO.getNombre());

        try {
            data.save(newcategorie);
            return new responseDTO(HttpStatus.OK.toString(), "Categoria guardada correctamente");
        }catch (Exception e){
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Ocurrio un error al registrar la categoria");
        }
    }

    public responseDTO updateCategorie(int categorie_id, categorieDTO categorieDTO){
        Optional<Categorie> existingCategorie = findById(categorie_id);

        if (!existingCategorie.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "No se encontro la categoria buscada");
        }

        Categorie categorieToUpdate = existingCategorie.get();
        categorieToUpdate.setDescripcion(categorieDTO.getDescripcion());
        categorieToUpdate.setNombre(categorieDTO.getNombre());

        try{
            data.save(categorieToUpdate);
            return new responseDTO(HttpStatus.OK.toString(), "Categoria actualizada con exito");
        }catch(Exception e){
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al intentar actualizar la categoria");
        }
    }

    public responseDTO deleteCategorie(int categorie_id){
        Optional<Categorie> existingCategorie = findById(categorie_id);

        if (!existingCategorie.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "No se encontro la categoria buscada"
            );
        }

        try{
            data.deleteById(categorie_id);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Categoria eliminadacorrectamente"
            );
        }catch(Exception e){
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al intentar eliminar la categoria"
            );
        }
    }
}