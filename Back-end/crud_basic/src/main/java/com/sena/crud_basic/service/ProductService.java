package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.productsDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.products;
import com.sena.crud_basic.repository.Iproducts;


@Service
public class ProductService {
    @Autowired
    private Iproducts data;

    public List<products> findAll(){
        return data.findAll();
    }

    public Optional<products> fingById(int product_id){
        return data.findById(product_id);
    }

    public responseDTO deleteProducts(int products_id){
        if(!fingById(products_id).isPresent()){
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El producto que deseas eliminar no se encuentra o ya esta eliminado"
            );
            return respuesta;
        }
        data.deleteById(products_id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Se elimino correctamente el producto"
        );
        return respuesta;
    }

    public responseDTO save(productsDTO productsDTO){
        products products = converToModel(productsDTO);
        data.save(products);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "se guardo correctamente"
        );
        return respuesta;
    }

    public  productsDTO converToDTO( products products){
        productsDTO productsDTO =  new productsDTO(
            products.getProduct_id(),
            products.getCategorie_id(),
            products.getName(),
            products.getDescription(),
            products.getPrice(),
            products.getStock()
        );
        return productsDTO;
    }

    public products converToModel(productsDTO productsDTO){
        products products = new products(
            0,
            productsDTO.getName(),
            productsDTO.getDescription(),
            productsDTO.getPrice(),
            productsDTO.getStock(),
            productsDTO.getCategorie_id()
        );
        return products;
    }
}