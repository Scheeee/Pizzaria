package com.uniamerica.pizzaria.repository;
import com.uniamerica.pizzaria.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PizzaRep extends JpaRepository<Pizza,Long> {
}
