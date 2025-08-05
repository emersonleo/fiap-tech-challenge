package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.controllers.usuario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.TrocaSenhaDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.proprietario.AtualizaProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.proprietario.NovoProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IUsuarioDataSource;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.common.PresenterMasks;
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

class ProprietarioControllerTest {

    @Mock
    private IProprietarioDataSource proprietarioDataSource;

    @Mock
    private IUsuarioDataSource usuarioDataSource;

    private ProprietarioController proprietarioController;

    private Proprietario proprietario;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        proprietarioController = new ProprietarioController(proprietarioDataSource, usuarioDataSource);

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
    @DisplayName("Deve criar proprietário com sucesso")
    void deveCriarProprietarioComSucesso() {
        // arrange
        NovoProprietarioDTO dto = new NovoProprietarioDTO(
                null,
                "João da Silva",
                "joao.silva@teste.com",
                "login.teste.jao",
                "senha123",
                "Rua Exemplo, 123");

        when(proprietarioDataSource.adicionaProprietario(any())).thenReturn(proprietario);
        when(usuarioDataSource.buscaUsuarioPorEmail(any())).thenReturn(Optional.empty());

        // act
        var resultado = proprietarioController.criaProprietario(dto);

        // assert
        assertNotNull(resultado);
        assertEquals(dto.nome(), resultado.nome());
        assertEquals(PresenterMasks.maskEmail(dto.email()), resultado.email());
        verify(proprietarioDataSource).adicionaProprietario(any());
    }

    @Test
    @DisplayName("Deve buscar todos proprietários")
    void deveBuscarTodosProprietarios() {
        // arrange
        int page = 0;
        int size = 10;
        List<Proprietario> proprietarios = List.of(
                new Proprietario(1L, "Nome 1", "email1@test.com", "login1", "senha1", "endereco1"),
                new Proprietario(2L, "Nome 2", "email2@test.com", "login2", "senha2", "endereco2")
        );

        when(proprietarioDataSource.buscaTodosProprietarios(page, size)).thenReturn(proprietarios);

        // act
        var resultado = proprietarioController.buscaTodosProprietarios(page, size);

        // assert
        assertEquals(2, resultado.size());
        verify(proprietarioDataSource).buscaTodosProprietarios(page, size);
    }

    @Test
    @DisplayName("Deve buscar proprietário por ID")
    void deveBuscarProprietarioPorId() {
        // arrange
        when(proprietarioDataSource.buscaProprietarioPorId(1L)).thenReturn(Optional.of(proprietario));

        // act
        var resultado = proprietarioController.buscaProprietarioPorId(1L);

        // assert
        assertNotNull(resultado);
        assertEquals(proprietario.getNome(), resultado.nome());
        verify(proprietarioDataSource).buscaProprietarioPorId(1L);
    }

    @Test
    @DisplayName("Deve buscar proprietário por login")
    void deveBuscarProprietarioPorLogin() {
        // arrange
        String login = "email@test.com";

        when(proprietarioDataSource.buscaProprietarioPorEmail(login)).thenReturn(Optional.of(proprietario));
        when(proprietarioDataSource.buscaProprietarioPorLogin(login)).thenReturn(Optional.of(proprietario));

        // act
        var resultado = proprietarioController.buscaProprietarioPorLogin(login);

        // assert
        assertNotNull(resultado);
        assertEquals(PresenterMasks.maskEmail(proprietario.getEmail()), resultado.email());
        verify(proprietarioDataSource).buscaProprietarioPorLogin(login);
    }

    @Test
    @DisplayName("Deve atualizar proprietário com sucesso")
    void deveAtualizarProprietarioComSucesso() {
        // arrange
        Long id = 1L;
        AtualizaProprietarioDTO dto = new AtualizaProprietarioDTO(
                "Novo Nome",
                "novo@email.com",
                "novo.login",
                "novoEndereco");

        when(proprietarioDataSource.buscaProprietarioPorId(id)).thenReturn(Optional.of(proprietario));
        when(proprietarioDataSource.atualizaProprietario(any())).thenReturn(proprietario);

        // act
        var resultado = proprietarioController.atualizaProprietario(dto, id);

        // assert
        verify(proprietarioDataSource).buscaProprietarioPorId(id);
        verify(proprietarioDataSource).atualizaProprietario(proprietario);
    }

    @Test
    @DisplayName("Deve deletar proprietário com sucesso")
    void deveDeletarProprietarioComSucesso() {
        // arrange
        Long id = 1L;

        when(proprietarioDataSource.buscaProprietarioPorId(id)).thenReturn(Optional.of(proprietario));

        // act
        proprietarioController.deletaProprietario(id);

        // assert
        verify(proprietarioDataSource).deletaProprietario(proprietario);
    }

    @Test
    @DisplayName("Deve atualizar senha com sucesso")
    void deveAtualizarSenhaComSucesso() {
        // arrange
        String login = "email@test.com";
        TrocaSenhaDTO dto = new TrocaSenhaDTO(login, "senha123", "senha_nova");

        when(proprietarioDataSource.buscaProprietarioPorLogin(login)).thenReturn(Optional.of(proprietario));
        when(proprietarioDataSource.atualizaProprietario(any())).thenReturn(proprietario);

        // act
        var resultado = proprietarioController.atualizaSenha(dto);

        // assert
        assertNotNull(resultado);
        assertEquals(proprietario.getNome(), resultado.nome());
        verify(proprietarioDataSource).atualizaProprietario(any());
    }
}