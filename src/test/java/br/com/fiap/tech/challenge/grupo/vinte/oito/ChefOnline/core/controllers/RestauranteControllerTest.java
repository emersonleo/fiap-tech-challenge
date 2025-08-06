package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.restaurante.RestauranteController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.AtualizaRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.NovoRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioDataSource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class RestauranteControllerTest {

    @Mock
    private IProprietarioDataSource proprietarioDataSource;

    @Mock
    private IRestauranteDataSource restauranteDataSource;

    private RestauranteController restauranteController;

    private Proprietario proprietario;

    private Restaurante restaurante;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        restauranteController = new RestauranteController(proprietarioDataSource, restauranteDataSource);

        proprietario = new Proprietario(
                1L,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null);

        restaurante = new Restaurante(
                1L,
                "Restaurante Teste",
                "Endereço Teste",
                "Cozinha Teste",
                "Horário Teste",
                proprietario
        );
    }

    @Test
    @DisplayName("Deve criar um restaurante com sucesso")
    void deveCriarRestauranteComSucesso() {
        // arrange
        Long proprietarioId = 1L;
        NovoRestauranteDTO dto = new NovoRestauranteDTO(
                "Restaurante Teste",
                "Endereço Teste",
                "Italiana",
                "11:00-22:00",
                proprietarioId
        );

        when(proprietarioDataSource.buscaProprietarioPorId(proprietarioId)).thenReturn(Optional.of(proprietario));
        when(restauranteDataSource.adicionaRestaurante(any())).thenReturn(new Restaurante(
                1L,
                dto.nomeRestaurante(),
                dto.endereco(),
                dto.tipoCozinha(),
                dto.horarioFuncionamento(),
                proprietario
        ));

        // act
        var resultado = restauranteController.criaRestaurante(dto);

        // assert
        assertNotNull(resultado);
        assertEquals(dto.nomeRestaurante(), resultado.nomeRestaurante());
        verify(proprietarioDataSource).buscaProprietarioPorId(proprietarioId);
        verify(restauranteDataSource).adicionaRestaurante(any());
    }

    @Test
    @DisplayName("Deve buscar restaurante por ID")
    void deveBuscarRestaurantePorId() {
        // arrange
        Long restauranteId = 1L;
        Restaurante restaurante = new Restaurante(
                restauranteId,
                "Nome",
                "Endereço",
                "Italiana",
                "11:00-22:00",
                proprietario
        );

        when(restauranteDataSource.buscaRestaurantePorId(restauranteId)).thenReturn(Optional.of(restaurante));

        // act
        var resultado = restauranteController.buscaRestaurantePorId(restauranteId);

        // assert
        assertNotNull(resultado);
        assertEquals(restaurante.getNomeRestaurante(), resultado.nomeRestaurante());
        verify(restauranteDataSource).buscaRestaurantePorId(restauranteId);
    }

    @Test
    @DisplayName("Deve listar todos os restaurantes")
    void deveListarTodosRestaurantes() {
        // arrange
        int page = 0;
        int size = 10;
        List<Restaurante> restaurantes = List.of(
                new Restaurante(1L, "Rest 1", "End 1", "Italiana", "11:00-22:00", proprietario),
                new Restaurante(2L, "Rest 2", "End 2", "Japonesa", "11:00-22:00", proprietario)
        );

        when(restauranteDataSource.buscaTodosRestaurantes(page, size)).thenReturn(restaurantes);

        // act
        var resultado = restauranteController.buscaTodosRestaurantes(page, size);

        // assert
        assertEquals(2, resultado.size());
        verify(restauranteDataSource).buscaTodosRestaurantes(page, size);
    }

    @Test
    @DisplayName("Deve atualizar restaurante com sucesso")
    void deveAtualizarRestauranteComSucesso() {
        // arrange
        Long restauranteId = 1L;
        AtualizaRestauranteDTO dto = new AtualizaRestauranteDTO(
                "Novo Nome",
                "Novo Endereço",
                "Nova Especialidade",
                "10:00-23:00",
                1L
        );

        when(restauranteDataSource.buscaRestaurantePorId(restauranteId)).thenReturn(Optional.of(restaurante));

        // act
        restauranteController.atualizaRestaurante(dto, restauranteId);

        // assert
        verify(restauranteDataSource).buscaRestaurantePorId(restauranteId);
        verify(restauranteDataSource).atualizaRestaurante(restaurante);
    }

    @Test
    @DisplayName("Deve deletar restaurante com sucesso")
    void deveDeletarRestauranteComSucesso() {
        // arrange

        when(restauranteDataSource.buscaRestaurantePorId(restaurante.getId())).thenReturn(Optional.of(restaurante));

        // act
        restauranteController.deletaRestaurante(restaurante.getId());

        // assert
        verify(restauranteDataSource).buscaRestaurantePorId(restaurante.getId());
        verify(restauranteDataSource).deletaRestaurante(restaurante);
    }
}