package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.Activity_proyectDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Activity_proyect;
import com.sena.crud_basic.repository.IActivity_proyect;

@Service
public class Activity_proyectService {
    @Autowired
    private IActivity_proyect data;

    public List<Activity_proyect> findAll(){
        return data.findAll();
    }

    public Optional<Activity_proyect> findById(int activity_proyect_id){
        return data.findById(activity_proyect_id);
    }

    public responseDTO save(Activity_proyectDTO Activity_proyectDTO){
        if(Activity_proyectDTO == null || Activity_proyectDTO.getActivity_id() == null || Activity_proyectDTO.getProyect_id() == null){
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Todos los campos son necesarios");
        }

        Activity_proyect newActivity_proyect = new Activity_proyect();
        newActivity_proyect.setActivity_id(Activity_proyectDTO.getActivity_id());
        newActivity_proyect.setProyect_id(Activity_proyectDTO.getProyect_id());

        try{
            data.save(newActivity_proyect);
            return new responseDTO(HttpStatus.OK.toString(), "Vinculacion exitosa");
        }catch (Exception e){
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al registrar la nueva vinculacion");
        }
    }

    public responseDTO update(int activity_proyect_id, Activity_proyectDTO Activity_proyectDTO){
        Optional<Activity_proyect> existingVinvul =  findById(activity_proyect_id);

        if (!existingVinvul.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "No se encontro algun vinculo");
        }

        Activity_proyect Activity_proyectToUpdate = existingVinvul.get();
        Activity_proyectToUpdate.setActivity_id(Activity_proyectDTO.getActivity_id());
        Activity_proyectToUpdate.setProyect_id(Activity_proyectDTO.getProyect_id());

        try{
            data.save(Activity_proyectToUpdate);
            return new responseDTO(HttpStatus.OK.toString(), "Actualizacion actualizada correctamente");
        }catch(Exception e){
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al intentar actualizar la vinculacion");
        }
    }

    public responseDTO deleteActivity_proyect(int activity_proyect_id){
        Optional<Activity_proyect> existingVincul = findById(activity_proyect_id);

        if (!existingVincul.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "La vinculacion no existe o ya fue eliminada");
        }

        try{
            data.deleteById(activity_proyect_id);
            return new responseDTO(HttpStatus.OK.toString(), "Vinculacion eliminada correctamente");
        }catch (Exception e){
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al intentar eliminar la vinculacion");
        }
    }
}