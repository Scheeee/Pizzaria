package com.uniamerica.pizzaria.entity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "pedidos", schema = "public")
@NoArgsConstructor
public class Pedido {

    @Id @Getter
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false, unique = true)
    private Long id;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Getter @Setter
    @Column( name = "cadastro")
    private LocalDate cadastrado;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    @Getter @Setter
    @Column( name = "finalizado")
    private LocalDate finalizado;
    @Getter @Setter @ManyToOne
    @JsonBackReference // Indica que esta é a parte "de trás" da referência
    @JoinColumn(name = "atendente_id")
    private Atendente atendente;
    @Getter @Setter @ManyToOne
    @JoinColumn(name = "cliente_id")
   // @JsonBackReference // Indica que esta é a parte "de trás" da referência
    private Cliente cliente;
    @Getter
    @Setter
    @ManyToMany
    @JoinTable(
            name = "pedido_pizza",
            joinColumns = @JoinColumn(name = "pedido_id"),
            inverseJoinColumns = @JoinColumn(name = "pizza_id")
    )
    private List<Pizza> pizzas;
    @Getter @Setter
    @JoinColumn(name = "entrega", nullable = false)
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
    @JoinColumn(name = "pagamento", nullable = false)
    private boolean dinheiro;
    @Getter
    @Setter
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;
    @PrePersist
    public void prePersist(){
        this.cadastrado = LocalDate.now();
        this.status = Status.ATIVO;
    }
    @PreUpdate
    public void preUpdate(){
        this.finalizado = LocalDate.now();
    }

    public Pedido(long id, Atendente atendente, Cliente cliente, boolean entrega, String detalhes, BigDecimal valorTotal, boolean dinheiro) {
        this.id = id;
        this.atendente = atendente;
        this.cliente = cliente;
        this.entrega = entrega;
        this.detalhes = detalhes;
        this.valorTotal = valorTotal;
        this.dinheiro = dinheiro;
    }
    public Pedido(long id, Atendente atendente, Cliente cliente, List<Pizza> pizzas, boolean entrega, String detalhes, BigDecimal valorTotal, boolean dinheiro) {
        this.id = id;
        this.atendente = atendente;
        this.cliente = cliente;
        this.pizzas = pizzas;
        this.entrega = entrega;
        this.detalhes = detalhes;
        this.valorTotal = valorTotal;
        this.dinheiro = dinheiro;

    }
    public Pedido(long id, Atendente atendente, Cliente cliente, List<Pizza> pizzas, boolean entrega, String detalhes, BigDecimal valorTotal, boolean dinheiro, LocalDate cadastrado) {
        this.id = id;
        this.atendente = atendente;
        this.cliente = cliente;
        this.pizzas = pizzas;
        this.entrega = entrega;
        this.detalhes = detalhes;
        this.valorTotal = valorTotal;
        this.dinheiro = dinheiro;
        this.cadastrado = cadastrado;
    }

}
