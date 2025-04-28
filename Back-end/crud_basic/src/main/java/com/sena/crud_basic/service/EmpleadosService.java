package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.EmpleadosDTO;
import com.sena.crud_basic.model.Empleados;
import com.sena.crud_basic.repository.IEmpleadosRepository;


@Service
public class EmpleadosService {

    @Autowired
    private IEmpleadosRepository empleadosRepository;

    // Obtener todos los empleados
    public List<Empleados> findAll() {
        return empleadosRepository.findAll();
    }

    // Obtener empleado por ID
    public Optional<Empleados> findById(int id) {
        return empleadosRepository.findById(id);
    }

    // Registrar un empleado
    public String save(EmpleadosDTO empleadosDTO) {
        if (empleadosDTO == null || !isValidEmpleado(empleadosDTO)) {
            return HttpStatus.BAD_REQUEST.toString() + ": Los datos del empleado son inválidos";
        }

        Empleados empleado = convertToModel(empleadosDTO);
        empleadosRepository.save(empleado);
        return HttpStatus.OK.toString() + ": Empleado registrado exitosamente";
    }

    // Actualizar un empleado
    public String update(int id, EmpleadosDTO empleadosDTO) {
        Optional<Empleados> existingEmpleado = findById(id);

        if (existingEmpleado.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El empleado no existe o ya fue eliminado";
        }

        Empleados empleadoToUpdate = existingEmpleado.get();
        empleadoToUpdate.setNombre(empleadosDTO.getNombre());
        empleadoToUpdate.setPuesto(empleadosDTO.getPuesto());
        empleadoToUpdate.setEmail(empleadosDTO.getEmail());

        empleadosRepository.save(empleadoToUpdate);
        return HttpStatus.OK.toString() + ": Empleado actualizado correctamente";
    }

    // Eliminar empleado por ID
    public String delete(int id) {
        Optional<Empleados> empleado = findById(id);

        if (empleado.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El empleado no existe o ya fue eliminado";
        }

        empleadosRepository.deleteById(id);
        return HttpStatus.OK.toString() + ": Empleado eliminado correctamente";
    }

    // Conversión de DTO a Modelo
    private Empleados convertToModel(EmpleadosDTO empleadosDTO) {
        return new Empleados(0, empleadosDTO.getNombre(), empleadosDTO.getPuesto(), empleadosDTO.getEmail());
    }

    // Validación de datos de empleado
    private boolean isValidEmpleado(EmpleadosDTO empleadosDTO) {
        return empleadosDTO.getNombre() != null && !empleadosDTO.getNombre().trim().isEmpty()
                && empleadosDTO.getNombre().length() <= 100;
    }
}