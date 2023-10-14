package com.uniamerica.pizzaria.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Table(name = "clientes", schema = "public")
@NoArgsConstructor
public class Cliente {

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

    @Getter
    @Setter
    @Pattern(regexp = "\\d{2}-\\d{4,5}-\\d{4}", message = "Telefone inválido")
    @Size(min = 12, max = 13, message = "A quantidade de caracteres é inválida")
    @Column(name = "telefone",nullable = false)
    private String telefone;

    @Getter @Setter
    @OneToOne(mappedBy = "cliente", fetch = FetchType.EAGER)
    @JsonIgnore
    private Endereco endereco;

    public Cliente(String nome, String telefone) {
        this.nome = nome;
        this.telefone = telefone;

    }
    public Cliente(long id, String nome, String telefone) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;

    }


    public Cliente(long id, String nome, String telefone, Endereco endereco) {
        this.id = id;
        this.nome = nome;
        this.telefone = telefone;
        this.endereco = endereco;
    }


}
