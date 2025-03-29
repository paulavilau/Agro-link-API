package com.myproject.agrolink.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "distribuire_venit")
@Data
public class FundsDistrib {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_status_comanda")
    @JsonIgnore
    @MapsId
    private OrderStatus orderStatus;

    @Column(name = "suma_client")
    private BigDecimal clientSum;

    @Column(name = "suma_ferma")
    private BigDecimal farmSum;

    @Column(name = "dt_distrib")
    private LocalDateTime date;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_admin_distrib")
    @JsonIgnore
    private Admin admin;
}

// @Id
// @GeneratedValue(strategy = GenerationType.IDENTITY)
// @Column(name="id")
// private Integer id;