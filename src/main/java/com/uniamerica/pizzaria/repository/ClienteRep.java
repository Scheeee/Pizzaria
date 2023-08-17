package com.uniamerica.pizzaria.repository;

import com.uniamerica.pizzaria.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRep extends JpaRepository<Cliente,Long> {
}
