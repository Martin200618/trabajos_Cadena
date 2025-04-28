package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.sql.Date;

import com.sena.crud_basic.DTO.VentasDTO;
import com.sena.crud_basic.model.Empleados;
import com.sena.crud_basic.model.Ventas;
import com.sena.crud_basic.repository.IEmpleadosRepository;
import com.sena.crud_basic.repository.IVentasRepository;

@Service
public class VentasService {

    @Autowired
    private IVentasRepository ventasRepository;

    @Autowired
    private IEmpleadosRepository empleadosRepository;

    // Obtener todas las ventas
    public List<Ventas> findAll() {
        return ventasRepository.findAll();
    }

    // Obtener venta por ID
    public Optional<Ventas> findById(int id) {
        return ventasRepository.findById(id);
    }

    // Registrar una venta
    public String save(VentasDTO ventasDTO) {
        Optional<Empleados> empleado = empleadosRepository.findById(Integer.parseInt(ventasDTO.getEmpleado())); // Conversion de String a Integer
        if (empleado.isEmpty()) {
            return HttpStatus.BAD_REQUEST + ": El empleado asociado no existe.";
        }

        try {
            Ventas venta = convertToModel(ventasDTO, empleado.get());
            ventasRepository.save(venta);
            return HttpStatus.OK + ": Venta registrada exitosamente.";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR + ": Error al registrar la venta. Detalles: " + e.getMessage();
        }
    }

    // Eliminar una venta por ID
    public String delete(int id) {
        Optional<Ventas> venta = findById(id);

        if (venta.isEmpty()) {
            return HttpStatus.BAD_REQUEST + ": La venta no existe o ya fue eliminada.";
        }

        ventasRepository.deleteById(id);
        return HttpStatus.OK + ": Venta eliminada correctamente.";
    }

    // Actualizar una venta por ID
    public String update(int id, VentasDTO ventasDTO) {
        Optional<Ventas> venta = findById(id);

        if (venta.isEmpty()) {
            return HttpStatus.BAD_REQUEST + ": La venta no existe o ya fue eliminada.";
        }

        Optional<Empleados> empleado = empleadosRepository.findById(Integer.parseInt(ventasDTO.getEmpleado())); // Conversion de String a Integer
        if (empleado.isEmpty()) {
            return HttpStatus.BAD_REQUEST + ": El empleado asociado no existe.";
        }

        try {
            Ventas updatedVenta = convertToModel(ventasDTO, empleado.get());
            updatedVenta.setVentas_id(id); // Establecer el ID de la venta existente
            ventasRepository.save(updatedVenta);
            return HttpStatus.OK + ": Venta actualizada exitosamente.";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR + ": Error al actualizar la venta. Detalles: " + e.getMessage();
        }
    }
    
    // Conversión de DTO a Modelo
    private Ventas convertToModel(VentasDTO ventasDTO, Empleados empleado) {
        try {
            return new Ventas(
                0, // ID autogenerado
                empleado,
                Date.valueOf(ventasDTO.getFecha()), // Conversion de String a Date
                ventasDTO.getTotal()
            );
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Formato de fecha incorrecto: " + ventasDTO.getFecha());
        }
    }
}
