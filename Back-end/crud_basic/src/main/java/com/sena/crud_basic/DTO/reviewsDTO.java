package com.sena.crud_basic.DTO;

import java.time.LocalDateTime;

import com.sena.crud_basic.model.products;
import com.sena.crud_basic.model.user;

public class reviewsDTO {
    private user userId;
    private products product_Id;
    private double rating;
    private String comment;
    private LocalDateTime reviewDate;
    
    public reviewsDTO() {
    }

    public reviewsDTO(
        user userId, 
        products product_Id, 
        double rating, 
        String comment, 
        LocalDateTime reviewDate
    ){
        this.userId = userId;
        this.product_Id = product_Id;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public user getUserId() {
        return userId;
    }

    public void setUserId(user userId) {
        this.userId = userId;
    }

    public products getProduct_Id() {
        return product_Id;
    }

    public void setProduct_Id(products product_Id) {
        this.product_Id = product_Id;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(LocalDateTime reviewDate) {
        this.reviewDate = reviewDate;
    }
}
