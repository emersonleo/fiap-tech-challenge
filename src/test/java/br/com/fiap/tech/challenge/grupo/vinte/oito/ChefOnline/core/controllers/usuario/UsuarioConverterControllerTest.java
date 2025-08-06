package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.UsuarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IUsuarioDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class UsuarioConverterControllerTest {

    @Mock
    private IUsuarioDataSource usuarioDataSource;

    @Mock
    private IRestauranteDataSource restauranteDataSource;

    private UsuarioConverterController controller;
    private Cliente cliente;
    private Proprietario proprietario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new UsuarioConverterController(usuarioDataSource, restauranteDataSource);

        cliente = new Cliente(
                1L,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null
        );

        proprietario = new Proprietario(
                1L,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null
        );
    }

    @Test
    @DisplayName("Deve converter cliente para proprietário com sucesso")
    void deveConverterClienteParaProprietarioComSucesso() {
        // arrange
        Long usuarioId = 1L;
        when(usuarioDataSource.buscaUsuarioPorId(usuarioId)).thenReturn(Optional.of(cliente));
        when(usuarioDataSource.atualizaUsuario(any())).thenReturn(proprietario);

        // act
        UsuarioDTO resultado = controller.converterTipoUsuario(usuarioId, NomeDoTipo.PROPRIETARIO);

        // assert
        assertNotNull(resultado);
        assertEquals(proprietario.getId(), resultado.id());
        assertEquals(proprietario.getNome(), resultado.nome());
        assertEquals(proprietario.getEmail(), resultado.email());
        assertEquals(NomeDoTipo.PROPRIETARIO, resultado.tipo());

        verify(usuarioDataSource).buscaUsuarioPorId(usuarioId);
        verify(usuarioDataSource).atualizaUsuario(any());
    }

}