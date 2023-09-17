package com.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity

@Table(name = "pizzas", schema = "public")
public class Pizza {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @NotNull(message = "Campo não informado")
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "tamanho")
    private Tamanho tamanho;


    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "pizza_sabor",
            joinColumns = @JoinColumn(name = "pizza_id"),
            inverseJoinColumns = @JoinColumn(name = "sabor_id")
    )
    private List<Sabor> sabores;
    @ManyToMany(mappedBy = "pizzas")
    private List<Pedido> pedidos;


    @Getter
    @Setter
    @NotNull(message = "Campo não informado")
    @JoinColumn(name = "valorUnidade")
    private BigDecimal valorUnit;
}
