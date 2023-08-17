package com.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
@Entity
@Table(name = "pizzas", schema = "public")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;
}
