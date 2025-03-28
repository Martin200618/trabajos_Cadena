package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.orderDetailsDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.orderDetails;
import com.sena.crud_basic.repository.IOrderDetails;

@Service
public class orderDetailsService {
    @Autowired
    
    private IOrderDetails data;

    public List<orderDetails> findAll(){
        return data.findAll();
    }
    
    public Optional<orderDetails> findById(int orderDetails_id){
        return data.findById(orderDetails_id);
    }
    
    public responseDTO deleteOrderDetail(int orderDetails_id){
        if(!findById(orderDetails_id).isPresent()){
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "El detalle de la orden no se encontro o ya se elimino"
                );
            return respuesta;
        }
        data.deleteById(orderDetails_id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El detalle de la orden fue eliminado correctamente"
        );
        return respuesta;
    };

    public responseDTO save(orderDetailsDTO orderDetailsDTO){
        converToModel(orderDetailsDTO);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "se guardo correctamente"
        );
        return respuesta;
    }
    
    public responseDTO update(int orderDetails_id, orderDetailsDTO orderDetailsDTO) {
        Optional<orderDetails> existingOrderDetail = findById(orderDetails_id);
        if (existingOrderDetail.isPresent()) {
            orderDetails orderDetailToUpdate = existingOrderDetail.get();
            orderDetailToUpdate.setProduct_id(orderDetailsDTO.getProduct_id());
            orderDetailToUpdate.setQuantity((int) orderDetailsDTO.getQuantity());
            orderDetailToUpdate.setSubtotal(orderDetailsDTO.getSubtotal());
            orderDetailToUpdate.setImage(orderDetailsDTO.getImagen());
            data.save(orderDetailToUpdate);
            return new responseDTO(HttpStatus.OK.toString(), "Order detail updated successfully");
        } else {
            return new responseDTO(HttpStatus.NOT_FOUND.toString(), "Order detail not found");
        }
    }

    public orderDetailsDTO converToDTO(orderDetails orderDetails){
        orderDetailsDTO orderDetailsDTO = new orderDetailsDTO(
            orderDetails.getOrder_id(),
            orderDetails.getProduct_id(),
            orderDetails.getQuantity(),
            orderDetails.getSubtotal(),
            orderDetails.getImage()
        );
        return orderDetailsDTO;
    }

    public orderDetails converToModel(orderDetailsDTO orderDetailsDTO){
        orderDetails orderDetails = new orderDetails(
            0,
            orderDetailsDTO.getOrder_id(),
            orderDetailsDTO.getProduct_id(),
            (int) orderDetailsDTO.getQuantity(),
            orderDetailsDTO.getSubtotal(),
            orderDetailsDTO.getImagen()
        );
        return orderDetails;
    }
}