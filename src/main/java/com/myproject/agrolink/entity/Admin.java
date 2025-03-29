package com.myproject.agrolink.entity;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name = "admin")
@Data
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_firebase")
    private String firebaseId;

    @Column(name = "email")
    private String email;
}
