package com.geekbrains.decembermarket.entites;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private BigDecimal price;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Review> reviews;

    @OneToMany(mappedBy = "product", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<ProductImage> images;
}