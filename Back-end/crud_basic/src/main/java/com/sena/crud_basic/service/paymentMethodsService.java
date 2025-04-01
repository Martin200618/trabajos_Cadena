package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.paymentMethodsDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.paymentMethods;
import com.sena.crud_basic.repository.IPaymentMethods;

@Service
public class paymentMethodsService {
    @Autowired
    private IPaymentMethods data;

    public List<paymentMethods> finAll(){
        return data.findAll();
    }

    public Optional<paymentMethods> findById(int paymentId){
        return data.findById(paymentId);
    }

    public responseDTO deleteMethods(int paymentId){
        if(!findById(paymentId).isPresent()){
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El metodo de pago que deseas eliminar no se encuentra o ya se elimino"
            );
            return respuesta;
        }
        data.deleteById(paymentId);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "El metodo de pago que deseas eliminar no se"
        );
        return respuesta;
    }

    public responseDTO save(paymentMethodsDTO paymentMethodsDTO){
        paymentMethods paymentMethods = converToModel(paymentMethodsDTO);
        data.save(paymentMethods);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "se guardo correctamente"
        );
        return respuesta;
    }

    public paymentMethodsDTO converToDTO(paymentMethods paymentMethods){
        paymentMethodsDTO paymentMethodsDTO = new paymentMethodsDTO(
            paymentMethods.getMethod()
        );
        return paymentMethodsDTO;
    }

    public paymentMethods converToModel(paymentMethodsDTO paymentMethodsDTO){
        paymentMethods paymentMethods = new paymentMethods(
            0,
            paymentMethodsDTO.getMethod()
        );
        return paymentMethods;
    }

    public responseDTO update(int paymentId, String method){
        Optional<paymentMethods> existingMethodOtp = data.findById(paymentId);
        if(existingMethodOtp.isPresent()){
            paymentMethods paymentMethods = existingMethodOtp.get();
            paymentMethods.setMethod(method);
            data.save(paymentMethods);
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "se actualizo correctamente"
            );
            return respuesta;
        }else{
            responseDTO respuesta = new responseDTO(
                HttpStatus.NOT_FOUND.toString(),
                "El metodo no existe"
            );
            return respuesta;
        }
    }
}