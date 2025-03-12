package com.sena.crud_basic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.model.pedidos;
import com.sena.crud_basic.DTO.pedidosDTO;
import com.sena.crud_basic.repository.Ipedidos;

@Service
public class pedidosService {
    @Autowired
    private Ipedidos data;
    public void save(pedidosDTO pedidosDTO){
        pedidos pedidosRegister = converToModel(pedidosDTO);
        data.save(pedidosRegister);
    }    
    public pedidosDTO converToDTO(pedidos pedidos){
        pedidosDTO pedidosDTO = new pedidosDTO(    
        pedidos.getfecha_pedido(),
        pedidos.gettotal(),
        pedidos.getestado());
        return pedidosDTO;
    }
    public pedidos converToModel(pedidosDTO pedidosDTO){
        pedidos pedidos = new pedidos(
            0,
            pedidosDTO.getfecha_pedido(),
            pedidosDTO.gettotal(),
            pedidosDTO.geestado());
            return pedidos;
    }
}
