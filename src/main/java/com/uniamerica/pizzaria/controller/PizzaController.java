package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.entity.Endereco;
import com.uniamerica.pizzaria.entity.Pizza;
import com.uniamerica.pizzaria.entity.Sabor;
import com.uniamerica.pizzaria.repository.PizzaRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/pizzaria/pizza")
public class PizzaController {
    @Autowired
    PizzaRep pizzaRep;

    @GetMapping("/lista")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(pizzaRep.findAll());
    }
    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody final Pizza pizza){
        try {

            pizzaRep.save(pizza);
            return ResponseEntity.ok("pizza cadastrada com sucesso!");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Pizza pizza){
        try {
            final Pizza pizza1 = this.pizzaRep.findById(id).orElse(null);

            if (pizza1 == null || pizza1.equals(pizza.getId())){
                throw new RuntimeException("Não foi possivel indentificar a pizza informada");
            }
            this.pizzaRep.save(pizza);
            return ResponseEntity.ok
                    ("pizza cadastrada com sucesso");
        }
        catch (DataIntegrityViolationException e){
            return ResponseEntity.internalServerError()
                    .body("Error: " + e.getCause().getCause().getMessage());
        }
        catch (RuntimeException e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

}
