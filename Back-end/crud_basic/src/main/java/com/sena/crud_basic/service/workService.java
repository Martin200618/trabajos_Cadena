package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.workDTO;
import com.sena.crud_basic.model.work;
import com.sena.crud_basic.repository.IWork;

@Service
public class workService {
    @Autowired
    private IWork data;

    public List<work> findAll() {
        return data.findAll();
    }

    public Optional<work> findById(int work_Id) {
        return data.findById(work_Id);
    }

    public responseDTO save(workDTO workDTO){
        if (workDTO == null || workDTO.getTitulo() == null || workDTO.getStateWork() == null) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "Faltan algunos campos"
            );
        }

        work newWork = new work();
        newWork.setProyectId(workDTO.getProyect_id());
        newWork.setStateWork(workDTO.getStateWork());
        newWork.setTitulo(workDTO.getTitulo());

        try {
            data.save(newWork);
            return new responseDTO(
                HttpStatus.OK.toString(),
                "Trabajo registrado exitosamente"
            );
        } catch (Exception e) {
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                "Error al registrar el trabajo"
            );
        }
    }

    public responseDTO update(int work_Id, workDTO workDTO) {
        Optional<work> existingWork = findById(work_Id);
        if (!existingWork.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "Trabajo no encontrado"
            );
        }
        work workToUpdate = existingWork.get();
        workToUpdate.setTitulo(workDTO.getTitulo());
        workToUpdate.setStateWork(workDTO.getStateWork());
        workToUpdate.setProyectId(workDTO.getProyect_id());

        try {
            data.save(workToUpdate);
            return new responseDTO(
                HttpStatus.OK.toString(),
                "Trabajo actualizado correctamente"
            );
        } catch (Exception e) {
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                "Error al actualizar el trabajo"
            );
        }
    }

    public responseDTO deleteWork(int work_Id) {
        Optional<work> existingWork = findById(work_Id);
        if (!existingWork.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El trabajo no existe o ya fue eliminado"
            );
        }

        try {
            data.deleteById(work_Id);
            return new responseDTO(
                HttpStatus.OK.toString(),
                "Trabajo eliminado correctamente"
            );
        } catch (Exception e) {
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(),
                "Error al eliminar el trabajo"
            );
        }
    }
}