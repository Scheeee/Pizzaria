package com.uniamerica.pizzaria.dto;


import com.uniamerica.pizzaria.entity.Atendente;
import com.uniamerica.pizzaria.entity.Cliente;
import com.uniamerica.pizzaria.entity.Pizza;
import com.uniamerica.pizzaria.entity.Status;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;



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

    public PedidoDTO(long id,Atendente atendente, Cliente cliente, List<Pizza> pizzas, boolean entrega, String detalhes, BigDecimal valorTotal, boolean dinheiro) {
        this.id = id;
        this.atendente = atendente;
        this.cliente = cliente;
        this.pizzas = pizzas;
        this.entrega = entrega;
        this.detalhes = detalhes;
        this.valorTotal = valorTotal;
        this.dinheiro = dinheiro;

    }


    public PedidoDTO() {
    }
}
