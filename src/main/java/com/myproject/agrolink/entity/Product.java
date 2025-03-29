package com.myproject.agrolink.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
// import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "produs")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "cod")
    private String code;

    @Column(name = "denumire")
    private String name;

    @Column(name = "descriere")
    private String description;

    @Column(name = "UM")
    private String unit;

    @Column(name = "masura")
    private String measure;

    @Column(name = "pret")
    private BigDecimal price;

    @Column(name = "dt_creare")
    private LocalDateTime creationDt;

    @Column(name = "dt_ult_mod")
    private LocalDateTime lastModDt;

    @Column(name = "in_stoc")
    private Integer inStock;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "id_categorie")
    private Subcategory subcategory;

    @OneToMany(mappedBy = "product")
    @JsonIgnore
    private List<CartItem> cartItems;

    // @JsonBackReference
    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "id_ferma")
    private Farm farm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "id_user_creare")
    private FarmUser creationFarmUser;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "id_user_ult_mod")
    private FarmUser lastModFarmUser;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Image> images;
}
