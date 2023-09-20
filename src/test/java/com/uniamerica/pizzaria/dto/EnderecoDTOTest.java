package com.uniamerica.pizzaria.dto;



import com.uniamerica.pizzaria.entity.Cliente;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class EnderecoDTOTest {



    EnderecoDTO endereco = new EnderecoDTO(1,"Avenida tancredo neves","1234", "casa 123");
    @Test
    void getRuaandSetRua() {

        endereco.setRua("rua 2");

        Assertions.assertEquals("rua 2", endereco.getRua());
    }

    @Test
    void getNumeroandSetNumero() {
        endereco.setNumero("1");
        Assertions.assertEquals("1", endereco.getNumero());
    }

    @Test
    void getComplementoandSetComplemento() {
        endereco.setComplemento("nada");
        Assertions.assertEquals("nada", endereco.getComplemento());
    }

    @Test
    void getClienteandSetCliente() {
        Cliente cliente2 = new Cliente(2,"Gabriele", "45-98034-3600");

        endereco.setCliente(cliente2);

        Assertions.assertEquals(cliente2, endereco.getCliente());
    }

    @Test
    void setIdandGetId() {
        endereco.setId(2);

        Assertions.assertEquals(2, endereco.getId());
    }



    @Test
    void testEquals() {
        EnderecoDTO endereco2 = new EnderecoDTO(1,"Avenida tancredo neves","1234", "casa 123");

        Assertions.assertEquals(endereco, endereco2);
    }

    @Test
    void canEqual() {
        EnderecoDTO endereco2 = new EnderecoDTO(1,"Avenida tancredo neves","1234", "casa 123");

        Assertions.assertTrue(endereco.canEqual(endereco2));
    }
    @Test
    void testHashCode(){
        EnderecoDTO endereco2 = new EnderecoDTO(1,"Avenida tancredo neves","1234", "casa 123");

        Assertions.assertEquals(endereco.hashCode(), endereco2.hashCode());
    }

    @Test
    void testToString(){
        EnderecoDTO endereco2 = new EnderecoDTO(1,"Avenida tancredo neves","1234", "casa 123");
        Assertions.assertEquals(endereco2.toString(), endereco.toString());
    }



}