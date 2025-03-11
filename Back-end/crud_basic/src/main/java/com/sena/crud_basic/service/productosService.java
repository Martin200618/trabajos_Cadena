package com.sena.crud_basic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.model.productos;
import com.sena.crud_basic.DTO.productosDTO;
import com.sena.crud_basic.repository.Iproductos;

@Service
public class productosService {
    @Autowired
    private Iproductos data;
    public void save(productosDTO productosDTO){
        productos productosRegister = converToModel(productosDTO);
        data.save(productosRegister);
    }
    public productosDTO converToDTO(productos productos){
        productosDTO productosDTO = new productosDTO(
            productos.getnombre(),
            productos.getdescripcion(),
            productos.getprecio(),
            productos.getcantidad());
        return productosDTO;
    }
    public productos converToModel(productosDTO productosDTO){
        productos productos = new productos(
            0,
            productosDTO.getnombre(),
            productosDTO.getdescripcion(),
            productosDTO.getprecio(),
            productosDTO.getcantidad());
        return productos;
    }
}
