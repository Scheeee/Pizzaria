package com.uniamerica.pizzaria.repository;

import com.uniamerica.pizzaria.entity.Atendente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AtendenteRep extends JpaRepository<Atendente,Long> {

  Optional<List<Atendente>> findByUsernameIgnoreCase(String username);

  Atendente findByUsername(String username);
}
