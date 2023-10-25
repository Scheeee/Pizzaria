package com.uniamerica.pizzaria.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.uniamerica.pizzaria.entity.*;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate cadastrado;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate finalizado;

    private Atendente atendente;

    private Cliente cliente;

    private List<Pizza> pizzas;
    private List<Produto> produtos;

    private boolean entrega;

    private String detalhes;

    private BigDecimal valorTotal;

    private boolean dinheiro;

    private Status status;

    public PedidoDTO(long id,Atendente atendente, Cliente cliente, List<Pizza> pizzas, List<Produto> produtos, boolean entrega, String detalhes, BigDecimal valorTotal, boolean dinheiro) {
        this.id = id;
        this.atendente = atendente;
        this.cliente = cliente;
        this.pizzas = pizzas;
        this.produtos = produtos;
        this.entrega = entrega;
        this.detalhes = detalhes;
        this.valorTotal = valorTotal;
        this.dinheiro = dinheiro;

    }


    public PedidoDTO() {
    }
}
