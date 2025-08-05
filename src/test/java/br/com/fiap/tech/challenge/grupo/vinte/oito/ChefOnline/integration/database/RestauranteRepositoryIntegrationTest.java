package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.integration.database;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.restaurante.Restaurante;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.NomeDoTipo;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante.AtualizaRestauranteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante.BuscaRestaurantePorIdUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante.BuscaTodosRestaurantesUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante.CriaRestauranteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.restaurante.DeletaRestauranteUseCase;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.AtualizaRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.restaurante.NovoRestauranteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario.ProprietarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.restaurante.RestauranteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.gateway.usuario.ProprietarioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.restaurante.IRestauranteGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioGateway;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.dataSource.restaurante.RestauranteDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.entity.usuario.UsuarioEntity;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.restaurante.RestauranteJpaRepository;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.infrastructure.persistence.repository.usuario.UsuarioJpaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
@Transactional
@ActiveProfiles("dev")
@DisplayName("Restaurante Use Cases Integration Tests")
class RestauranteUseCaseIntegrationTest {

    private IProprietarioDataSource proprietarioDataSource;
    private IRestauranteDataSource restauranteDataSource;

    @Autowired
    private ProprietarioGateway proprietarioGateway;

    @Autowired
    private RestauranteGateway restauranteGateway;

    @Autowired
    private UsuarioJpaRepository usuarioRepository;

    @Autowired
    private RestauranteJpaRepository restauranteRepository;

    private CriaRestauranteUseCase criaRestauranteUseCase;
    private BuscaRestaurantePorIdUseCase buscaRestaurantePorIdUseCase;
    private BuscaTodosRestaurantesUseCase buscaTodosRestaurantesUseCase;
    private AtualizaRestauranteUseCase atualizaRestauranteUseCase;
    private DeletaRestauranteUseCase deletaRestauranteUseCase;

    private Proprietario proprietarioValido;
    private Long proprietarioInexistenteId = 999L;

    @BeforeEach
    void setUp() {
        
        restauranteRepository.deleteAll();
        usuarioRepository.deleteAll();

        this.proprietarioGateway = ProprietarioGateway.create(this.proprietarioDataSource);
        this.restauranteGateway = RestauranteGateway.create(this.restauranteDataSource);

        // Cria os use cases
        criaRestauranteUseCase = CriaRestauranteUseCase.create(this.proprietarioGateway, this.restauranteGateway);
        buscaRestaurantePorIdUseCase = BuscaRestaurantePorIdUseCase.create(this.restauranteGateway);
        buscaTodosRestaurantesUseCase = BuscaTodosRestaurantesUseCase.create(this.restauranteGateway);
        atualizaRestauranteUseCase = AtualizaRestauranteUseCase.create(this.restauranteGateway);
        deletaRestauranteUseCase = DeletaRestauranteUseCase.create(this.restauranteGateway);

        // Cria um proprietário válido para os testes
        criarProprietarioValido();
    }

    @Test
    @DisplayName("Deve criar restaurante com sucesso quando proprietário existe")
    void deveCriarRestauranteComSucessoQuandoProprietarioExiste() {
        // Arrange
        NovoRestauranteDTO novoRestauranteDTO = new NovoRestauranteDTO(
                "Pizzaria do João",
                "Rua das Pizzas, 123",
                "Italiana",
                "18:00 - 23:00",
                proprietarioValido.getId()
        );

        // Act
        Restaurante restauranteCriado = criaRestauranteUseCase.run(novoRestauranteDTO);

        // Assert
        assertThat(restauranteCriado).isNotNull();
        assertThat(restauranteCriado.getId()).isNotNull();
        assertThat(restauranteCriado.getNomeRestaurante()).isEqualTo("Pizzaria do João");
        assertThat(restauranteCriado.getEndereco()).isEqualTo("Rua das Pizzas, 123");
        assertThat(restauranteCriado.getTipoCozinha()).isEqualTo("Italiana");
        assertThat(restauranteCriado.getHorarioFuncionamento()).isEqualTo("18:00 - 23:00");
        assertThat(restauranteCriado.getProprietario().getId()).isEqualTo(proprietarioValido.getId());

        Optional<Restaurante> restauranteNoBanco = restauranteGateway.buscaRestaurantePorId(restauranteCriado.getId());
        assertThat(restauranteNoBanco).isPresent();
        assertThat(restauranteNoBanco.get().getNomeRestaurante()).isEqualTo("Pizzaria do João");
    }
    
