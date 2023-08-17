package com.uniamerica.pizzaria.repository;

import com.uniamerica.pizzaria.entity.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnderecoRep extends JpaRepository<Endereco,Long> {
}
