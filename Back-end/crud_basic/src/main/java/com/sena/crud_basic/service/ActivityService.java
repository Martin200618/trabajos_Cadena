package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ActivityDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Activity;
import com.sena.crud_basic.repository.IActivity;

@Service
public class ActivityService {
    @Autowired
    private IActivity data;

    public List<Activity> findAll(){
        return data.findAll();
    }

    public Optional<Activity> findById(int Activity_id){
        return data.findById(Activity_id);
    }

    public responseDTO save(ActivityDTO ActivityDTO){
        if (ActivityDTO == null || ActivityDTO.getUser_id() == null) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Los datos de la actividad son invalidos");
        }

        Activity newActivity = new Activity();
        newActivity.setUser_id(ActivityDTO.getUser_id());
        newActivity.setAccion(ActivityDTO.getAccion());
        newActivity.setFecha(ActivityDTO.getFecha());

        try{
            data.save(newActivity);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Actividad registrada correctamente"
            );
        } catch (Exception e) {
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al registrar el proyecto");
        }
    }

    
}