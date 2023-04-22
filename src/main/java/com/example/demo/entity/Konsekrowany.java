package com.example.demo.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@Data
@NoArgsConstructor
@ToString
public class Konsekrowany {


    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    String name;
    String lastname;
    String oasis;
    LocalDate birthDay;

    public Konsekrowany(String name, String lastname, String oasis, LocalDate birthDay) {
        this.name = name;
        this.lastname = lastname;
        this.oasis = oasis;
        this.birthDay = birthDay;
    }
}
