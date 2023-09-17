package com.uniamerica.pizzaria.controller;
import com.uniamerica.pizzaria.dto.AtendenteDTO;
import com.uniamerica.pizzaria.entity.Atendente;
import com.uniamerica.pizzaria.repository.AtendenteRep;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/pizzaria/atendente")
public class AtendenteController {

    @Autowired
    AtendenteRep atendenteRep;

    @GetMapping("/lista")
    public ResponseEntity<List> findAll(){
        return ResponseEntity.ok(atendenteRep.findAll());
    }
    @PostMapping
    public ResponseEntity<Object> inserir(@RequestBody final AtendenteDTO atendente){
        try {
            Atendente atendente1 = new Atendente();
            BeanUtils.copyProperties(atendente,atendente1);

            atendenteRep.save(atendente1);
            return ResponseEntity.ok("Atendente cadastrado(a) com sucesso!");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAtendente(@PathVariable(value = "id")Long id,@RequestBody @Valid AtendenteDTO atendente){

        Atendente atendenteNovo = atendenteRep.getById(id);
        BeanUtils.copyProperties(atendente, atendenteNovo, "id");
        atendenteRep.save(atendenteNovo);
        return ResponseEntity.ok("Atendente atualizado com sucesso!");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")Long id){
        try {
            Atendente atendente = atendenteRep.getById(id);


            atendenteRep.delete(atendente);
            return ResponseEntity.ok("Atendente deletado com sucesso!");
        }catch (Exception e){
        return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
    }

    }




}
