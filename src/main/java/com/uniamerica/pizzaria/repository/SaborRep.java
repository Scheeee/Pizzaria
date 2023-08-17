package com.uniamerica.pizzaria.repository;

import com.uniamerica.pizzaria.entity.Sabor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaborRep extends JpaRepository<Sabor,Long> {
}
