package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.integration.api;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.restaurante.RestauranteController;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.AtualizaRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.NovoRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.RestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario.ProprietarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioDataSource;

@SpringBootTest
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayName("Testes de Integração - RestauranteController + UseCases + DataSources")
class RestauranteApiIntegrationTest {

    @MockBean
    private IRestauranteDataSource restauranteDataSource;

    @MockBean
    private IProprietarioDataSource proprietarioDataSource;

    private RestauranteController restauranteController;

    private Proprietario proprietarioMock;
    private Restaurante restauranteMock;
    private NovoRestauranteDTO novoRestauranteDTO;
    private AtualizaRestauranteDTO atualizaRestauranteDTO;

    @BeforeEach
    void setUp() {
        restauranteController = new RestauranteController(proprietarioDataSource, restauranteDataSource);

        // Setup proprietário mock
        proprietarioMock = new Proprietario(1L, "João Silva", "joao@email.com", "teste", "teste", "Rua do Proprietário, 123");

        // Setup restaurante mock
        restauranteMock = new Restaurante(
            1L,
            "Pizzaria do João",
            "Rua das Pizzas, 123",
            "Italiana",
            "18:00-23:00",
            proprietarioMock
        );

        // Setup DTOs
        novoRestauranteDTO = new NovoRestauranteDTO(
            "Pizzaria do João",
            "Rua das Pizzas, 123",
            "Italiana",
            "18:00-23:00",
            1L
        );

        atualizaRestauranteDTO = new AtualizaRestauranteDTO(
             "Outra pizzaria do João",
            "Rua das Pizzas, 123",
            "Italiana",
            "18:00-23:00",
            1L
        );
    }

    @Test
    @Order(1)
    @DisplayName("Deve criar restaurante com sucesso quando proprietário existe")
    void deveCriarRestauranteComSucessoQuandoProprietarioExiste() {
        
        // Arrange
        when(proprietarioDataSource.buscaProprietarioPorId(1L))
            .thenReturn(Optional.of(proprietarioMock));
        when(restauranteDataSource.adicionaRestaurante(any(Restaurante.class)))
            .thenReturn(restauranteMock);

        // Act
        RestauranteDTO resultadoRestaurante = restauranteController.criaRestaurante(novoRestauranteDTO);

        // Assert
        assertNotNull(resultadoRestaurante);
        assertEquals("Pizzaria do João", resultadoRestaurante.nomeRestaurante());
        assertEquals("Rua das Pizzas, 123", resultadoRestaurante.endereco());
        assertEquals("Italiana", resultadoRestaurante.tipoCozinha());
        assertEquals("18:00-23:00", resultadoRestaurante.horarioFuncionamento());
        assertEquals(1L, resultadoRestaurante.donoRestaurante().id());


        verify(proprietarioDataSource).buscaProprietarioPorId(1L);
        verify(restauranteDataSource).adicionaRestaurante(any(Restaurante.class));
    }

    @Test
    @Order(2)
    @DisplayName("Deve lançar exceção ao criar restaurante com proprietário inexistente")
    void deveLancarExcecaoAoCriarRestauranteComProprietarioInexistente() {
        // Arrange
        when(proprietarioDataSource.buscaProprietarioPorId(999L))
            .thenReturn(Optional.empty());

        NovoRestauranteDTO dtoComProprietarioInexistente = new NovoRestauranteDTO(
            "Restaurante Órfão",
            "Rua Inexistente, 999",
            "Comida Fantasma",
            "00:00-00:00",
            999L
        );

        // Act & Assert
        assertThrows(ProprietarioNotFoundException.class, () -> {
            restauranteController.criaRestaurante(dtoComProprietarioInexistente);
        });


        verify(proprietarioDataSource).buscaProprietarioPorId(999L);
        verify(restauranteDataSource, never()).adicionaRestaurante(any());
    }

    @Test
    @Order(3)
    @DisplayName("Deve buscar restaurante por ID quando existir")
    void deveBuscarRestaurantePorIdQuandoExistir() {
        // Arrange
        Long id = 1L;
        when(restauranteDataSource.buscaRestaurantePorId(id))
            .thenReturn(Optional.of(restauranteMock));

        // Act
        RestauranteDTO resultadoRestaurante = restauranteController.buscaRestaurantePorId(id);

        // Assert
        assertNotNull(resultadoRestaurante);
        assertEquals(1L, resultadoRestaurante.id());
        assertEquals("Pizzaria do João", resultadoRestaurante.nomeRestaurante());
        assertEquals("Rua das Pizzas, 123", resultadoRestaurante.endereco());
        assertEquals("Italiana", resultadoRestaurante.tipoCozinha());
        assertEquals("18:00-23:00", resultadoRestaurante.horarioFuncionamento());


        verify(restauranteDataSource).buscaRestaurantePorId(id);
    }

