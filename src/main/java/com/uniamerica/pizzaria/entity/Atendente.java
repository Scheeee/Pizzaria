package com.uniamerica.pizzaria.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import java.util.Collection;


@Entity
@Table(name = "atendentes", schema = "public")
@NoArgsConstructor
public class Atendente  {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Getter
  @Column(name = "id", nullable = false, unique = true)
  private long id;

  @Getter
  @Setter
  @NotBlank(message = "Campo não informado")
  @Size(max = 50, message = "A quantidade de caracteres é inválida")
  @Column(name="username", nullable = false)
  private String username;

  @Getter
  @Setter
  @NotBlank(message = "Campo não informado")
  @Column(name="password", nullable = false)
  private String password;

  @Getter
  @Setter
  @Size(max = 15, message = "A quantidade de caracteres é inválida")
  @Column(name="role")
  private String role;


}
