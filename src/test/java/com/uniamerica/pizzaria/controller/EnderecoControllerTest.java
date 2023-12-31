package com.uniamerica.pizzaria.controller;
import com.uniamerica.pizzaria.dto.EnderecoDTO;
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
    @BeforeEach
    void injectGetById() {
        Endereco endereco = new Endereco(1, "Avenida tancredo neves", "1234", "casa 123");

        Mockito.when(enderecoRep.getReferenceById(1L)).thenReturn(endereco);
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
        EnderecoDTO endereco = new EnderecoDTO(1,"Avenida tancredo neves","1234", "casa 123");

        var endereco1 = enderecoController.inserir(endereco);


        Assertions.assertNotNull(endereco1);
        Assertions.assertEquals(null, endereco1.getBody());
    }



    /*@Test
    void updateEndereco() {
        EnderecoDTO endereco = new EnderecoDTO(1,"Travessa Oscar Muxfeld","1234", "casa 123");

        var endereco1 = enderecoController.updateEndereco(1L, endereco);


        Assertions.assertNotNull(endereco1);
        Assertions.assertEquals(null, endereco1.getBody());
    }

   */
}
