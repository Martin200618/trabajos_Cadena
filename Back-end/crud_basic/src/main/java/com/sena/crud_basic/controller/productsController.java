package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.productsDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.service.ProductService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/products")
public class productsController {
    @Autowired
    private ProductService productService;

    @PostMapping("/")
    public ResponseEntity<Object> registerProduct(@RequestBody productsDTO products){
        responseDTO respuesta = productService.save(products);
        return new ResponseEntity<>(respuesta,HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<Object> getAllProducts(){
        var listaProductos =  productService.findAll();
        return new ResponseEntity<>(listaProductos,HttpStatus.OK);
    }
    @GetMapping("/get/{products_id}")
    public ResponseEntity<Object> getOneProduct(@PathVariable int product_id){
        var producto = productService.fingById(product_id);
        return new ResponseEntity<>(producto,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{products_id}")
    public ResponseEntity<Object> deleteProducts(@PathVariable int product_id){
        var message = productService.deleteProducts(product_id);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
}
