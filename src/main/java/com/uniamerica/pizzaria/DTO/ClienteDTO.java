package com.uniamerica.pizzaria.DTO;

import com.uniamerica.pizzaria.entity.Endereco;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

public class ClienteDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;


    private String nome;

    private String telefone;

    private Endereco endereco;

}
