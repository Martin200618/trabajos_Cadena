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

    // Obtener todos los usuarios
    public List<user> findAll() {
        return data.findAll();
    }

    // Obtener usuario por ID
    public Optional<user> findById(int user_id) {
        return data.findById(user_id);
    }

    // Registrar un usuario
    public responseDTO save(userDTO userDTO) {
        if (userDTO == null || !isValidUser(userDTO)) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Los datos del usuario son inválidos"
            );
        }

        if (data.findByEmail(userDTO.getEmail()).isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "El correo ya está registrado"
            );
        }

        user userEntity = convertToModel(userDTO);
        data.save(userEntity);
        return new responseDTO(
            HttpStatus.OK.toString(), 
            "Usuario registrado exitosamente"
        );
    }

    // Iniciar sesión
    public responseDTO login(String email, String password) {
        Optional<user> user = data.findByEmail(email);

        if (user.isEmpty()) {
            return new responseDTO(
                HttpStatus.UNAUTHORIZED.toString(), 
                "Email no registrado"
            );
        }

        if (!user.get().getPassword().equals(password)) {
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

    // Eliminar usuario por ID
    public responseDTO deleteUser(int user_id) {
        Optional<user> user = findById(user_id);

        if (user.isEmpty()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "El usuario no existe o ya fue eliminado"
            );
        }

        data.deleteById(user_id);
        return new responseDTO(
            HttpStatus.OK.toString(),
            "Usuario eliminado correctamente"
        );
    }

    // Actualizar usuario por ID
    public responseDTO update(int user_id, userDTO userDTO) {
        Optional<user> existingUser = findById(user_id);

        if (!existingUser.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "El usuario no existe o ya fue eliminado"
            );
        }

        user userToUpdate = existingUser.get();
        userToUpdate.setName(userDTO.getName());
        userToUpdate.setEmail(userDTO.getEmail());
        userToUpdate.setPassword(userDTO.getPassword());

        data.save(userToUpdate);
        return new responseDTO(
            HttpStatus.OK.toString(), 
            "Usuario actualizado correctamente"
        );
    }

    // Conversión de Entidad a DTO
    public userDTO convertToDTO(user user) {
        return new userDTO(
            user.getName(), 
            user.getEmail(), 
            user.getPassword()
        );
    }

    // Conversión de DTO a Entidad
    public user convertToModel(userDTO userDTO) {
        return new user(
            0, 
            userDTO.getName(), 
            userDTO.getEmail(), 
            userDTO.getPassword(), 
            LocalDateTime.now()
        );
    }

    // Validación de datos de usuario
    private boolean isValidUser(userDTO userDTO) {
        return userDTO.getName() != null && !userDTO.getName().trim().isEmpty()
        && userDTO.getName().length() <= 100
        && userDTO.getEmail() != null && !userDTO.getEmail().trim().isEmpty();
    }
}