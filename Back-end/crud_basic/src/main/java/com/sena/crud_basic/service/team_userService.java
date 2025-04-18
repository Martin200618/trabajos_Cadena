package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.team_userDTO;
import com.sena.crud_basic.model.team_user;
import com.sena.crud_basic.repository.ITeam_user;

@Service
public class team_userService {
    @Autowired
    private ITeam_user data;

    public List<team_user> findAll(){
        return data.findAll();
    }

    public Optional<team_user> findById(int team_user_id){
        return data.findById(team_user_id);
    }

    public responseDTO save(team_userDTO team_userDTO){
        if (team_userDTO == null || team_userDTO.getTeam_id() == null || team_userDTO.getUser_id() == null) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Faltan datos necesarios para crear la union"
            );
        }

        team_user newtTeam_user = new team_user();
        newtTeam_user.setTeam_id(team_userDTO.getTeam_id());
        newtTeam_user.setUser_id(team_userDTO.getUser_id());

        try {
            data.save(newtTeam_user);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Union creada exitosamente"
            );
        }catch(Exception e){
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al intentar crear la union"
            );
        }
    }

    public responseDTO updateUnion(int team_user_id, team_userDTO team_userDTO){
        Optional<team_user> existingUnion = findById(team_user_id);
        if (!existingUnion.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "No se encontro la union que deseas actualizar"
            );
        }

        team_user team_userToUpdate = existingUnion.get();
        team_userToUpdate.setTeam_id(team_userDTO.getTeam_id());
        team_userToUpdate.setUser_id(team_userDTO.getUser_id());

        try {
            data.save(team_userToUpdate);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Union actualizada correctamente"
            );
        }catch (Exception e){
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al intentar actualizar la union"
            );
        }
    }

    public responseDTO deleteUnion(int team_user_id){
        Optional<team_user> existingUnion = findById(team_user_id);
        if (!existingUnion.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La union no ha sido encontrado o ya fue eliminada"
            );
        }

        try {
            data.deleteById(team_user_id);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Union eliminada exitosamente"
            );
        }catch (Exception e){
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al eliminar la union"
            );
        }
    }
}