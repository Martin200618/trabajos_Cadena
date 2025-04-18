package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.user_proyectDTO;
import com.sena.crud_basic.model.user_proyects;
import com.sena.crud_basic.repository.IUser_proyects;

@Service
public class user_proyectService {
    @Autowired
    private IUser_proyects data;

    public List<user_proyects> findAll(){
        return data.findAll();
    }

    public Optional<user_proyects> findById(int user_proyect_id){
        return data.findById(user_proyect_id);
    }

    public responseDTO save(user_proyectDTO user_proyectDTO){
        if (user_proyectDTO == null || user_proyectDTO.getUser_id() == null || user_proyectDTO.getProyect_id() == null) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "Faltan algunos campos"
            );
        }

        user_proyects newUser_proyects = new user_proyects();
        newUser_proyects.setProyect_id(user_proyectDTO.getProyect_id());
      newUser_proyects.setUser_id(user_proyectDTO.getUser_id());

        try {
            data.save(newUser_proyects);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Anclaje registrado exitosamente"
            );
        }catch (Exception e){
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                "Error al registrar el anclaje"
            );
        }
    }

    public responseDTO update(int user_proyect_id, user_proyectDTO user_proyectDTO){
        Optional<user_proyects> existingAnclaje = findById(user_proyect_id);
        if (!existingAnclaje.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "Anclaje no encontrado"
            );
        }

        user_proyects anclajeToUpdate = existingAnclaje.get();
        anclajeToUpdate.setProyect_id(user_proyectDTO.getProyect_id());
        anclajeToUpdate.setUser_id(user_proyectDTO.getUser_id());

        try {
            data.save(anclajeToUpdate);
            return new responseDTO(
                HttpStatus.OK.toString(),
                "Anclaje actualizado exitosamente"
            );
        } catch (Exception e) {
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                "Error al actualizar el anclaje"
            );
        }
    }

    public responseDTO delete(int user_proyect_id){
        Optional<user_proyects> existingAnclaje = findById(user_proyect_id);
        if (!existingAnclaje.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El anclaje no existe o ya fue eliminado"
            );
        }

        try {
            data.deleteById(user_proyect_id);
            return new responseDTO(
                HttpStatus.OK.toString(),
                "Anclaje eliminado exitosamente"
            );
        } catch (Exception e) {
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                "Error al eliminar el anclaje"
            );
        }
    }
}