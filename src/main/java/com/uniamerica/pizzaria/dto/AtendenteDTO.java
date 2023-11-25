package com.uniamerica.pizzaria.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;


@Data
public class AtendenteDTO {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;


  @NotBlank(message = "Campo não informado")
  @Size(max = 50, message = "A quantidade de caracteres é inválida")
  private String username;


  @NotBlank(message = "Campo não informado")
  private String password;

  @Size(max = 15, message = "A quantidade de caracteres é inválida")
  private String role;
}
