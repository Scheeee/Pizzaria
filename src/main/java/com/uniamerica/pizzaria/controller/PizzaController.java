package com.uniamerica.pizzaria.controller;


import com.uniamerica.pizzaria.entity.Pizza;
import com.uniamerica.pizzaria.repository.PizzaRep;
import com.uniamerica.pizzaria.service.PizzaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/pizzaria/pizza")
public class PizzaController {
    @Autowired
    PizzaRep pizzaRep;
    @Autowired
    PizzaService pizzaService;

    @GetMapping("/lista")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(pizzaRep.findAll());
    }
    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody final Pizza pizza){
        try {

            Pizza pizza1 = new Pizza();
            BeanUtils.copyProperties(pizza,pizza1);
            return pizzaService.save(pizza1);

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePizza(@PathVariable(value = "id")Long id,@RequestBody Pizza pizza){

        Pizza pizzaNovo = pizzaRep.getReferenceById(id);

        BeanUtils.copyProperties(pizza, pizzaNovo, "id");
        pizzaRep.save(pizzaNovo);
        return ResponseEntity.ok("pizza atualizada com sucesso!");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
        try {
            Pizza pizza = pizzaRep.getReferenceById(id);


            pizzaRep.delete(pizza);
            return ResponseEntity.ok("Pizza deletada com sucesso!");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }


    }

}
