package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.proyectDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.proyects;
import com.sena.crud_basic.repository.Iproyects;

@Service
public class proyectService {

    @Autowired
    private Iproyects data;

    private static final int MAX_PROJECTS = 100; // Límite máximo de proyectos permitidos

    // Obtener todos los proyectos
    public List<proyects> findAll() {
        return data.findAll();
    }

    // Contar el total de proyectos (nuevo método)
    public int countAllProyects() {
        return (int) data.count(); // Consulta optimizada
    }

    // Obtener un proyecto por ID
    public Optional<proyects> findById(int proyectId) {
        return data.findById(proyectId);
    }

    // Registrar un nuevo proyecto con validación de límite
    public responseDTO save(proyectDTO proyectDTO) {
        if (
            proyectDTO == null || 
            proyectDTO.getName() == null || 
            proyectDTO.getDescripcion() == null || 
            proyectDTO.getimagenBase64() == null
        ){
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Todos los campos son obligatorios"
            );
        }

        // Verificar límite de proyectos
        int currentCount = countAllProyects();
        if (currentCount >= MAX_PROJECTS) {
            return new responseDTO(
                HttpStatus.FORBIDDEN.toString(), 
                "No se pueden agregar más proyectos. Límite alcanzado."
            );
        }

        // Crear y guardar el nuevo proyecto
        proyects newProyect = new proyects();
        newProyect.setNombre(proyectDTO.getName());
        newProyect.setDescripcion(proyectDTO.getDescripcion());
        newProyect.setImagenBase64(proyectDTO.getimagenBase64());

        try {
            data.save(newProyect);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Proyecto registrado exitosamente"
            );
        } catch (Exception e) {
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al registrar el proyecto"
            );
        }
    }

    // Actualizar un proyecto existente
    public responseDTO update(int proyectId, proyectDTO proyectDTO) {
        Optional<proyects> existingProyect = findById(proyectId);

        if (!existingProyect.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "No encontramos el proyecto"
            );
        }

        // Actualizar los valores del proyecto
        proyects proyectToUpdate = existingProyect.get();
        proyectToUpdate.setNombre(proyectDTO.getName());
        proyectToUpdate.setImagenBase64(proyectDTO.getimagenBase64());
        proyectToUpdate.setDescripcion(proyectDTO.getDescripcion());

        try {
            data.save(proyectToUpdate);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Proyecto actualizado correctamente"
            );
        } catch (Exception e) {
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al actualizar el proyecto"
            );
        }
    }

    // Eliminar un proyecto por ID
    public responseDTO deleteProyects(int proyectId) {
        Optional<proyects> existingProyect = findById(proyectId);
        if (!existingProyect.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "El proyecto no existe o ya fue eliminado"
            );
        }

        try {
            data.deleteById(proyectId);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Proyecto eliminado correctamente"
            );
        } catch (Exception e) {
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al eliminar el proyecto"
            );
        }
    }
}