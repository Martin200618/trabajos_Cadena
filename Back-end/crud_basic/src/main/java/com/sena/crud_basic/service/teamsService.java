package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.teamsDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.teams;
import com.sena.crud_basic.repository.ITeams;

@Service
public class teamsService {

    @Autowired
    private ITeams data;

    // Obtener todos los equipos
    public List<teams> findAll() {
        return data.findAll();
    }

    // Obtener un equipo por ID
    public Optional<teams> findById(int teamId) {
        return data.findById(teamId);
    }

    // Registrar un nuevo equipo
    public responseDTO save(teamsDTO teamsDTO) {
        if (teamsDTO == null || teamsDTO.getNombre() == null || teamsDTO.getDescripcion() == null) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "Todos los campos son obligatorios");
        }

        teams newTeam = new teams();
        newTeam.setNombre(teamsDTO.getNombre());
        newTeam.setDescripcion(teamsDTO.getDescripcion());

        try {
            data.save(newTeam);
            return new responseDTO(HttpStatus.OK.toString(), "Equipo registrado exitosamente");
        } catch (Exception e) {
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al registrar el equipo");
        }
    }

    // Actualizar un equipo existente
    public responseDTO update(int teamId, teamsDTO teamsDTO) {
        Optional<teams> existingTeam = findById(teamId);

        if (!existingTeam.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "No encontramos el equipo");
        }

        teams teamToUpdate = existingTeam.get();
        teamToUpdate.setNombre(teamsDTO.getNombre());
        teamToUpdate.setDescripcion(teamsDTO.getDescripcion());

        try {
            data.save(teamToUpdate);
            return new responseDTO(HttpStatus.OK.toString(), "Equipo actualizado correctamente");
        } catch (Exception e) {
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al actualizar el equipo");
        }
    }

    // Eliminar un equipo por ID
    public responseDTO deleteTeam(int teamId) {
        Optional<teams> existingTeam = findById(teamId);
        if (!existingTeam.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(), "El equipo no existe o ya fue eliminado");
        }

        try {
            data.deleteById(teamId);
            return new responseDTO(HttpStatus.OK.toString(), "Equipo eliminado correctamente");
        } catch (Exception e) {
            return new responseDTO(HttpStatus.INTERNAL_SERVER_ERROR.toString(), "Error al eliminar el equipo");
        }
    }
}