package com.uniamerica.pizzaria.controller;
import com.uniamerica.pizzaria.entity.Endereco;
import com.uniamerica.pizzaria.repository.EnderecoRep;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.util.ArrayList;
import java.util.List;



@SpringBootTest
class EnderecoControllerTest {

    @MockBean
    EnderecoRep enderecoRep;

    @Autowired
    private  final EnderecoController enderecoController = new EnderecoController();


    @BeforeEach
    void injectFindAll() {
        Endereco endereco = new Endereco(1, "Avenida tancredo neves", "1234", "casa 123");

        List<Endereco> enderecos = new ArrayList<>();

        enderecos.add(endereco);

        Mockito.when(enderecoRep.findAll()).thenReturn(enderecos);
    }

    @Test
    void getAll() {
        var enderecocontroller = enderecoController.getAll();
        List<Endereco> enderecos = enderecocontroller.getBody();
        assert enderecos != null;
        int valor = enderecos.size();
        System.out.println(valor);
        Assertions.assertEquals(1, valor, 0);
    }

    @Test
    void inserir() {
        Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123");

        var endereco1 = enderecoController.inserir(endereco);


        Assertions.assertNotNull(endereco1);
        Assertions.assertEquals("Endereço cadastrado com sucesso!", endereco1.getBody());
    }

    @Test
    void updateEndereco() {
    }

    @Test
    void delete() {
        inserir();

        var delete = enderecoController.delete(1L);

        Assertions.assertNotNull(delete);
        Assertions.assertEquals("Endereço deletado com sucesso!", delete.getBody());
    }
}