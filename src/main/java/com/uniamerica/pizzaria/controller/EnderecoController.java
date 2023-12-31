package com.uniamerica.pizzaria.controller;
import com.uniamerica.pizzaria.dto.EnderecoDTO;
import com.uniamerica.pizzaria.entity.Endereco;
import com.uniamerica.pizzaria.entity.Pedido;
import com.uniamerica.pizzaria.repository.EnderecoRep;
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
@RequestMapping(value = "/pizzaria/endereco")
public class EnderecoController {
    @Autowired
    EnderecoRep enderecoRep;
    private static final String ERRO = "Error:  ";

    @GetMapping("/lista")
    public ResponseEntity<List<Endereco>> getAll(){
        return ResponseEntity.ok(enderecoRep.findAll());
    }
    @PostMapping
    public ResponseEntity<Object> inserir(@RequestBody final EnderecoDTO endereco){
        try {

            ModelMapper modelMapper = new ModelMapper();
            Endereco endereco1 = modelMapper.map(endereco, Endereco.class);
            Assert.notNull(endereco1);

            enderecoRep.save(endereco1);
            return new ResponseEntity<>(HttpStatus.OK);

        }
        catch (Exception e){
            return ResponseEntity.internalServerError().body(ERRO+ e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateEndereco(@PathVariable(value = "id")Long id,@RequestBody EnderecoDTO endereco){

      try {
        Endereco enderecoNovo = enderecoRep.getReferenceById(id);

        BeanUtils.copyProperties(endereco, enderecoNovo, "id");
        enderecoRep.save(enderecoNovo);
        return new ResponseEntity<>(HttpStatus.OK);

      }
      catch (Exception e ){
        return ResponseEntity.internalServerError().body(ERRO);
      }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> delete(@PathVariable(value = "id")Long id){
        try{
        Endereco endereco = enderecoRep.getReferenceById(id);


        enderecoRep.delete(endereco);
        return ResponseEntity.ok("Endereço deletado com sucesso!");
        }catch (Exception e){
            return ResponseEntity.internalServerError().body(ERRO+ e.getMessage());
        }
    }
}
