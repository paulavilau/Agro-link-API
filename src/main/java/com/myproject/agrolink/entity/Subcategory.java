package com.myproject.agrolink.entity;

import lombok.Data;

import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "subcategorie")
@Data
public class Subcategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "denumire")
    private String name;

    @Column(name = "img_link")
    private String imageLink;

    // @Column(name = "id_clasa")
    // private int categoryId;

    @ManyToOne(fetch = FetchType.EAGER) // the Category entity will only be loaded from the database when it is actually
                                        // accessed in code
    @JoinColumn(name = "id_categorie")
    private Category category;

    @OneToMany(mappedBy = "subcategory")
    private List<Product> products;
}
