package com.uniamerica.pizzaria.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "produtos", schema = "public")
@NoArgsConstructor
public class Produto {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  @Column(name = "id", nullable = false, unique = true)
  private long id;

  @Getter
  @Setter
  @NotBlank(message = "Campo não informado")
  @Size(max = 50, message = "A quantidade de caracteres é inválida")
  @Column(name="nomeProduto", nullable = false)
  private String nome;
  @Getter
  @Setter
  @NotBlank(message = "Campo não informado")
  @Size(max = 50, message = "A quantidade de caracteres é inválida")
  @Column(name="detalhes", nullable = false)
  private String detalhes;
  @Getter
  @Setter
  @NotNull(message = "Campo não informado")
  @JoinColumn(name = "valorUnidade")
  private BigDecimal valorUnit;

  @ManyToMany(mappedBy = "produtos")
  private List<Pedido> pedidos;


  public Produto(long id, String nome) {
    this.id = id;
    this.nome = nome;
  }
}

