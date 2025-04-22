package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.sena.crud_basic.DTO.filasDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.filas;
import com.sena.crud_basic.repository.Ifilas;

@Service
public class filasService {

    @Autowired
    private Ifilas data;

    private static final int MAX_PROJECTS = 100; // Límite máximo de proyectos permitidos

    // Obtener todos los proyectos
    public List<filas> findAll() {
        return data.findAll();
    }

    // Contar el total de proyectos (nuevo método)
    public int countAllFilas() {
        return (int) data.count(); // Consulta optimizada
    }

    // Obtener un proyecto por ID
    public Optional<filas> findById(int filasId) {
        return data.findById(filasId);
    }

    // Registrar un nuevo proyecto con validación de límite
    public responseDTO save(filasDTO filasDTO) {
        if (filasDTO.getNumero() == 0 || filasDTO.getDescripcion() == null || filasDTO.getDescripcion() == null
        ){
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Todos los campos son obligatorios"
            );
        }

        // Verificar límite de proyectos
        int currentCount = countAllFilas();
        if (currentCount >= MAX_PROJECTS) {
            return new responseDTO(
                HttpStatus.FORBIDDEN.toString(), 
                "No se pueden agregar más filas. Límite alcanzado."
            );
        }

        // Crear y guardar el nuevo proyecto
        filas newFilas = new filas();
        newFilas.setNumero(filasDTO.getNumero());
        newFilas.setDescripcion(filasDTO.getDescripcion());

        try {
            data.save(newFilas);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Filas registrado exitosamente"
            );
        } catch (Exception e) {
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al registrar la fila"
            );
        }
    }

    // Actualizar un proyecto existente
    public responseDTO update(int filasId, filasDTO filasDTO) {
        Optional<filas> existingFilas = findById(filasId);

        if (!existingFilas.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "No encontramos la filas"
            );
        }

        // Actualizar los valores del proyecto
        filas filasToUpdate = existingFilas.get();
        filasToUpdate.setNumero(filasDTO.getNumero());
        filasToUpdate.setDescripcion(filasDTO.getDescripcion());

        try {
            data.save(filasToUpdate);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Filas actualizado correctamente"
            );
        } catch (Exception e) {
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al actualizar la fila"
            );
        }
    }

    // Eliminar un proyecto por ID
    public responseDTO deleteFilas(int filasId) {
        Optional<filas> existingFilas = findById(filasId);
        if (!existingFilas.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "El filas no existe o ya fue eliminado"
            );
        }

        try {
            data.deleteById(filasId);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Fila eliminada correctamente"
            );
        } catch (Exception e) {
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al eliminar la fila"
            );
        }
    }
}