package com.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "pedidos", schema = "public")
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @Setter
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Setter
    @ManyToOne
    @JoinColumn(name = "atendente_id")
    private Atendente atendente;
    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "pedido_pizza",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id")
    )
    private List<Pizza> pizzas;

    @Getter
    @Setter
    @JoinColumn(name = "entrega")
    private boolean entrega;

    @Getter
    @Setter
    @JoinColumn(name = "detalhes")
    private String detalhes;





}
