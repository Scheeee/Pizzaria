package com.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;



@Entity
@Table(name = "clientes", schema = "public")
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
    private Endereco endereco;





}
