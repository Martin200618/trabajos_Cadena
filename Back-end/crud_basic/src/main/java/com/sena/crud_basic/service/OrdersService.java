package com.sena.crud_basic.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.OrdersDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Orders;
import com.sena.crud_basic.repository.IOrders;

import java.util.List;
import java.util.Optional;

@Service
public class OrdersService {
    @Autowired
    private IOrders data;

    public List<Orders> finAll(){
        return data.findAll();
    }
    public Optional<Orders> findById(int orderID){
        return data.findById(orderID);
    }
    public responseDTO deleteOrders(int orderId){
        if(!findById(orderId).isPresent()){
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La orden ya fue eliminada o no se encuentra"
            );
            return respuesta;
        }
        data.deleteById(orderId);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "La Orden fue eliminada correctamente"
            );
        return respuesta;
    }
    public responseDTO save(OrdersDTO OrdersDTO){
        Orders Orders = converToModel(OrdersDTO);
        data.save(Orders);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(), 
            "Se guardo correctamente"
            );
        return respuesta;
    }
    public OrdersDTO converToDTO(Orders Orders){
        OrdersDTO OrdersDTO = new OrdersDTO
        (0, 
        Orders.getUser(), 
        Orders.getOrderDate(), 
        Orders.getTotal(), 
        Orders.getStatus()
        );
        return OrdersDTO;
    }
    public Orders converToModel(OrdersDTO OrdenDTO){
        Orders Orders = new Orders(
            0, 
            OrdenDTO.getUser(),
            OrdenDTO.getOrderDate(),
            OrdenDTO.getTotal(),
            OrdenDTO.getStatus()
            );
        return Orders;
    }
}
