package com.uniamerica.pizzaria.controller;
import com.uniamerica.pizzaria.entity.Atendente;
import com.uniamerica.pizzaria.repository.AtendenteRep;
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

    private static final String erro = "Error:  \" + e.getMessage()";
    @GetMapping("/lista")
    public ResponseEntity<List<Atendente>> findAll(){
        return ResponseEntity.ok(atendenteRep.findAll());
    }
    @PostMapping
    public ResponseEntity<Object> inserir(@RequestBody final Atendente atendente){
        try {
            Atendente atendente1 = new Atendente();
            BeanUtils.copyProperties(atendente,atendente1);

            atendenteRep.save(atendente1);
            return ResponseEntity.ok("Atendente cadastrado(a) com sucesso!");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(erro);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAtendente(@PathVariable(value = "id") long id, @RequestBody Atendente atendente){

        Atendente atendenteNovo = atendenteRep.getReferenceById(id);
        BeanUtils.copyProperties(atendente, atendenteNovo, "id");
        atendenteRep.save(atendenteNovo);
        return ResponseEntity.ok("Atendente atualizado com sucesso!");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")Long id){
        try {
            Atendente atendente = atendenteRep.getReferenceById(id);


            atendenteRep.delete(atendente);
            return ResponseEntity.ok("Atendente deletado com sucesso!");
        }catch (Exception e){
        return ResponseEntity.internalServerError().body(erro);
    }

    }




}
