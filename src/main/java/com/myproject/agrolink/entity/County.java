package com.myproject.agrolink.entity;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "judet")
@Data
public class County {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "denumire")
    private String name;

    @OneToMany(mappedBy = "county")
    private List<Client> clients;

    @OneToMany(mappedBy = "county")
    @JsonIgnore
    private List<Farm> farms;

}
