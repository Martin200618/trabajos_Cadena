package com.sena.crud_basic.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity(name="user")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="user_id")
        private int user_id;
    @Column(name="name",length = 100,nullable = false)
        private String name;
    @Column(name="email",length = 100,nullable = false)
        private String email;
    @Column(name="password",length=20)
        private String password;
    @Column(name="phone")
        private String phone;
    @Column(name="register_data")
        private LocalDateTime register_data;
    public user(){}
    public user(int user_id, String name, String email, String password, String phone, LocalDateTime register_data) {
        this.user_id = user_id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.register_data = register_data;
    }
    public int getUser_id() {
        return user_id;
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public LocalDateTime getRegister_data() {
        return register_data;
    }
    public void setRegister_data(LocalDateTime register_data) {
        this.register_data = register_data;
    }
    
}
