package com.sena.crud_basic.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.sena.crud_basic.DTO.responseDTO;
import com.sena.crud_basic.DTO.reviewsDTO;
import com.sena.crud_basic.model.reviews;
import com.sena.crud_basic.repository.Ireviews;

@Service
public class reviewsService {
    @Autowired
    private Ireviews data;

    public List<reviews> findAll(){
        return data.findAll();
    }

    public Optional<reviews> findById(int reviewsId){
        return data.findById(reviewsId);
    }

    public responseDTO deleteReviews(int reviewsId){
        if(!findById(reviewsId).isPresent()){
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(), 
                "La reseña no se encontró o ya se eliminó"
            );
            return respuesta;
        }
        data.deleteById(reviewsId);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "La reseña se elimino correctamente"
        );
        return respuesta;
    }

    public responseDTO save(reviewsDTO reviewsDTO){
        reviews reviews = converToModel(reviewsDTO);
        data.save(reviews);
        responseDTO respuesta = new responseDTO(
            HttpStatus.OK.toString(),
            "se guardo correctamente"
        );
        return respuesta;
    }

    public reviewsDTO converToDto(reviews reviews){
        reviewsDTO reviewsDTO = new reviewsDTO(
            reviews.getUserId(),
            reviews.getProductId(),
            reviews.getRating(),
            reviews.getComment(),
            reviews.getReviewDate()
        );
        return reviewsDTO;
    }

    public reviews converToModel(reviewsDTO reviewsDTO){
        reviews reviews = new reviews(
            0,
            reviewsDTO.getUserId(),
            reviewsDTO.getProduct_Id(),
            reviewsDTO.getRating(),
            reviewsDTO.getComment(),
            reviewsDTO.getReviewDate()
        );
        return reviews;
    }

    public responseDTO update(int reviewsId, reviewsDTO reviewsDTO){
        Optional<reviews> existingReviewsOtp = data.findById(reviewsId);
        if (existingReviewsOtp.isPresent()) {
            reviews existingReviews = existingReviewsOtp.get();
            existingReviews.setUserId(reviewsDTO.getUserId());
            existingReviews.setProductId(reviewsDTO.getProduct_Id());
            existingReviews.setRating(reviewsDTO.getRating());
            existingReviews.setComment(reviewsDTO.getComment());
            existingReviews.setReviewDate(reviewsDTO.getReviewDate());
            data.save(existingReviews);
            responseDTO respuesta = new responseDTO(
                HttpStatus.OK.toString(),
                "La reseña se actualizó correctamente"
            );
            return respuesta;
        }else{
            responseDTO respuesta = new responseDTO(
                HttpStatus.BAD_REQUEST.toString(),
                "La reseña no se encontró o ya se eliminó"
            );
            return respuesta;
        }
    }
}