package com.sena.crud_basic.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.GenerosDTO;
import com.sena.crud_basic.model.Generos;
import com.sena.crud_basic.repository.IGenerosRepository;


@Service
public class GenerosService {

    @Autowired
    private IGenerosRepository generosRepository;

    // Obtener todos los géneros
    public List<Generos> findAll() {
        return generosRepository.findAll();
    }

    // Obtener género por ID
    public Optional<Generos> findById(int id) {
        return generosRepository.findById(id);
    }

    // Registrar un género
    public String save(GenerosDTO generosDTO) {
        if (generosDTO == null || !isValidGenero(generosDTO)) {
            return HttpStatus.BAD_REQUEST.toString() + ": Los datos del género son inválidos";
        }

        Generos genero = convertToModel(generosDTO);
        generosRepository.save(genero);
        return HttpStatus.OK.toString() + ": Género registrado exitosamente";
    }

    // Actualizar un género
    public String update(int id, GenerosDTO generosDTO) {
        Optional<Generos> existingGenero = findById(id);

        if (existingGenero.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El género no existe o ya fue eliminado";
        }

        Generos generoToUpdate = existingGenero.get();
        generoToUpdate.setNombre(generosDTO.getNombre());
        generosRepository.save(generoToUpdate);
        return HttpStatus.OK.toString() + ": Género actualizado correctamente";
    }

    // Eliminar un género por ID
    public String delete(int id) {
        Optional<Generos> genero = findById(id);

        if (genero.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El género no existe o ya fue eliminado";
        }

        generosRepository.deleteById(id);
        return HttpStatus.OK.toString() + ": Género eliminado correctamente";
    }

    // Conversión de DTO a Modelo
    private Generos convertToModel(GenerosDTO generosDTO) {
        return new Generos(0, generosDTO.getNombre());
    }

    // Validación de datos de género
    private boolean isValidGenero(GenerosDTO generosDTO) {
        return generosDTO.getNombre() != null && !generosDTO.getNombre().trim().isEmpty()
                && generosDTO.getNombre().length() <= 50;
    }
}