package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.entity.Endereco;
import com.uniamerica.pizzaria.entity.Sabor;
import com.uniamerica.pizzaria.repository.SaborRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/pizzaria/sabor")
public class SaborController {
    @Autowired
    SaborRep saborRep;

    @GetMapping("/lista")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(saborRep.findAll());
    }
    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody final Sabor sabor){
        try {

            saborRep.save(sabor);
            return ResponseEntity.ok("Sabor cadastrado com sucesso!");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Sabor sabor){
        try {
            final Sabor sabor1 = this.saborRep.findById(id).orElse(null);

            if (sabor1 == null || sabor1.equals(sabor.getId())){
                throw new RuntimeException("Não foi possivel indentificar o sabor informada");
            }
            this.saborRep.save(sabor);
            return ResponseEntity.ok
                    ("Sabor cadastrado com sucesso");
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
