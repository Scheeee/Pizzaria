package com.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "clientes", schema = "public")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Getter
    @Setter
    @Column(name="nome", nullable = false)
    private String nome;

    @Getter
    @Setter
    @Column(name = "telefone",nullable = false)
    private String telefone;

    @Getter @Setter
    @OneToOne(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Endereco endereco;

   /* @Getter @Setter
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private List<Pedido> pedidos;
    */




}
