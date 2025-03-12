package com.sena.crud_basic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.model.detalle_pedido;
import com.sena.crud_basic.DTO.detalle_pedidoDTO;
import com.sena.crud_basic.repository.Idetalle_pedido;

@Service
public class detalle_pedidoService {
    @Autowired
    private Idetalle_pedido data;
    public void save(detalle_pedidoDTO detalle_pedidoDTO){
        detalle_pedido detalle_pedidoRegister = converToModel(detalle_pedidoDTO);
        data.save(detalle_pedidoRegister);
    }
    public detalle_pedidoDTO converToDTO(detalle_pedido detalle_pedido){
        detalle_pedidoDTO detalle_pedidoDTO = new detalle_pedidoDTO(
            detalle_pedido.getcantidad(),
            detalle_pedido.getsubtotal());
        return detalle_pedidoDTO;
    }
    public detalle_pedido converToModel(detalle_pedidoDTO detalle_pedidoDTO){
        detalle_pedido detalle_pedido = new detalle_pedido(
            0,
            detalle_pedidoDTO.getcantidad(),
            detalle_pedidoDTO.getsubtotal());
        return detalle_pedido;
    }
}