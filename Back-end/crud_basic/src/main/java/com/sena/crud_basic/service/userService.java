package com.sena.crud_basic.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.userDTO;
import com.sena.crud_basic.model.user;
import com.sena.crud_basic.repository.Iuser;

@Service
public class userService {
    @Autowired
    private Iuser data;
    public List<user> findAll(){
        return data.findAll();
    }

    public Optional<user> fingById(int user_id){
        return data.findById(user_id);
    }

    public responseDTO deleteuser(int user_id){
        if(!fingById(user_id).isPresent()){
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El usuario que deseas eliminar no se encuentra o ya esta eliminado"
            );
            return respuesta;
        }
        data.deleteById(user_id);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Se elimino correctamente el usuario"
        );
        return respuesta;
    }

    public responseDTO save(userDTO userDTO){
        if(userDTO.getName().length()<1 ||
        userDTO.getName().length()>100){
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El nombre completo tiene que ser menor de 100 caracteres"
            );   
            return respuesta;
        }
        user userRegister = converToModel(userDTO);
        data.save(userRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Se guardo correctamente"
        );
        return respuesta;
    }

    public userDTO converToDTO(user user){
        userDTO userDTO = new userDTO(
            user.getName(),
            user.getEmail(),
            user.getPassword()
        );
        return userDTO;
    }

    public user converToModel(userDTO userDTO){
        user user =  new user(
            0,
            userDTO.getName(),
            userDTO.getEmail(),
            userDTO.getPassword(),
            LocalDateTime.now()
        );
        return user;
    }

    public responseDTO update(int user_id, userDTO userDTO){
        if(!fingById(user_id).isPresent()){
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El usuario que deseas actualizar no se encuentra o ya esta eliminado"
            );
            return respuesta;
        }
        user userRegister = converToModel(userDTO);
        data.save(userRegister);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "Se actualizo correctamente el usuario"
        );
        return respuesta;
    }
}