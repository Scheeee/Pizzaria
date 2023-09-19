package com.uniamerica.pizzaria.service;

import com.uniamerica.pizzaria.entity.Pizza;
import com.uniamerica.pizzaria.entity.Sabor;
import com.uniamerica.pizzaria.entity.Tamanho;
import com.uniamerica.pizzaria.repository.PizzaRep;
import com.uniamerica.pizzaria.repository.SaborRep;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SaborService {

    @Autowired
    SaborRep saborRep;
    @Autowired
    PizzaRep pizzaRep;

    @Transactional
    public ResponseEntity<String> saveSabor(Sabor sabor){

        saborRep.save(sabor);
        List<Sabor> sabores = saborRep.findAll();




        List<Sabor> sabor1 = new ArrayList<>();

        Pizza pizza1 = new Pizza();
        sabor1.add(sabor);
        pizza1.setSabores(sabor1);
        pizza1.setTamanho(Tamanho.P);
        pizza1.setValorUnit(BigDecimal.valueOf(25));
         pizzaRep.save(pizza1);

         Pizza pizza2 = new Pizza();
        pizza2.setSabores(sabor1);
        pizza2.setTamanho(Tamanho.M);
        pizza2.setValorUnit(BigDecimal.valueOf(30));
        pizzaRep.save(pizza2);

        Pizza pizza3 = new Pizza();
        pizza3.setSabores(sabor1);
        pizza3.setTamanho(Tamanho.G);
        pizza3.setValorUnit(BigDecimal.valueOf(35));
        pizzaRep.save(pizza3);

        for (Sabor sabore : sabores) {

            Pizza pizza4 = new Pizza();
            pizza4.setSabores(sabor1);
            if (sabor != sabore) {
                pizza4.getSabores().add(sabore);
                pizza4.setTamanho(Tamanho.M);
                pizza4.setValorUnit(BigDecimal.valueOf(30));
                pizzaRep.save(pizza4);
            }
        }
        for(int i = 1; i < sabores.size(); i++){
            Pizza pizza5 = new Pizza();
            pizza5.setSabores(sabor1);
            if(sabor != sabores.get(i)) {

                pizza5.getSabores().add(sabores.get(i));
                pizza5.setTamanho(Tamanho.G);
                pizza5.setValorUnit(BigDecimal.valueOf(40));
                for (Sabor sabore : sabores) {
                    if (sabores.get(i) != sabore && sabor != sabore) {
                        pizza5.getSabores().add(sabore);// testar essa coisa aqui e verificar a possibilidade de diminuir esse cod
                    }
                }
                pizzaRep.save(pizza5);
            }

        }

        return ResponseEntity.status(HttpStatus.CREATED).body("Pizza cadastrada com sucesso");
    }
}
