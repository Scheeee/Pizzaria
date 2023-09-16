package com.uniamerica.pizzaria.controller;
import com.uniamerica.pizzaria.DTO.AtendenteDTO;
import com.uniamerica.pizzaria.entity.Atendente;
import com.uniamerica.pizzaria.repository.AtendenteRep;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> inserir(@RequestBody final AtendenteDTO atendente){
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
    public ResponseEntity<?> updateAtendente(@PathVariable(value = "id")Long id,@RequestBody @Valid AtendenteDTO atendente){

        Atendente atendenteNovo = atendenteRep.getById(id);
        BeanUtils.copyProperties(atendente, atendenteNovo, "id");
        atendenteRep.save(atendenteNovo);
        return ResponseEntity.ok("Atendente atualizado com sucesso!");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id")Long id){
        try {
            Atendente atendente = atendenteRep.getById(id);


            atendenteRep.delete(atendente);
            return ResponseEntity.ok("Atendente deletado com sucesso!");
        }catch (Exception e){
        return ResponseEntity.internalServerError().body("Error: " + e.getMessage());
    }

    }




}
