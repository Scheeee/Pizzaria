package com.uniamerica.pizzaria.controller;


import com.uniamerica.pizzaria.dto.PizzaDTO;
import com.uniamerica.pizzaria.entity.Pizza;
import com.uniamerica.pizzaria.repository.PizzaRep;
import com.uniamerica.pizzaria.service.PizzaService;
import jakarta.validation.constraints.AssertTrue;
import org.modelmapper.ModelMapper;
import org.modelmapper.internal.util.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/pizzaria/pizza")
public class PizzaController {
    @Autowired
    PizzaRep pizzaRep;
    @Autowired
    PizzaService pizzaService;

    private static final String ERRO = "Error: ";
    @GetMapping("/lista")
    public ResponseEntity<List<Pizza>> getAll(){
        return ResponseEntity.ok(pizzaRep.findAll());
    }
    @PostMapping
    public ResponseEntity<Object> inserir(@RequestBody final PizzaDTO pizza){
        try {

            Assert.notNull(pizza.getSabores());
            ModelMapper modelMapper = new ModelMapper();
            Pizza pizza1 =  modelMapper.map(pizza, Pizza.class);
            BeanUtils.copyProperties(pizza,pizza1);
            return new ResponseEntity<>(HttpStatus.OK);

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(ERRO+ e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePizza(@PathVariable(value = "id")Long id,@RequestBody PizzaDTO pizza){

        Pizza pizzaNovo = pizzaRep.getReferenceById(id);

        BeanUtils.copyProperties(pizza, pizzaNovo, "id");
        pizzaRep.save(pizzaNovo);
        return new ResponseEntity<>(HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")Long id){
        try {
            Pizza pizza = pizzaRep.getReferenceById(id);


            pizzaRep.delete(pizza);
          return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ERRO+ e.getMessage());
        }


    }

}
