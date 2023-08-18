package com.uniamerica.pizzaria.service;

import com.uniamerica.pizzaria.repository.PizzaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PizzaService {
    @Autowired
    PizzaRep pizzaRep;

}
