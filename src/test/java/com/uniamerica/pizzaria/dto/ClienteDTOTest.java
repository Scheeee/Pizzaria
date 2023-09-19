package com.uniamerica.pizzaria.dto;


import com.uniamerica.pizzaria.entity.Endereco;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class ClienteDTOTest {

    Endereco endereco = new Endereco(1,"Avenida tancredo neves","1234", "casa 123");
    ClienteDTO cliente = new ClienteDTO(1,"Sche", "45-98034-3600", endereco);

    @Test
    void getIdandSetId() {

        cliente.setId(2);
        Assertions.assertEquals(2, cliente.getId());
    }
    @Test
    void getNomeandSetNome() {
        cliente.setNome("Gabi");

        Assertions.assertEquals("Gabi",cliente.getNome());
    }

    @Test
    void getTelefoneandSetTelefone() {
        cliente.setTelefone("45-99995-0385");

        Assertions.assertEquals("45-99995-0385", cliente.getTelefone());

    }

    @Test
    void getEnderecoandSetEndereco() {
        Endereco endereco2 = new Endereco(1,"Avenida Brasil","1234", "casa 123");
        cliente.setEndereco(endereco2);

        Assertions.assertEquals(endereco2, cliente.getEndereco());
    }


    @Test
    void testEquals() {
        ClienteDTO cliente2 = new ClienteDTO(1,"Sche", "45-98034-3600", endereco);

        Assertions.assertEquals(cliente, cliente2);
    }

    @Test
    void canEqual() {
        ClienteDTO cliente2 = new ClienteDTO(1,"Sche", "45-98034-3600", endereco);
        Assertions.assertTrue(cliente.canEqual(cliente2));
    }
    @Test
    void testHashCode(){
        ClienteDTO cliente2 = new ClienteDTO(1,"Sche", "45-98034-3600", endereco);
        Assertions.assertEquals(cliente.hashCode(), cliente2.hashCode());

    }
    @Test
    void testToString() {
        Assertions.assertEquals("ClienteDTO(id=1, nome=Sche, telefone=45-98034-3600, endereco=com.uniamerica.pizzaria.entity.Endereco@5ccddd20)", cliente.toString());

    }



}