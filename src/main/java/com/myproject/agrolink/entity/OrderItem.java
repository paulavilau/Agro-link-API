package com.myproject.agrolink.entity;

import lombok.Data;

import java.math.BigDecimal;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "art_comanda")
@Data
public class OrderItem {

    public OrderItem() {
    }

    public OrderItem(Product product, BigDecimal quantity, BigDecimal price) {
        this.product = product;
        this.quantity = quantity;
        this.price = price;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_comanda")
    @JsonIgnore
    private Order order;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_articol")
    @JsonIgnore
    private Product product;

    @Column(name = "cantitate")
    private BigDecimal quantity;

    @Column(name = "pret")
    private BigDecimal price;

}
