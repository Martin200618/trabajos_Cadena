package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.suppliersDTO;
import com.sena.crud_basic.model.suppliers;
import com.sena.crud_basic.repository.ISuppliers;

@Service
public class suppliersService {
    @Autowired
    private ISuppliers data;

    public List<suppliers> findAll(){
        return data.findAll();
    }

    public Optional<suppliers> findById(int suppliersId){
        return data.findById(suppliersId);
    }

    public responseDTO deleteSuppliers(int suppliersId){
        if(!findById(suppliersId).isPresent()) {
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "el proveedor ya ha sido eliminado o no se encuentra"
            );
            return respuesta;
        }
        data.deleteById(suppliersId);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El proveedor se elimino correctamente"
        );
        return respuesta;
    }

    public responseDTO save(suppliersDTO suppliersDTO){
        suppliers suppliers = converToModel(suppliersDTO);
        data.save(suppliers);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El proveedor se guardo correctamente"
        );
        return respuesta;
    }

    public suppliersDTO converToDTO(suppliers suppliers){
        suppliersDTO suppliersDTO = new suppliersDTO(
            suppliers.getName(),
            suppliers.getContact(), 
            suppliers.getPhone()
        );
        return suppliersDTO;
    }

    public suppliers converToModel(suppliersDTO supplierDTO) {
        suppliers suppliers = new suppliers(
            0, 
            supplierDTO.getName(), 
            supplierDTO.getContact(), 
            supplierDTO.getPhone()
        );
        return suppliers;
    }

    public responseDTO update(int suppliersId, suppliersDTO suppliersDTO){
        Optional<suppliers> existingSuppliersOtp = data.findById(suppliersId);
        if(existingSuppliersOtp.isPresent()){
            suppliers existingSupplier = existingSuppliersOtp.get();
            existingSupplier.setName(suppliersDTO.getName());
            existingSupplier.setContact(suppliersDTO.getContact());
            existingSupplier.setPhone(suppliersDTO.getPhone());
            data.save(existingSupplier);
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "se actualizo correctamente"
            );
            return respuesta;
        }else{
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "El proveedor no existe"
            );
            return respuesta;
        }
    }
}