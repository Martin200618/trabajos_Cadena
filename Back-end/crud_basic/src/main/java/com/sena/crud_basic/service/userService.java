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
    // Guardar un usuario
    public responseDTO save(userDTO userDTO) {
        if (userDTO == null) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "Los datos del empleado son inválidos"
            );
        }

        Optional<user> existingEmployee = data.findByEmail(userDTO.getEmail());
        if (existingEmployee.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El correo ya está registrado"
            );
        }

        if (userDTO.getName() == null || userDTO.getName().trim().isEmpty() ||
            userDTO.getName().length() > 100) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El nombre completo debe tener entre 1 y 100 caracteres"
            );
        }

        user userEntity = converToModel(userDTO);
        data.save(userEntity);
        return new responseDTO(
            HttpStatus.OK.toString(),
            "Empleado guardado exitosamente"
        );
    }

    // Iniciar sesión
    public responseDTO login(String email, String password) {
        Optional<user> employee = data.findByEmail(email);

        if (employee.isEmpty()) {
            return new responseDTO(
                HttpStatus.UNAUTHORIZED.toString(),
                "Email no registrado"
            );
        }

        if (!employee.get().getPassword().equals(password)) {
            return new responseDTO(
                HttpStatus.UNAUTHORIZED.toString(),
                "Contraseña incorrecta"
            );
        }

        return new responseDTO(
            HttpStatus.OK.toString(),
            "Inicio de sesión exitoso"
        );
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
        Optional<user> existingUser = fingById(user_id);
        if (!existingUser.isPresent()) {
            return new responseDTO(HttpStatus.BAD_REQUEST.toString(),
                "El usuario que deseas actualizar no se encuentra o ya esta eliminado");
        }
    
        user userToUpdate = existingUser.get();
        userToUpdate.setName(userDTO.getName());
        userToUpdate.setEmail(userDTO.getEmail());
        userToUpdate.setPassword(userDTO.getPassword());
    
        data.save(userToUpdate);
    
        return new responseDTO(HttpStatus.OK.toString(), "Se actualizó correctamente el usuario");
    }
}