package com.senai.JOGGAR;

import org.junit.jupiter.api.Assertions;
// import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.senai.JOGGAR.entities.Endereco;
import com.senai.JOGGAR.repositories.EnderecoRepository;
import com.senai.JOGGAR.services.EnderecoService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class EnderecoServiceTest {

    @Mock
    private EnderecoRepository enderecoRepository;

    @InjectMocks
    private EnderecoService enderecoService;

    // @BeforeEach
    // public void setup() {
    //     enderecoService = new EnderecoService(enderecoRepository);
    // }

    @Test
    public void testCreate_ValidEndereco_ReturnsCreatedEndereco() {
        // Cria um ednereço exemplo
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLocal("Local Teste");

        // Substitue o método de salvamento do repositório por um stub que retorna o 'Endereco' criado.
        Mockito.when(enderecoRepository.save(Mockito.any(Endereco.class))).thenReturn(endereco);

        // Chama o método de criação
        Endereco createdEndereco = enderecoService.create(endereco);

        // Verifica que o método de salvamento do repositório foi chamado com o 'Endereco' correto
        Mockito.verify(enderecoRepository).save(endereco);

        // Asserts do 'Endereco' criado
        Assertions.assertEquals(endereco, createdEndereco);
    }

    @Test
    public void testRead_ExistingId_ReturnsEndereco() {
        // Create a sample Endereco
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLocal("Local Teste");
    
        // Stub do método findById do repositório para retornar um optional contendo o 'Endereco' criado
        Mockito.when(enderecoRepository.findById(1L)).thenReturn(Optional.of(endereco));

        // Chamada do método de leitura
        Endereco readEndereco = enderecoService.read(1L);

        // Verifica que o método findById do repositório foi chamado com o id correto
        Mockito.verify(enderecoRepository).findById(1L);

        // Asserts do 'Endereco' lido
        Assertions.assertEquals(endereco, readEndereco);
    }

    @Test
    public void testList_ReturnsListOfEnderecos() {
        // Cria uma lista de exemplos de endereço
        List<Endereco> enderecos = new ArrayList<>();
        Endereco endereco1 = new Endereco();
        endereco1.setId(1L);
        endereco1.setLocal("Local Teste");

        enderecos.add(endereco1);
        Endereco endereco2 = new Endereco();
        endereco2.setId(2L);
        endereco2.setLocal("Local Teste");

        enderecos.add(endereco2);

        // Stub do método findAll do repositório para retornar a lista de endereços criada
        Mockito.when(enderecoRepository.findAll()).thenReturn(enderecos);

        // Chama o método de listagem
        List<Endereco> resultList = enderecoService.list();

        // Verifica que o método findAll do repositório foi chamado
        Mockito.verify(enderecoRepository).findAll();

        // Assert para a lista de endereços
        Assertions.assertEquals(enderecos, resultList);
    }

    @Test
    public void testDelete_ExistingId_DeletesEndereco() {
        // Chama o método de deleção
        enderecoService.delete(1L);

        // Verifica que o método deleteById do repositório foi chamado com o id correto
        Mockito.verify(enderecoRepository).deleteById(1L);
    }

    @Test
    public void testUpdate_ExistingEndereco_ReturnsUpdatedEndereco() {
        // Cria um exemplo de Endereco
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLocal("Local Teste");

        // Stub do método existsById do repositório para retornar true
        Mockito.when(enderecoRepository.existsById(1L)).thenReturn(true);

        // Stub do método save do repositório para retornar o Endereco criado
        Mockito.when(enderecoRepository.save(Mockito.any(Endereco.class))).thenReturn(endereco);

        // Chama o método de atualização
        Endereco updatedEndereco = enderecoService.update(endereco);

        // Verifica que o método existsById do repositório foi chamado com o id correto
        Mockito.verify(enderecoRepository).existsById(1L);

        // Verifica que o método save do repositório foi chamado com o Endereco correto
        Mockito.verify(enderecoRepository).save(endereco);

        // Assert que o Endereco retornado é o mesmo que foi criado
        Assertions.assertEquals(endereco, updatedEndereco);
    }

    @Test
    public void testUpdate_NonExistingEndereco_ReturnsNull() {
        // Cria um exemplo de Endereco
        Endereco endereco = new Endereco();
        endereco.setId(1L);
        endereco.setLocal("Local teste");

        // Stub do método existsById do repositório para retornar false
        Mockito.when(enderecoRepository.existsById(1L)).thenReturn(false);

        // Chama o método de atualização
        Endereco updatedEndereco = enderecoService.update(endereco);

        // Verifica que o método existsById do repositório foi chamado com o id correto
        Mockito.verify(enderecoRepository).existsById(1L);

        // Verifica que o método save do repositório nunca foi chamado
        Mockito.verify(enderecoRepository, Mockito.never()).save(Mockito.any(Endereco.class));

        // Assert que o Endereco retornado é null
        Assertions.assertNull(updatedEndereco);
    }
}
