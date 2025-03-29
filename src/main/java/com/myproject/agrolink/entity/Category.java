package com.myproject.agrolink.entity;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "categorie")
@Data
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "denumire")
    private String name;

    @Column(name = "img_link")
    private String imageLink;

    @OneToMany(mappedBy = "category")
    private List<Subcategory> subcategories;
}
