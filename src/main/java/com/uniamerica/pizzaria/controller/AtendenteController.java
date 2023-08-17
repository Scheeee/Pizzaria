package com.uniamerica.pizzaria.controller;

import com.uniamerica.pizzaria.entity.Atendente;
import com.uniamerica.pizzaria.repository.AtendenteRep;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/pizzaria/atendente")
public class AtendenteController {

    @Autowired
    AtendenteRep atendenteRep;

    @GetMapping("/lista")
    public ResponseEntity<?> getAll(){
        return ResponseEntity.ok(atendenteRep.findAll());
    }
    @PostMapping
    public ResponseEntity<?> inserir(@RequestBody final Atendente atendente){
        try {

            atendenteRep.save(atendente);
            return ResponseEntity.ok("Atendente cadastrado(a) com sucesso!");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping
    public ResponseEntity<?> editar(@RequestParam("id") final Long id, @RequestBody final Atendente atendente){
        try {
            final Atendente atendente1 = this.atendenteRep.findById(id).orElse(null);

            if (atendente1 == null || atendente1.equals(atendente.getId())){
                throw new RuntimeException("Não foi possivel indentificar o atendente informada");
            }
            this.atendenteRep.save(atendente);
            return ResponseEntity.ok
                    ("Atendente Cadastrado(a) com Sucesso");
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
