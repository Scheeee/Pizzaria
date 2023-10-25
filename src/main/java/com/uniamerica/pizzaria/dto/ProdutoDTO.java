package com.uniamerica.pizzaria.dto;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;


import java.math.BigDecimal;
@Data
public class ProdutoDTO {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;


  @NotBlank(message = "Campo não informado")
  @Size(max = 50, message = "A quantidade de caracteres é inválida")
   private String nome;

  @NotBlank(message = "Campo não informado")
  @Size(max = 50, message = "A quantidade de caracteres é inválida")
  private String detalhes;

  @NotNull(message = "Campo não informado")
  private BigDecimal valorUnit;



}