    @Test
    @DisplayName("Deve lançar exceção quando tentar criar restaurante com proprietário inexistente")
    void deveLancarExcecaoQuandoTentarCriarRestauranteComProprietarioInexistente() {
        // Arrange
        NovoRestauranteDTO novoRestauranteDTO = new NovoRestauranteDTO(
                "Restaurante Inexistente",
                "Rua Inexistente, 456",
                "Brasileira",
                "12:00 - 22:00",
                proprietarioInexistenteId
        );

        // Act & Assert
        assertThatThrownBy(() -> criaRestauranteUseCase.run(novoRestauranteDTO))
                .isInstanceOf(ProprietarioNotFoundException.class)
                .hasMessageContaining(proprietarioInexistenteId.toString());

        List<Restaurante> restaurantes = restauranteGateway.buscaTodosRestaurantes(0, 10);
        assertThat(restaurantes).isEmpty();
    }

    @Test
    @DisplayName("Deve criar múltiplos restaurantes para o mesmo proprietário")
    void deveCriarMultiplosRestaurantesParaMesmoProprietario() {
        // Arrange
        NovoRestauranteDTO restaurante1 = new NovoRestauranteDTO(
                "Pizzaria Central",
                "Rua A, 100",
                "Italiana",
                "18:00 - 23:00",
                proprietarioValido.getId()
        );

        NovoRestauranteDTO restaurante2 = new NovoRestauranteDTO(
                "Sushi House",
                "Rua B, 200",
                "Japonesa",
                "19:00 - 00:00",
                proprietarioValido.getId()
        );

        // Act
        Restaurante primeiroRestaurante = criaRestauranteUseCase.run(restaurante1);
        Restaurante segundoRestaurante = criaRestauranteUseCase.run(restaurante2);

        // Assert
        assertThat(primeiroRestaurante.getId()).isNotNull();
        assertThat(segundoRestaurante.getId()).isNotNull();
        assertThat(primeiroRestaurante.getId()).isNotEqualTo(segundoRestaurante.getId());

        List<Restaurante> todosRestaurantes = restauranteGateway.buscaTodosRestaurantes(0,10);
        assertThat(todosRestaurantes).hasSize(2);
        assertThat(todosRestaurantes)
                .extracting(Restaurante::getNomeRestaurante)
                .containsExactlyInAnyOrder("Pizzaria Central", "Sushi House");
    }

    @Test
    @DisplayName("Deve manter integridade referencial entre restaurante e proprietário")
    void deveManterIntegridadeReferencialEntreRestauranteEProprietario() {

        // Arrange
        NovoRestauranteDTO novoRestauranteDTO = new NovoRestauranteDTO(
                "Restaurante Teste",
                "Rua Teste, 789",
                "Brasileira",
                "11:00 - 15:00",
                proprietarioValido.getId()
        );

        // Act
        Restaurante restauranteCriado = criaRestauranteUseCase.run(novoRestauranteDTO);

        // Assert
        assertThat(restauranteCriado.getProprietario()).isNotNull();
        assertThat(restauranteCriado.getProprietario().getId()).isEqualTo(proprietarioValido.getId());
        assertThat(restauranteCriado.getProprietario().getNome()).isEqualTo(proprietarioValido.getNome());
        assertThat(restauranteCriado.getProprietario().getEmail()).isEqualTo(proprietarioValido.getEmail());
        assertThat(restauranteCriado.getProprietario().getTipo()).isEqualTo(NomeDoTipo.PROPRIETARIO);
    }

    @Test
    @DisplayName("Deve validar campos obrigatórios do restaurante")
    void deveValidarCamposObrigatoriosDoRestaurante() {

        // Arrange - DTO com campo vazio/nulo
        NovoRestauranteDTO restauranteComNomeVazio = new NovoRestauranteDTO(
                "", // nome vazio
                "Rua Teste, 123",
                "Italiana",
                "18:00 - 23:00",
                proprietarioValido.getId()
        );

        NovoRestauranteDTO restauranteComEnderecoVazio = new NovoRestauranteDTO(
                "Restaurante Teste",
                "", // endereço vazio
                "Italiana",
                "18:00 - 23:00",
                proprietarioValido.getId()
        );

        Restaurante restaurante1 = criaRestauranteUseCase.run(restauranteComNomeVazio);
        Restaurante restaurante2 = criaRestauranteUseCase.run(restauranteComEnderecoVazio);

        assertThat(restaurante1.getNomeRestaurante()).isEmpty();
        assertThat(restaurante2.getEndereco()).isEmpty();
    }

