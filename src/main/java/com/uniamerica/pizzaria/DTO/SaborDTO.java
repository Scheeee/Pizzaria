package com.uniamerica.pizzaria.DTO;

import com.uniamerica.pizzaria.entity.Pizza;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    private List<Pizza> pizzas;
}
