package com.uniamerica.pizzaria.repository;
import com.uniamerica.pizzaria.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRep  extends JpaRepository<Produto,Long> {
}
