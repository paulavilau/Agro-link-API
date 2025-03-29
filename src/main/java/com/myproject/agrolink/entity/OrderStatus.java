package com.myproject.agrolink.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Table(name = "status_comanda")
@Data
public class OrderStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "id_comanda")
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinColumn(name = "id_ferma")
    private Farm farm;

    @Column(name = "subtotal_comanda")
    private BigDecimal orderSubtotal;

    @Column(name = "cost_transport")
    private BigDecimal deliveryFee;

    @Column(name = "status")
    private Integer status;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_user_ult_mod")
    @JsonIgnore
    private FarmUser lastModFarmUser;

    @Column(name = "dt_ult_mod")
    private LocalDateTime lastModDt;

    @Column(name = "estimare_livrare")
    private String deliveryEstimate;

    @Column(name = "suma_fermier")
    private BigDecimal farmFee;

    @Column(name = "suma_client")
    private BigDecimal clientFee;

    @OneToMany(mappedBy = "orderStatus", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderStatusNotif> orderStatusNotifs;

}