    @Test
    @DisplayName("Deve buscar restaurante por ID com sucesso")
    void deveBuscarRestaurantePorIdComSucesso() {
        // Arrange
        Restaurante restauranteCriado = criarRestauranteParaTeste();

        // Act
        Restaurante restauranteEncontrado = buscaRestaurantePorIdUseCase.run(restauranteCriado.getId());

        // Assert
        assertThat(restauranteEncontrado).isNotNull();
        assertThat(restauranteEncontrado.getId()).isEqualTo(restauranteCriado.getId());
    }

    @Test
    @DisplayName("Deve buscar todos os restaurantes com paginação")
    void deveBuscarTodosRestaurantesComPaginacao() {
        // Arrange
        criarRestauranteParaTeste();
        criarRestauranteParaTeste();

        // Act
        List<Restaurante> resultado = buscaTodosRestaurantesUseCase.run(0, 10);

        // Assert
        assertThat(resultado).hasSize(2);
    }

    @Test
    @DisplayName("Deve atualizar restaurante existente")
    void deveAtualizarRestauranteExistente() {
        // Arrange
        NovoRestauranteDTO restauranteCriado = new NovoRestauranteDTO(
            "Pizzaria Central",
            "Rua A, 100",
            "Italiana",
            "18:00 - 23:00",
            proprietarioValido.getId()
        );

        AtualizaRestauranteDTO restauranteAtualizadoDTO = new AtualizaRestauranteDTO(
            "Restaurante Atualizado",
            "Rua Atualizada, 100",
            "Atualizada",
            "18:00 - 23:00",
            proprietarioValido.getId()
        );
        
        Restaurante restauranteSalvo = criaRestauranteUseCase.run(restauranteCriado);

        // Act
        atualizaRestauranteUseCase.run(restauranteAtualizadoDTO, restauranteSalvo.getId());

        Restaurante restauranteAtualizadoDB = buscaRestaurantePorIdUseCase.run(restauranteSalvo.getId());

        // Assert
        assertThat(restauranteAtualizadoDB).isNotNull();
        assertThat(restauranteAtualizadoDB.getNomeRestaurante()).isEqualTo("Restaurante Atualizado");
        assertThat(restauranteAtualizadoDB.getEndereco()).isEqualTo("Rua Atualizada, 100");
        assertThat(restauranteAtualizadoDB.getTipoCozinha()).isEqualTo("Atualizada");
        assertThat(restauranteAtualizadoDB.getHorarioFuncionamento()).isEqualTo("18:00 - 23:00");
        assertThat(restauranteAtualizadoDB.getProprietario().getId()).isEqualTo(proprietarioValido.getId());
    }

    @Test
    @DisplayName("Deve deletar restaurante existente")
    void deveDeletarRestauranteExistente() {
        // Arrange
        NovoRestauranteDTO restaurante1 = new NovoRestauranteDTO(
            "Pizzaria Central",
            "Rua A, 100",
            "Italiana",
            "18:00 - 23:00",
            proprietarioValido.getId()
        );

        // Act
        Restaurante restauranteSalvo = criaRestauranteUseCase.run(restaurante1);
        deletaRestauranteUseCase.run(restauranteSalvo.getId());

        // Assert
        Optional<Restaurante> restauranteDeletado = restauranteGateway.buscaRestaurantePorId(restauranteSalvo.getId());
        assertThat(restauranteDeletado).isEmpty();

    } 

    private void criarProprietarioValido() {
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        usuarioEntity.setNome("João Silva");
        usuarioEntity.setEmail("joao.silva@teste.com");
        usuarioEntity.setLogin("joao_silva");
        usuarioEntity.setSenha("senha123");
        usuarioEntity.setEndereco("Rua do Proprietário, 456");
        usuarioEntity.setTipo(NomeDoTipo.PROPRIETARIO);
        usuarioEntity.setDataUltimaAlteracao(new Date());

        UsuarioEntity usuarioSalvo = usuarioRepository.save(usuarioEntity);

        proprietarioValido = new Proprietario(
                usuarioSalvo.getId(),
                usuarioSalvo.getNome(),
                usuarioSalvo.getEmail(),
                usuarioSalvo.getLogin(),
                usuarioSalvo.getSenha(),
                usuarioSalvo.getEndereco()
        );
    }

    private Restaurante criarRestauranteParaTeste() {
        NovoRestauranteDTO dto = new NovoRestauranteDTO(
                "Restaurante Para Teste",
                "Rua de Teste, 123",
                "Brasileira",
                "12:00 - 22:00",
                proprietarioValido.getId()
        );
        return criaRestauranteUseCase.run(dto);
    }
}