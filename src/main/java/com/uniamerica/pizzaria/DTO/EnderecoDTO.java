package com.uniamerica.pizzaria.DTO;

import com.uniamerica.pizzaria.entity.Cliente;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

public class EnderecoDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;


    private String rua;


    private String numero;


    private String complemento;



    private Cliente cliente;
}
