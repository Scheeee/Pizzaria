package com.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "enderecos", schema = "public")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Getter
    @Setter
    @Column(name = "ruas",nullable = false)
    private String rua;

    @Getter
    @Setter
    @Column(name = "numero",nullable = false)
    private String numero;

    @Getter
    @Setter
    @Column(name = "complemento")
    private String complemento;


    @Setter
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;





}
