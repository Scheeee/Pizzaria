package com.uniamerica.pizzaria.controller;
import com.uniamerica.pizzaria.dto.SaborDTO;
import com.uniamerica.pizzaria.entity.Sabor;
import com.uniamerica.pizzaria.repository.SaborRep;
import com.uniamerica.pizzaria.service.SaborService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
@RequestMapping(value = "/pizzaria/sabor")
public class SaborController {
    @Autowired
    SaborRep saborRep;
    @Autowired
    SaborService saborService;

    @GetMapping("/lista")
    public ResponseEntity<List<Sabor>> getAll(){
        return ResponseEntity.ok(saborRep.findAll());
    }
    private static final String ERRO = "Error:  + e.getMessage()";
    @PostMapping
    public ResponseEntity<Object> inserir(@RequestBody final SaborDTO sabor){
        try {
            ModelMapper modelMapper = new ModelMapper();
            Sabor sabor1 =  modelMapper.map(sabor, Sabor.class);

            BeanUtils.copyProperties(sabor,sabor1);
            saborService.saveSabor(sabor1);
            return ResponseEntity.ok("Sabor cadastrado com sucesso!");

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(ERRO+ e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateSabor(@PathVariable(value = "id")Long id,@RequestBody SaborDTO sabor){

        Sabor saborNovo = saborRep.getReferenceById(id);

        BeanUtils.copyProperties(sabor, saborNovo, "id");
        saborRep.save(saborNovo);
        return ResponseEntity.ok("sabor atualizado com sucesso!");

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")Long id){
        try{
            Sabor sabor = saborRep.getReferenceById(id);


           saborRep.delete(sabor);
            return ResponseEntity.ok("Sabor deletado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ERRO+ e.getMessage());
        }
    }
}
