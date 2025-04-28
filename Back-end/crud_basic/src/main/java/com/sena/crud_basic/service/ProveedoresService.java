package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.ProveedoresDTO;
import com.sena.crud_basic.model.Proveedores;
import com.sena.crud_basic.repository.IProveedoresRepository;


@Service
public class ProveedoresService {

    @Autowired
    private IProveedoresRepository proveedoresRepository;

    // Obtener todos los proveedores
    public List<Proveedores> findAll() {
        return proveedoresRepository.findAll();
    }

    // Obtener proveedor por ID
    public Optional<Proveedores> findById(int id) {
        return proveedoresRepository.findById(id);
    }

    // Registrar un proveedor
    public String save(ProveedoresDTO proveedoresDTO) {
        if (proveedoresDTO == null || !isValidProveedor(proveedoresDTO)) {
            return HttpStatus.BAD_REQUEST.toString() + ": Los datos del proveedor son inválidos";
        }

        Proveedores proveedor = convertToModel(proveedoresDTO);
        proveedoresRepository.save(proveedor);
        return HttpStatus.OK.toString() + ": Proveedor registrado exitosamente";
    }

    // Actualizar un proveedor
    public String update(int id, ProveedoresDTO proveedoresDTO) {
        Optional<Proveedores> existingProveedor = findById(id);

        if (existingProveedor.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El proveedor no existe o ya fue eliminado";
        }

        Proveedores proveedorToUpdate = existingProveedor.get();
        proveedorToUpdate.setNombre(proveedoresDTO.getNombre());
        proveedorToUpdate.setContacto(proveedoresDTO.getContacto());
        proveedorToUpdate.setTelefono(proveedoresDTO.getTelefono());

        proveedoresRepository.save(proveedorToUpdate);
        return HttpStatus.OK.toString() + ": Proveedor actualizado correctamente";
    }

    // Eliminar un proveedor por ID
    public String delete(int id) {
        Optional<Proveedores> proveedor = findById(id);

        if (proveedor.isEmpty()) {
            return HttpStatus.BAD_REQUEST.toString() + ": El proveedor no existe o ya fue eliminado";
        }

        proveedoresRepository.deleteById(id);
        return HttpStatus.OK.toString() + ": Proveedor eliminado correctamente";
    }

    // Conversión de DTO a Modelo
    private Proveedores convertToModel(ProveedoresDTO proveedoresDTO) {
        return new Proveedores(0, proveedoresDTO.getNombre(), proveedoresDTO.getContacto(), proveedoresDTO.getTelefono());
    }

    // Validación de datos de proveedor
    private boolean isValidProveedor(ProveedoresDTO proveedoresDTO) {
        return proveedoresDTO.getNombre() != null && !proveedoresDTO.getNombre().trim().isEmpty()
                && proveedoresDTO.getNombre().length() <= 100;
    }
}