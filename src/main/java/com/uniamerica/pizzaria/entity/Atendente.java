package com.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "atendentes", schema = "public")
public class Atendente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Getter
    @Setter
    @Column(name="nome", nullable = false)
    private String nome;

    /*@Getter @Setter
    @OneToMany(mappedBy = "atendente", fetch = FetchType.EAGER)
    private List<Pedido> pedidos;
    */
}
