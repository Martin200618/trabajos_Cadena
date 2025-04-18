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
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al registrar el proyecto"
            );
        }
    }

    public responseDTO update(int Activity_id, ActivityDTO ActivityDTO){
        if (Activity_id <= 0 || ActivityDTO == null) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Los datos de la actividad son invalidos"
            );
        }

        Optional<Activity> existingActivity = data.findById(Activity_id);
        if (!existingActivity.isPresent()) {
            return new responseDTO(
                HttpStatus.NOT_FOUND.toString(), 
                "Actividad no encontrada"
            );
        }

        Activity activityToUpdate = existingActivity.get();
        activityToUpdate.setUser_id(ActivityDTO.getUser_id());
        activityToUpdate.setAccion(ActivityDTO.getAccion());
        activityToUpdate.setFecha(ActivityDTO.getFecha());

        try{
            data.save(activityToUpdate);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Actividad actualizada correctamente"
            );
        } catch (Exception e) {
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al actualizar la actividad"
            );
        }
    }   

    public responseDTO deleteActivity(int Activity_id){
        Optional<Activity> existingActivity = findById(Activity_id);
        if (!existingActivity.isPresent()) {
            return new responseDTO(
                HttpStatus.NOT_FOUND.toString(),    
                "Actividad no encontrada"
            );
        }

        try{
            data.deleteById(Activity_id);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Actividad eliminada correctamente"
            );
        } catch (Exception e) {
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al eliminar la actividad"
            );
        }
    }
}