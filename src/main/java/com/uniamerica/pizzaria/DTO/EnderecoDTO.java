package com.uniamerica.pizzaria.DTO;

import com.uniamerica.pizzaria.entity.Cliente;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
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



    private Cliente cliente;
}
