package com.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@NoArgsConstructor
@Table(name = "sabores", schema = "public")
public class Sabor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;


    @Getter
    @Setter
    @NotBlank(message = "Campo não informado")
    @Size(max = 50, message = "A quantidade de caracteres é inválida")
    @Column(name = "nome")
    private String nome;

    @Getter
    @Setter
    @NotNull(message = "Campo não informado")
    @Column(name = "ingrediente")
    private List<String> ingredientes;


    @ManyToMany(mappedBy = "sabores")
    private List<Pizza> pizzas;

    public Sabor(long id, String nome, List<String> ingredientes) {
        this.id = id;
        this.nome = nome;
        this.ingredientes = ingredientes;
    }
}
