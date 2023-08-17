package com.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
@Entity
@Table(name = "pedidos", schema = "public")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;
}
