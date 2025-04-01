package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.shipmentsDTO;
import com.sena.crud_basic.model.shipments;
import com.sena.crud_basic.repository.Ishipments;

@Service
public class shipmentsService {
    @Autowired
    private Ishipments data;

    public List<shipments> findAll(){
        return data.findAll();
    }

    public Optional<shipments> findById(int shipmentId){
        return data.findById(shipmentId);
    }

    public responseDTO deleteShipments(int shipmentId){
        if(!findById(shipmentId).isPresent()){
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El envio que deseas eliminar no se encuentra o ya se elimino"
            );
            return respuesta;
        }
        data.deleteById(shipmentId);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "El envio se elimino correctamente"
        );
        return respuesta;
    }

    public responseDTO save(shipmentsDTO shipmentsDTO){
        shipments shipments = converToModel(shipmentsDTO);
        data.save(shipments);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El envio se guardo correctamente"
        );
        return respuesta;
    }

    public shipmentsDTO converToDTO(shipments shipments){
        shipmentsDTO shipmentsDTO = new shipmentsDTO(
            shipments.getOrderId(),
            shipments.getAddress(),
            shipments.getEstatus(),
            shipments.getDate()
        );
        return shipmentsDTO;
    }

    public shipments converToModel(shipmentsDTO shipmentsDTO){
        shipments shipments = new shipments(
        0,
        shipmentsDTO.getOrderId(),    
        shipmentsDTO.getAddress(),
        shipmentsDTO.getEstatus(),
        shipmentsDTO.getDate()
        );
        return shipments;
    }

    public responseDTO update(int shipmentId, shipmentsDTO shipmentsDTO){
        Optional<shipments> existingshipmentOtp = data.findById(shipmentId);
        if (existingshipmentOtp.isPresent()) {
            shipments existingShipments = existingshipmentOtp.get();
            existingShipments.setOrderId(shipmentsDTO.getOrderId());
            existingShipments.setAddress(shipmentsDTO.getAddress());
            existingShipments.setEstatus(shipmentsDTO.getEstatus());
            existingShipments.setDate(shipmentsDTO.getDate());

            data.save(existingShipments);
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "El envio se actualizo correctamente"
            );
            return respuesta;
        }else{
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "El envio no existe"
            );
            return respuesta;
        }
    }
}