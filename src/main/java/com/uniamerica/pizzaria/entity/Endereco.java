package com.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Table(name = "enderecos", schema = "public")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Getter
    @Column(name = "id", nullable = false, unique = true)
    private long id;

    @Getter
    @Setter
    @NotBlank(message = "Campo não informado")
    @Size(max = 50, message = "A quantidade de caracteres é inválida")
    @Column(name = "ruas",nullable = false)
    private String rua;

    @Getter
    @Setter
    @NotBlank(message = "Campo não informado")
    @Size(max = 50, message = "A quantidade de caracteres é inválida")
    @Column(name = "numero",nullable = false)
    private String numero;

    @Getter
    @Setter
    @NotBlank(message = "Campo não informado")
    @Size(max = 50, message = "A quantidade de caracteres é inválida")
    @Column(name = "complemento")
    private String complemento;


    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;


    public Endereco(long id, String rua, String numero, String complemento ) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;

    }

    public Endereco(long id, String rua, String numero, String complemento, Cliente cliente) {
        this.id = id;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.cliente = cliente;
    }
}
