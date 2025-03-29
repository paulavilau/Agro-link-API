package com.myproject.agrolink.entity;

import lombok.Data;

import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "ferma")
@Data
public class Farm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "nume")
    private String name;

    @Column(name = "cod")
    private String code;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_judet")
    @JsonIgnore
    private County county;

    @Column(name = "localitate")
    private String locality;

    @Column(name = "adresa")
    private String address;

    @Column(name = "telefon")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "img_link")
    private String image;

    @Column(name = "descriere")
    private String description;

    @Column(name = "cost_transport")
    private BigDecimal deliveryPrice;

    @Column(name = "dt_adaugare")
    private LocalDateTime creationDt;

    @Column(name = "validat")
    private Integer validated;

    @Column(name = "dt_validare")
    private LocalDateTime validationDt;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_admin_valid")
    private Admin admin;

    @OneToMany(mappedBy = "farm")
    @JsonIgnore
    private List<Product> products;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<DeliveryCounty> deliveryCounties;

    @OneToMany(mappedBy = "farm", cascade = CascadeType.ALL, orphanRemoval = false)
    @JsonIgnore
    private List<FarmUser> farmUsers;

}
