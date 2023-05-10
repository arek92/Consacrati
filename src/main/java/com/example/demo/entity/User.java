package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User{
    @Id
    @Column(name = "id", nullable = false)
    private Long id;

    private java.lang.String username;
    private java.lang.String password;



    public User(java.lang.String username, java.lang.String password) {
        this.username = username;
        this.password = password;



    }


}