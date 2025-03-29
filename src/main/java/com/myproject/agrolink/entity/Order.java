package com.myproject.agrolink.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "comanda")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client")
    private Client client;

    @Column(name = "dtCreare")
    private LocalDateTime creationDt;

    @Column(name = "val_articole")
    private BigDecimal productsValue;

    @Column(name = "val_transport")
    private BigDecimal deliveryPrice;

    @Column(name = "val_plata")
    private BigDecimal paymentValue;

    @Column(name = "tip_plata")
    private Integer paymentType;

    @Column(name = "telefon")
    private String phone;

    @Column(name = "loc_livrare")
    private String locality;

    @Column(name = "adr_livrare")
    private String address;

    @Column(name = "finalizata")
    private Integer completed;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_admin_final")
    @JsonIgnore
    private Admin admin;

    @Column(name = "dt_finalizare")
    private LocalDateTime completionDt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderStatus> orderStatuses = new ArrayList<>();
}
