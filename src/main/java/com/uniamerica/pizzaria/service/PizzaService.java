package com.uniamerica.pizzaria.service;

import com.uniamerica.pizzaria.entity.Pizza;
import com.uniamerica.pizzaria.entity.Tamanho;
import com.uniamerica.pizzaria.repository.PizzaRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.math.BigDecimal;

@Service
public class PizzaService {
    @Autowired
    PizzaRep pizzaRep;


    @Transactional(rollbackOn = Exception.class)
    public ResponseEntity<Object> save(Pizza pizza) {
        Assert.isTrue(pizza.getSabores().size() >= 1,"insira um sabor");
        if( pizza.getTamanho() == Tamanho.P){
            Assert.isTrue(pizza.getSabores().size() == 1,"A pizza P possui apenas um sabor");
            pizza.setValorUnit(BigDecimal.valueOf(20.00));
        } else if (pizza.getTamanho() == Tamanho.M) {
            Assert.isTrue(pizza.getSabores().size() <= 2  ,"A pizza M possui apenas dois sabores");
          pizza.setValorUnit(BigDecimal.valueOf(25.00));
        } else if (pizza.getTamanho() == Tamanho.G) {
            Assert.isTrue(pizza.getSabores().size() <= 3,"A pizza G possui apenas três sabores");
          pizza.setValorUnit(BigDecimal.valueOf(30.00));
        } else if (pizza.getTamanho() == Tamanho.GG) {
            Assert.isTrue(pizza.getSabores().size() <= 4,"A pizza GG possui apenas quatro sabores");
          pizza.setValorUnit(BigDecimal.valueOf(40.00));
        }


        pizzaRep.save(pizza);



        return ResponseEntity.status(HttpStatus.CREATED).body("Pizza cadastrada com sucesso");
    }


}
