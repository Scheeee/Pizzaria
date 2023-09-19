package com.uniamerica.pizzaria.dto;

import com.uniamerica.pizzaria.entity.Cliente;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
@Data
public class EnderecoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;


    private String rua;


    private String numero;


    private String complemento;



    private ClienteDTO cliente;

    public EnderecoDTO(long i, String avenidaTancredoNeves, String s, String s1, ClienteDTO cliente) {
    }
}
