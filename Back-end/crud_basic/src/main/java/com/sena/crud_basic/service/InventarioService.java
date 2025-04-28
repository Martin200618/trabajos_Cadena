package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.InventarioDTO;
import com.sena.crud_basic.model.Albumes;
import com.sena.crud_basic.model.Inventario;
import com.sena.crud_basic.model.Proveedores;
import com.sena.crud_basic.repository.IAlbumesRepository;
import com.sena.crud_basic.repository.IInventarioRepository;
import com.sena.crud_basic.repository.IProveedoresRepository;

import java.sql.Date;

@Service
public class InventarioService {

    @Autowired
    private IInventarioRepository inventarioRepository;

    @Autowired
    private IAlbumesRepository albumesRepository;

    @Autowired
    private IProveedoresRepository proveedoresRepository;

    // Obtener todos los inventarios
    public List<Inventario> findAll() {
        return inventarioRepository.findAll();
    }

    // Buscar inventario por ID
    public Optional<Inventario> findById(int id) {
        return inventarioRepository.findById(id);
    }

    // Guardar inventario
    public String save(InventarioDTO inventarioDTO) {
        // Validar álbum
        Optional<Albumes> album = albumesRepository.findById(inventarioDTO.getAlbum());
        if (album.isEmpty()) {
            return HttpStatus.BAD_REQUEST + ": El álbum asociado no existe.";
        }

        // Validar proveedor
        Optional<Proveedores> proveedor = proveedoresRepository.findById(inventarioDTO.getProveedor());
        if (proveedor.isEmpty()) {
            return HttpStatus.BAD_REQUEST + ": El proveedor asociado no existe.";
        }

        // Validar stock
        if (inventarioDTO.getStock() <= 0) {
            return HttpStatus.BAD_REQUEST + ": El stock debe ser mayor a cero.";
        }

        // Validar fecha
        if (inventarioDTO.getFechaEntrada() == null || inventarioDTO.getFechaEntrada().isEmpty()) {
            return HttpStatus.BAD_REQUEST + ": La fecha de entrada es obligatoria.";
        }

        try {
            Inventario inventario = convertToModel(inventarioDTO, album.get(), proveedor.get());
            inventarioRepository.save(inventario);
            return HttpStatus.OK + ": Entrada de inventario registrada exitosamente.";
        } catch (Exception e) {
            return HttpStatus.INTERNAL_SERVER_ERROR + ": Error al guardar el inventario.";
        }
    }

    // Actualizar inventario
    public String update(int id, InventarioDTO inventarioDTO) {
        Optional<Inventario> existingInventario = findById(id);
        if (existingInventario.isEmpty()) {
            return HttpStatus.BAD_REQUEST + ": La entrada de inventario no existe o ya fue eliminada.";
        }

        // Validar álbum
        Optional<Albumes> album = albumesRepository.findById(inventarioDTO.getAlbum());
        if (album.isEmpty()) {
            return HttpStatus.BAD_REQUEST + ": El álbum asociado no existe.";
        }

        // Validar proveedor
        Optional<Proveedores> proveedor = proveedoresRepository.findById(inventarioDTO.getProveedor());
        if (proveedor.isEmpty()) {
            return HttpStatus.BAD_REQUEST + ": El proveedor asociado no existe.";
        }

        // Validar stock
        if (inventarioDTO.getStock() <= 0) {
            return HttpStatus.BAD_REQUEST + ": El stock debe ser mayor a cero.";
        }

        // Validar fecha
        if (inventarioDTO.getFechaEntrada() == null || inventarioDTO.getFechaEntrada().isEmpty()) {
            return HttpStatus.BAD_REQUEST + ": La fecha de entrada es obligatoria.";
        }

        Inventario inventarioToUpdate = existingInventario.get();
        inventarioToUpdate.setAlbum(album.get());
        inventarioToUpdate.setProveedor(proveedor.get());
        inventarioToUpdate.setStock(inventarioDTO.getStock());
        inventarioToUpdate.setFechaEntrada(Date.valueOf(inventarioDTO.getFechaEntrada())); // Conversión de String a Date

        inventarioRepository.save(inventarioToUpdate);
        return HttpStatus.OK + ": Entrada de inventario actualizada correctamente.";
    }

    // Eliminar inventario
    public String delete(int id) {
        Optional<Inventario> inventario = findById(id);
        if (inventario.isEmpty()) {
            return HttpStatus.BAD_REQUEST + ": La entrada de inventario no existe o ya fue eliminada.";
        }

        inventarioRepository.deleteById(id);
        return HttpStatus.OK + ": Entrada de inventario eliminada correctamente.";
    }

    // Conversión de DTO a Modelo
    private Inventario convertToModel(InventarioDTO inventarioDTO, Albumes album, Proveedores proveedor) {
        return new Inventario(
            0,
            album,
            proveedor,
            inventarioDTO.getStock(),
            Date.valueOf(inventarioDTO.getFechaEntrada()) // Conversión de String a Date
        );
    }
}