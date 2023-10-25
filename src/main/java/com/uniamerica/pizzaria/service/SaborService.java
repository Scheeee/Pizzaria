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
  public ResponseEntity<String> saveSabor(Sabor sabor) {
    saborRep.save(sabor);

    Pizza pizza1 = new Pizza();
    List<Sabor> saborList1 = new ArrayList<>();
    saborList1.add(sabor);
    pizza1.setSabores(saborList1);
    pizza1.setTamanho(Tamanho.P);
    pizza1.setValorUnit(BigDecimal.valueOf(25));
    pizzaRep.save(pizza1);

    Pizza pizza2 = new Pizza();
    List<Sabor> saborList2 = new ArrayList<>();
    saborList2.add(sabor);
    pizza2.setSabores(saborList2);
    pizza2.setTamanho(Tamanho.M);
    pizza2.setValorUnit(BigDecimal.valueOf(30));
    pizzaRep.save(pizza2);

    Pizza pizza3 = new Pizza();
    List<Sabor> saborList3 = new ArrayList<>();
    saborList3.add(sabor);
    pizza3.setSabores(saborList3);
    pizza3.setTamanho(Tamanho.G);
    pizza3.setValorUnit(BigDecimal.valueOf(35));
    pizzaRep.save(pizza3);

    Pizza pizzaGG = new Pizza();
    List<Sabor> saborListGG = new ArrayList<>();
    saborListGG.add(sabor);
    pizzaGG.setSabores(saborListGG);
    pizzaGG.setTamanho(Tamanho.GG);
    pizzaGG.setValorUnit(BigDecimal.valueOf(45));
    pizzaRep.save(pizzaGG);
    List<Sabor> sabores = saborRep.findAll();

    for (Sabor sabore : sabores) {
      if (!sabore.equals(sabor)) {
        Pizza pizza4 = new Pizza();
        List<Sabor> saborList4 = new ArrayList<>(saborList1); // Clonar a lista de sabores
        saborList4.add(sabore);
        pizza4.setSabores(saborList4);
        pizza4.setTamanho(Tamanho.M);
        pizza4.setValorUnit(BigDecimal.valueOf(30));
        pizzaRep.save(pizza4);
      }
    }

    for (int i = 0; i < sabores.size(); i++) {
      Sabor saborI = sabores.get(i);
      if (!sabor.equals(saborI)) {
        Pizza pizza5 = new Pizza();
        List<Sabor> saborList5 = new ArrayList<>(saborList1);
        saborList5.add(saborI);
        pizza5.setSabores(saborList5);
        pizza5.setTamanho(Tamanho.G);
        pizza5.setValorUnit(BigDecimal.valueOf(40));
        for (int j = i + 1; j < sabores.size(); j++) {
          Sabor saborJ = sabores.get(j);
          if (!sabor.equals(saborJ)) {
            saborList5.add(saborJ);
          }
        }
        pizzaRep.save(pizza5);
      }
    }


    for (int i = 0; i < sabores.size(); i++) {
      Sabor saborI = sabores.get(i);
      if (!sabor.equals(saborI)) {
        Pizza pizza6 = new Pizza();
        List<Sabor> saborList6 = new ArrayList<>(saborList1);
        saborList6.add(saborI);
        pizza6.setSabores(saborList6);
        pizza6.setTamanho(Tamanho.GG);
        pizza6.setValorUnit(BigDecimal.valueOf(45));
        for (int j = i + 1; j < sabores.size(); j++) {
          Sabor saborJ = sabores.get(j);
          if (!sabor.equals(saborJ)) {
            saborList6.add(saborJ);
          }
          for (int k = j + 1; k < sabores.size(); k++){
            Sabor saborK = sabores.get(k);
            if(!sabor.equals(saborK) && !saborK.equals(saborJ));{
              saborList6.add(saborK);
            }
          }
        }
        pizzaRep.save(pizza6);
      }
    }


    return ResponseEntity.status(HttpStatus.CREATED).body("Pizza cadastrada com sucesso");
  }




}
