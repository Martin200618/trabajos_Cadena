package com.sena.crud_basic.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.suppliersDTO;
import com.sena.crud_basic.service.suppliersService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("api/v1/suppliers")
public class suppliersController {
    @Autowired
    private suppliersService suppliersService;
    @PostMapping("/")
    public ResponseEntity<Object> registerSupplier(@RequestBody suppliersDTO suppliers){
        responseDTO respuesta = suppliersService.save(suppliers);
        return new ResponseEntity<>(respuesta,HttpStatus.OK);
    }
    @GetMapping("/")
    public ResponseEntity<Object> getAllSupplier(){
        var listaSupplier = suppliersService.findAll();
        return new ResponseEntity<>(listaSupplier,HttpStatus.OK);
    }
    @GetMapping("/get/{supplierId}")
    public ResponseEntity<Object> getOneSipplier(@PathVariable int supplierId){
        var suppliers = suppliersService.findById(supplierId);
        if (!suppliers.isPresent())
            return new ResponseEntity<>("error", HttpStatus.NOT_FOUND);
        return new ResponseEntity<>(suppliers,HttpStatus.OK);
    }
    @DeleteMapping("/delete/{supplierId}")
    public ResponseEntity<Object> deleteSupplier(@PathVariable int supplierId){
        var message = suppliersService.deleteSuppliers(supplierId);
        return new ResponseEntity<>(message,HttpStatus.OK);
    }
    @PostMapping("/update/{supplierId}")
    public ResponseEntity<Object> updateSupplier(@PathVariable int supplierId){
        var suppliers = suppliersService.findById(supplierId);
        if (!suppliers.isPresent())
            return new ResponseEntity<>("no se pudo actualizar",HttpStatus.NOT_FOUND);
        responseDTO respuesta = suppliersService.update(supplierId, suppliers);
        return new ResponseEntity<>(respuesta,HttpStatus.OK);
    }
}