    @Test
    @Order(4)
    @DisplayName("Deve lançar exceção ao buscar restaurante inexistente")
    void deveLancarExcecaoAoBuscarRestauranteInexistente() {
        // Arrange
        Long idInexistente = 999L;
        when(restauranteDataSource.buscaRestaurantePorId(idInexistente))
            .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            restauranteController.buscaRestaurantePorId(idInexistente);
        });


        verify(restauranteDataSource).buscaRestaurantePorId(idInexistente);
    }

    @Test
    @Order(5)
    @DisplayName("Deve buscar todos os restaurantes com paginação")
    void deveBuscarTodosRestaurantesComPaginacao() {
        // Arrange
        int page = 0;
        int size = 10;
        
        Restaurante restaurante2 = new Restaurante(
            2L,
            "Hamburgeria do Pedro",
            "Rua dos Hambúrgueres, 456",
            "Americana",
            "11:00-22:00",
            proprietarioMock
        );

        List<Restaurante> restaurantesMock = List.of(restauranteMock, restaurante2);
        
        when(restauranteDataSource.buscaTodosRestaurantes(page, size))
            .thenReturn(restaurantesMock);

        // Act
        List<RestauranteDTO> resultadoRestaurante = restauranteController.buscaTodosRestaurantes(page, size);

        // Assert
        assertNotNull(resultadoRestaurante);
        assertEquals(2, resultadoRestaurante.size());
        
        // Verificar primeiro restaurante
        RestauranteDTO primeiro = resultadoRestaurante.get(0);
        assertEquals("Pizzaria do João", primeiro.nomeRestaurante());
        assertEquals("Italiana", primeiro.tipoCozinha());
        
        // Verificar segundo restaurante
        RestauranteDTO segundo = resultadoRestaurante.get(1);
        assertEquals("Hamburgeria do Pedro", segundo.nomeRestaurante());
        assertEquals("Americana", segundo.tipoCozinha());


        verify(restauranteDataSource).buscaTodosRestaurantes(page, size);
    }

    @Test
    @Order(6)
    @DisplayName("Deve retornar lista vazia quando não houver restaurantes")
    void deveRetornarListaVaziaQuandoNaoHouverRestaurantes() {
        // Arrange
        int page = 0;
        int size = 10;
        when(restauranteDataSource.buscaTodosRestaurantes(page, size))
            .thenReturn(List.of());

        // Act
        List<RestauranteDTO> resultadoRestaurante = restauranteController.buscaTodosRestaurantes(page, size);

        // Assert
        assertNotNull(resultadoRestaurante);
        assertTrue(resultadoRestaurante.isEmpty());


        verify(restauranteDataSource).buscaTodosRestaurantes(page, size);
    }

    @Test
    @Order(7)
    @DisplayName("Deve atualizar restaurante existente")
    void deveAtualizarRestauranteExistente() {
        // Arrange
        Long id = 1L;
        
        // Mock: simular que a atualização não retorna nada (void)
        doNothing().when(restauranteDataSource).atualizaRestaurante(any(Restaurante.class));

        // Act & Assert - não deve lançar exceção
        assertDoesNotThrow(() -> {
            restauranteController.atualizaRestaurante(atualizaRestauranteDTO, id);
        });


        verify(restauranteDataSource).atualizaRestaurante(any(Restaurante.class));
    }

    @Test
    @Order(8)
    @DisplayName("Deve deletar restaurante existente")
    void deveDeletarRestauranteExistente() {
        // Arrange
        Long id = 1L;
        doNothing().when(restauranteDataSource).deletaRestaurante(any(Restaurante.class));

        // Act & Assert - não deve lançar exceção
        assertDoesNotThrow(() -> {
            restauranteController.deletaRestaurante(id);
        });


        verify(restauranteDataSource).deletaRestaurante(any(Restaurante.class));
    }

    @Test
    @Order(9)
    @DisplayName("Deve integrar corretamente todos os componentes no fluxo de criação")
    void deveIntegrarCorretamenteTodosComponentesNoFluxoDeCriacao() {
        // Arrange - Simular o fluxo completo
        when(proprietarioDataSource.buscaProprietarioPorId(1L))
            .thenReturn(Optional.of(proprietarioMock));
        when(restauranteDataSource.adicionaRestaurante(any(Restaurante.class)))
            .thenReturn(restauranteMock);

        // Act
        RestauranteDTO resultadoRestaurante = restauranteController.criaRestaurante(novoRestauranteDTO);

        // Assert - Verificar se a integração funcionou
        assertNotNull(resultadoRestaurante);
        
        // Verificar se os dados foram mapeados corretamente
        assertEquals(novoRestauranteDTO.nomeRestaurante(), resultadoRestaurante.nomeRestaurante());
        assertEquals(novoRestauranteDTO.endereco(), resultadoRestaurante.endereco());
        assertEquals(novoRestauranteDTO.tipoCozinha(), resultadoRestaurante.tipoCozinha());
        assertEquals(novoRestauranteDTO.horarioFuncionamento(), resultadoRestaurante.horarioFuncionamento());

        assertNotNull(resultadoRestaurante.donoRestaurante());
        assertEquals(proprietarioMock.getId(), resultadoRestaurante.donoRestaurante().id());
        assertEquals(proprietarioMock.getNome(), resultadoRestaurante.donoRestaurante().nome());

        verify(proprietarioDataSource).buscaProprietarioPorId(1L);
        verify(restauranteDataSource).adicionaRestaurante(any(Restaurante.class));
    }

    @Test
    @Order(10)
    @DisplayName("Deve validar integração entre Gateway, UseCase e DataSource")
    void deveValidarIntegracaoEntreGatewayUseCaseEDataSource() {

        // Arrange
        when(proprietarioDataSource.buscaProprietarioPorId(anyLong()))
            .thenReturn(Optional.of(proprietarioMock));
        when(restauranteDataSource.adicionaRestaurante(any(Restaurante.class)))
            .thenReturn(restauranteMock);

        // Act
        RestauranteDTO resultadoRestaurante = restauranteController.criaRestaurante(novoRestauranteDTO);

        // Assert
        assertNotNull(resultadoRestaurante);
        
        verify(proprietarioDataSource, times(1)).buscaProprietarioPorId(1L);
        verify(restauranteDataSource, times(1)).adicionaRestaurante(any(Restaurante.class));
    }

    @Test
    @Order(11)
    @DisplayName("Deve lidar com diferentes tipos de cozinha")
    void deveLidarComDiferentesTiposDeCozinha() {
        // Arrange
        String[] tiposCozinha = {"Italiana", "Japonesa", "Brasileira", "Francesa", "Americana"};
        
        when(proprietarioDataSource.buscaProprietarioPorId(anyLong()))
            .thenReturn(Optional.of(proprietarioMock));

        for (int i = 0; i < tiposCozinha.length; i++) {
            String tipoCozinha = tiposCozinha[i];
            
            Restaurante restauranteComTipo = new Restaurante(
                (long) (i + 1),
                "Restaurante " + tipoCozinha,
                "Rua " + tipoCozinha + ", " + (i + 1),
                tipoCozinha,
                "12:00-22:00",
                proprietarioMock
            );

            when(restauranteDataSource.adicionaRestaurante(any(Restaurante.class)))
                .thenReturn(restauranteComTipo);

            NovoRestauranteDTO dto = new NovoRestauranteDTO(
                "Restaurante " + tipoCozinha,
                "Rua " + tipoCozinha + ", " + (i + 1),
                tipoCozinha,
                "12:00-22:00",
                1L
            );

            // Act
            RestauranteDTO resultadoRestaurante = restauranteController.criaRestaurante(dto);

            // Assert
            assertNotNull(resultadoRestaurante);
            assertEquals(tipoCozinha, resultadoRestaurante.tipoCozinha());
            assertEquals("Restaurante " + tipoCozinha, resultadoRestaurante.nomeRestaurante());
        }

        // Verify
        verify(proprietarioDataSource, times(tiposCozinha.length)).buscaProprietarioPorId(1L);
        verify(restauranteDataSource, times(tiposCozinha.length)).adicionaRestaurante(any(Restaurante.class));
    }
}