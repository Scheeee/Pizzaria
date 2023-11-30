package com.uniamerica.pizzaria.repository;
import com.uniamerica.pizzaria.entity.Atendente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LoginRepository extends JpaRepository<Atendente, Long>{

	public Optional<Atendente> findByUsername(String login);


}
