package com.sena.crud_basic.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import com.sena.crud_basic.model.meto_pago;
import com.sena.crud_basic.DTO.meto_pagoDTO;
import com.sena.crud_basic.repository.Imeto_pago;

@Service
public class meto_pagoService {
    @Autowired
    private Imeto_pago data;
    public void save(meto_pagoDTO meto_pagoDTO){
        meto_pago meto_pagoRegister = converToModel(meto_pagoDTO);
        data.save(meto_pagoRegister);
    }    
    public meto_pagoDTO converToDTO(meto_pago meto_pago){
        meto_pagoDTO meto_pagoDTO = new meto_pagoDTO(
            meto_pago.getmetodo());
        return meto_pagoDTO;
    }
    public meto_pago converToModel(meto_pagoDTO meto_pagoDTO){
        meto_pago meto_pago = new meto_pago(
            0,
            meto_pagoDTO.getmetodo());
        return meto_pago;
    }
}