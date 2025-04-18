package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.commentDTO;
import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.model.Comments;
import com.sena.crud_basic.repository.IComments;

@Service
public class commentService {
    @Autowired
    private IComments data;

    public List<Comments> findAll(){
        return data.findAll();
    }

    public Optional<Comments> findById(int comment_id){
        return data.findById(comment_id);
    }

    public responseDTO save(commentDTO commentDTO){
        if (commentDTO == null || commentDTO.getProyectId() == null || commentDTO.getUser_id() == null || commentDTO.getTexto() == null) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Todos los campos son obligatorios"
            );
        }

        Comments newComment =  new Comments();
        newComment.setProyectId(commentDTO.getProyectId());
        newComment.setUser_id(commentDTO.getUser_id());
        newComment.setTexto(commentDTO.getTexto());

        try{
            data.save(newComment);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Comentario registrado exitosamente"
            );
        }catch (Exception e){
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al intentar crear el comentario"
            );
        }
    }

    public responseDTO updateComment(int comment_id, commentDTO commentDTO){
        Optional<Comments> existingComment = findById(comment_id);

        if (!existingComment.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "Comentario no encontrado"
            );
        }

        Comments commentToUpdate = existingComment.get();
        commentToUpdate.setProyectId(commentDTO.getProyectId());
        commentToUpdate.setUser_id(commentDTO.getUser_id());
        commentToUpdate.setTexto(commentDTO.getTexto());

        try {
            data.save(commentToUpdate);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "Comentario actualizado existosamente"
            );
        }catch(Exception e){
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al intentar actualizar el comentario"
            );
        }
    }

    public responseDTO deleteComment(int comment_id){
        Optional<Comments> existingComment = findById(comment_id);
        if (!existingComment.isPresent()) {
            return new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "No se encuentra el comentario que deseas eliminar"
            );
        }

        try {
            data.deleteById(comment_id);
            return new responseDTO(
                HttpStatus.OK.toString(), 
                "El comentario fue eliminado exitosamente"
            );
        }catch(Exception e){
            return new responseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.toString(), 
                "Error al intentar eliminar el comentario"
            );
        }
    }
}