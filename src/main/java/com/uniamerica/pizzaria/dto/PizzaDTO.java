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


    public PizzaDTO(long id, Tamanho tamanho, List<Sabor> sabores, Pedido pedido, BigDecimal valorUnit) {
        this.id = id;
        this.tamanho = tamanho;
        this.sabores = sabores;
        this.pedido = pedido;
        this.valorUnit = valorUnit;
    }
}
