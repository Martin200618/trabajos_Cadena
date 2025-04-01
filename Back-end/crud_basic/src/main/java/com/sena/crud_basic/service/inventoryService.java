package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.inventoryDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.inventory;
import com.sena.crud_basic.repository.Iinventory;

@Service
public class inventoryService {
    @Autowired
    private Iinventory data;

    public List<inventory> findAll(){
        return data.findAll();
    }

    public Optional<inventory> findById(int inventoryId){
        return data.findById(inventoryId);
    }

    public responseDTO deleteInventory(int inventoryId){
        if(!findById(inventoryId).isPresent()){
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "No se encontró el inventario"
            );
            return respuesta;
        }
        data.deleteById(inventoryId);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El inventario fue borrado"
        );
        return respuesta;
    }

    public responseDTO save(inventoryDTO inventoryDTO){
        inventory inventory = converToModel(inventoryDTO);
        data.save(inventory);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El inventario se guardó correctamente"
        );
        return respuesta;
    }

    public inventoryDTO converToDTO(inventory inventory){
        inventoryDTO inventoryDTO = new inventoryDTO(
            inventory.getProductId(),
            inventory.getSuppliersId(),
            inventory.getQuantity(),
            inventory.getLastUpdated()
        );
        return inventoryDTO;
    }

    public inventory converToModel(inventoryDTO inventoryDTO){
        inventory inventory = new inventory(
            0, 
            inventoryDTO.getProduct_id(), 
            inventoryDTO.getSuppliersId(), 
            inventoryDTO.getQuantity(), 
            inventoryDTO.getLastUpdated()
        );
        return inventory;
    }

    public responseDTO update(int inventoryId, inventoryDTO inventory){
        Optional<inventory> existingInventoryOtp = data.findById(inventoryId);
        if (existingInventoryOtp.isPresent()) {
            inventory existingInventory = existingInventoryOtp.get();
            existingInventory.setProductId(inventory.getProduct_id());
            existingInventory.setSuppliersId(inventory.getSuppliersId());
            existingInventory.setQuantity(inventory.getQuantity());
            existingInventory.setLastUpdated(inventory.getLastUpdated());
            data.save(existingInventory);
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "El inventario se actualizó correctamente"
            );
            return respuesta;
        } else {
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "El inventario no existe"
            );
            return respuesta;
        }
    }
}