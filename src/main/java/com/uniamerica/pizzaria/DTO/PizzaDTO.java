package com.uniamerica.pizzaria.DTO;

import com.uniamerica.pizzaria.entity.Pedido;
import com.uniamerica.pizzaria.entity.Sabor;
import com.uniamerica.pizzaria.entity.Tamanho;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;
@Data
public class PizzaDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;


    private Tamanho tamanho;
    private List<Sabor> sabores;

    private Pedido pedido;


    private List<String> ingredientes;


    private List<String> adicionais;


    private BigDecimal valorUnit;
}
