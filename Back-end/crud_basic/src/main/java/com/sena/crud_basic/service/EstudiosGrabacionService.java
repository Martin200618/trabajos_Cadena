package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.EstudiosGrabacionDTO;
import com.sena.crud_basic.model.EstudiosGrabacion;
import com.sena.crud_basic.repository.IEstudiosGrabacionRepository;


@Service
public class EstudiosGrabacionService {

    @Autowired
    private IEstudiosGrabacionRepository estudiosRepository;

    // Obtener todos los estudios de grabación
    public List<EstudiosGrabacion> findAll() {
        return estudiosRepository.findAll();
    }

    // Obtener estudio por ID
    public Optional<EstudiosGrabacion> findById(int id) {
        return estudiosRepository.findById(id);
    }

    // Registrar un estudio de grabación
    public String save(EstudiosGrabacionDTO estudiosDTO) {
        if (estudiosDTO == null || !isValidEstudio(estudiosDTO)) {
            return HttpStatus.BAD_REQUEST.toString() + ": Los datos del estudio de grabación son inválidos";
        }

        EstudiosGrabacion estudio = convertToModel(estudiosDTO);
        estudiosRepository.save(estudio);
        return HttpStatus.OK.toString() + ": Estudio de grabación registrado exitosamente";
    }

    // Actualizar un estudio de grabación
    public String update(int id, EstudiosGrabacionDTO estudiosDTO) {
        Optional<EstudiosGrabacion> existingEstudio = findById(id);

        if (existingEstudio.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El estudio de grabación no existe o ya fue eliminado";
        }

        EstudiosGrabacion estudioToUpdate = existingEstudio.get();
        estudioToUpdate.setNombre(estudiosDTO.getNombre());
        estudioToUpdate.setCiudad(estudiosDTO.getCiudad());
        estudioToUpdate.setPais(estudiosDTO.getPais());

        estudiosRepository.save(estudioToUpdate);
        return HttpStatus.OK.toString() + ": Estudio de grabación actualizado correctamente";
    }

    // Eliminar estudio por ID
    public String delete(int id) {
        Optional<EstudiosGrabacion> estudio = findById(id);

        if (estudio.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El estudio de grabación no existe o ya fue eliminado";
        }

        estudiosRepository.deleteById(id);
        return HttpStatus.OK.toString() + ": Estudio de grabación eliminado correctamente";
    }

    // Conversión de DTO a Modelo
    private EstudiosGrabacion convertToModel(EstudiosGrabacionDTO estudiosDTO) {
        return new EstudiosGrabacion(0, estudiosDTO.getNombre(), estudiosDTO.getCiudad(), estudiosDTO.getPais());
    }

    // Validación de datos del estudio de grabación
    private boolean isValidEstudio(EstudiosGrabacionDTO estudiosDTO) {
        return estudiosDTO.getNombre() != null && !estudiosDTO.getNombre().trim().isEmpty()
                && estudiosDTO.getNombre().length() <= 100
                && estudiosDTO.getCiudad() != null && !estudiosDTO.getCiudad().trim().isEmpty()
                && estudiosDTO.getPais() != null && !estudiosDTO.getPais().trim().isEmpty();
    }
}