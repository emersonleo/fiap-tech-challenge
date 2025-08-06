package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Usuario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.UsuarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.restaurante.RestauranteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario.UsuarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ConverterTipoUsuarioUseCaseTest {

    @Mock
    private UsuarioGateway usuarioGateway;

    @Mock
    private RestauranteGateway restauranteGateway;

    private ConverterTipoUsuarioUseCase useCase;
    private Cliente cliente;
    private Proprietario proprietario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        useCase = ConverterTipoUsuarioUseCase.create(usuarioGateway, restauranteGateway);

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
                null);
    }

    @Test
    @DisplayName("Deve converter cliente para proprietário")
    void deveConverterClienteParaProprietario() {
        // arrange
        when(usuarioGateway.buscaUsuarioPorId(1L)).thenReturn(Optional.of(cliente));
        when(usuarioGateway.atualizaUsuario(any())).thenReturn(proprietario);

        // act
        Usuario resultado = useCase.run(1L, NomeDoTipo.PROPRIETARIO);

        // assert
        assertNotNull(resultado);
        assertEquals(NomeDoTipo.PROPRIETARIO, resultado.getTipo());
        verify(usuarioGateway).buscaUsuarioPorId(1L);
        verify(usuarioGateway).atualizaUsuario(any());
        verify(restauranteGateway, never()).deletaRestaurantePorProprietarioId(any());
    }

    @Test
    @DisplayName("Deve converter proprietário para cliente")
    void deveConverterProprietarioParaCliente() {
        // arrange
        when(usuarioGateway.buscaUsuarioPorId(1L)).thenReturn(Optional.of(proprietario));
        when(usuarioGateway.atualizaUsuario(any())).thenReturn(cliente);

        // act
        Usuario resultado = useCase.run(1L, NomeDoTipo.CLIENTE);

        // assert
        assertNotNull(resultado);
        assertEquals(NomeDoTipo.CLIENTE, resultado.getTipo());
        verify(usuarioGateway).buscaUsuarioPorId(1L);
        verify(usuarioGateway).atualizaUsuario(any());
        verify(restauranteGateway).deletaRestaurantePorProprietarioId(1L);
    }

    @Test
    @DisplayName("Deve retornar mesmo usuário quando tipo é igual")
    void deveRetornarMesmoUsuarioQuandoTipoIgual() {
        // arrange
        when(usuarioGateway.buscaUsuarioPorId(1L)).thenReturn(Optional.of(cliente));

        // act
        Usuario resultado = useCase.run(1L, NomeDoTipo.CLIENTE);

        // assert
        assertNotNull(resultado);
        assertEquals(NomeDoTipo.CLIENTE, resultado.getTipo());
        verify(usuarioGateway).buscaUsuarioPorId(1L);
        verify(usuarioGateway, never()).atualizaUsuario(any());
        verify(restauranteGateway, never()).deletaRestaurantePorProprietarioId(any());
    }

    @Test
    @DisplayName("Deve lançar exceção quando usuário não encontrado")
    void deveLancarExcecaoQuandoUsuarioNaoEncontrado() {
        // arrange
        when(usuarioGateway.buscaUsuarioPorId(1L)).thenReturn(Optional.empty());

        // act & assert
        assertThrows(UsuarioNotFoundException.class, () ->
                useCase.run(1L, NomeDoTipo.PROPRIETARIO)
        );
        verify(usuarioGateway).buscaUsuarioPorId(1L);
        verify(usuarioGateway, never()).atualizaUsuario(any());
        verify(restauranteGateway, never()).deletaRestaurantePorProprietarioId(any());
    }
}