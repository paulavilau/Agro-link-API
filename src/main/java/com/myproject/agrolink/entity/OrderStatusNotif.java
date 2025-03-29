package com.myproject.agrolink.entity;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "notif_status_comanda")
@Data
public class OrderStatusNotif {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "id_status_comanda")
    private OrderStatus orderStatus;

    @Column(name = "status")
    private Integer status;

    @Column(name = "dt_trimitere")
    private LocalDateTime sendingDt;

    @Column(name = "email")
    private String email;

    @Column(name = "mesaj")
    private String message;
}
