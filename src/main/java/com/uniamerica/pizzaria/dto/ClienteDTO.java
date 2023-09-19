package com.uniamerica.pizzaria.dto;

import com.uniamerica.pizzaria.entity.Endereco;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;

@Data
public class ClienteDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;


    private String nome;

    private String telefone;

    private Endereco endereco;

    public ClienteDTO(long i, String sche, String s, Endereco endereco) {
    }

    public ClienteDTO(long i, String sche, String s) {
    }
}
