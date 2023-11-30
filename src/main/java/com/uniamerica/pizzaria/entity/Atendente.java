package com.uniamerica.pizzaria.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Entity
@Table(name = "atendentes")
@NoArgsConstructor
public class Atendente  implements UserDetails {
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


  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    List<GrantedAuthority> authorities = new ArrayList<>();
    authorities.add(new SimpleGrantedAuthority(this.role));
    return authorities;
  }

  @Override
  public String getPassword() {
    // TODO Auto-generated method stub
    return password;
  }

  @Override
  public String getUsername() {
    // TODO Auto-generated method stub
    return username;
  }

  @Override
  public boolean isAccountNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
  }

  @Override
  public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
  }




  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getRole() {
    return role;
  }

  public void setRole(String role) {
    this.role = role;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}
