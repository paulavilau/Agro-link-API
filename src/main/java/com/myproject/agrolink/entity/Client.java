package com.myproject.agrolink.entity;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "client")
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "id_firebase")
    private String firebaseId;

    @Column(name = "nume")
    private String firstName;

    @Column(name = "prenume")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "telefon")
    private String phone;

    @Column(name = "den_firma")
    private String companyName;

    @Column(name = "cif")
    private String cif;

    @Column(name = "adresa")
    private String address;

    @Column(name = "localitate")
    private String locality;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "id_judet")
    private County county;

    @Column(name = "dt_adaug")
    private String creationDt;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Cart> carts;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<Order> orders;

    @OneToMany(mappedBy = "client")
    @JsonIgnore
    private List<FavouriteProduct> favouriteProducts;

    @Column(name = "activ")
    private Integer active;

}
