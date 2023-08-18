package com.uniamerica.pizzaria.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.uniamerica.pizzaria.entity.Atendente;
import com.uniamerica.pizzaria.entity.Cliente;
import com.uniamerica.pizzaria.entity.Pizza;
import com.uniamerica.pizzaria.entity.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
@Data
public class PedidoDTO {
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    private LocalDate cadastrado;

    private LocalDate finalizado;

    private Atendente atendente;

    private Cliente cliente;

    private List<Pizza> pizzas;

    private boolean entrega;

    private String detalhes;

    private BigDecimal valorTotal;

    private boolean dinheiro;

    private Status status;
}
