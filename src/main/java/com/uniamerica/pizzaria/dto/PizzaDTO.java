package com.uniamerica.pizzaria.dto;

import com.uniamerica.pizzaria.entity.Pedido;
import com.uniamerica.pizzaria.entity.Sabor;
import com.uniamerica.pizzaria.entity.Tamanho;
import jakarta.persistence.*;

import lombok.Data;
import lombok.Getter;


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








    private BigDecimal valorUnit;

    public PizzaDTO(long i, Tamanho p, List<Sabor> singletonList, BigDecimal valor, Pedido pedido) {
    }
}
