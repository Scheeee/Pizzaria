package com.uniamerica.pizzaria.dto;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;



import java.util.List;
@Data

public class SaborDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    private String nome;

    private List<String> ingredientes;


    public SaborDTO(long id, String nome, List<String> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
    }
    public SaborDTO( String nome, List<String> ingredientes) {

        this.nome = nome;
        this.ingredientes = ingredientes;
    }


    public SaborDTO() {
    }
}
