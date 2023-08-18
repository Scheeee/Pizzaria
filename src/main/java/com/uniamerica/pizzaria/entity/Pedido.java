package com.uniamerica.pizzaria.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "pedidos", schema = "public")
public class Pedido {

    @Id @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Getter @Setter
    @Column( name = "cadastro")
    private LocalDate cadastrado;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Getter @Setter
    @Column( name = "finalizado")
    private LocalDate finalizado;
    @Setter @ManyToOne
    @JoinColumn(name = "atendente_id")
    private Atendente atendente;
    @Setter @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @Getter @Setter
    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private List<Pizza> pizzas;

    @Getter @Setter
    @JoinColumn(name = "entrega")
    private boolean entrega;

    @Getter
    @Setter
    @JoinColumn(name = "detalhes")
    private String detalhes;

    @Getter
    @Setter
    @JoinColumn(name = "valorTotal")
    private BigDecimal valorTotal;


    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @PrePersist
    private void prePersist(){
        this.cadastrado = LocalDate.now();
        this.status = Status.Ativo;
    }
    @PreUpdate
    private void preUpdate(){
        this.finalizado = LocalDate.now();
    }



}
