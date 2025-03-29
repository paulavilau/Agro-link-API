package com.myproject.agrolink.entity;

// import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "user_ferma")
@Data
public class FarmUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nume")
    private String name;

    @Column(name = "id_firebase")
    private String firebaseId;

    @Column(name = "rol")
    private Integer role;

    @Column(name = "validat")
    private Integer validated;

    @Column(name = "email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_ferma")
    private Farm farm;

    @OneToMany(mappedBy = "creationFarmUser", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<Product> createdProducts;

    @OneToMany(mappedBy = "lastModFarmUser", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<Product> lastModProducts;

    @OneToMany(mappedBy = "lastModFarmUser", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<Product> lastModOrders;
}
