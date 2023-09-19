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


    public ClienteDTO(long id, String nome, String telefone, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }

    public ClienteDTO(long id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
    }


}
