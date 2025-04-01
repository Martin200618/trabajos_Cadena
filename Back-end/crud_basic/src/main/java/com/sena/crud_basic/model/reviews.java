package com.sena.crud_basic.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity(name="reviews")
public class reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="reviewsId")
        private int reviewsId;
    @ManyToOne
    @JoinColumn(name="userId")
        private user userId;
    @ManyToOne
    @JoinColumn(name="productId")
        private products productId;
    @Column(name="rating")
        private double rating;
    @Column(name="comment")
        private String comment;
    @Column(name="reviewDate")
        private LocalDateTime reviewDate;
    
    public reviews() {
    }

    public reviews(int reviewsId, user userId, products productId, double rating, String comment, LocalDateTime reviewDate) {
        this.reviewsId = reviewsId;
        this.userId = userId;
        this.productId = productId;
        this.rating = rating;
        this.comment = comment;
        this.reviewDate = reviewDate;
    }

    public int getReviewsId() {
        return reviewsId;
    }

    public void setReviewsId(int reviewsId) {
        this.reviewsId = reviewsId;
    }

    public user getUserId() {
        return userId;
    }

    public void setUserId(user userId) {
        this.userId = userId;
    }

    public products getProductId() {
        return productId;
    }

    public void setProductId(products productId) {
        this.productId = productId;
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
