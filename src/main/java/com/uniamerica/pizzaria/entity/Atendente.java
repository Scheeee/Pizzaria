package com.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "atendentes", schema = "public")
public class Atendente {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Getter
    @Setter
    @NotBlank(message = "Campo não informado")
    @Size(max = 50, message = "A quantidade de caracteres é inválida")
    @Column(name="nome", nullable = false)
    private String nome;

    /*@Getter @Setter
    @OneToMany(mappedBy = "atendente", fetch = FetchType.EAGER)
    private List<Pedido> pedidos;
    */
}
