package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases.usuario.proprietario;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.proprietario.AtualizaProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.exceptions.usuario.proprietario.ProprietarioNotFoundException;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioGateway;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class AtualizaProprietarioUseCaseTest {

    @Mock
    private IProprietarioGateway proprietarioGateway;

    private AtualizaProprietarioUseCase atualizaProprietarioUseCase;

    private Proprietario proprietario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        atualizaProprietarioUseCase = AtualizaProprietarioUseCase.create(proprietarioGateway);

        proprietario = new Proprietario(
                null,
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "senha123",
                "Rua Exemplo, 123",
                null);
    }

    @Test
    @DisplayName("Deve atualizar um proprietario com sucesso")
    void deveAtualizarProprietarioComSucesso() {
        //arrange
        Long idProprietario = 1L;
        when(proprietarioGateway.buscaProprietarioPorId(idProprietario)).thenReturn(Optional.of(proprietario));
        AtualizaProprietarioDTO atualizaProprietarioDTO = new AtualizaProprietarioDTO(
                "João da Silva",
                "joao.silva@teste.com",
                "joao.silva",
                "Rua Exemplo, 123"
        );

        //act
        atualizaProprietarioUseCase.run(atualizaProprietarioDTO, idProprietario);

        //assert
        verify(proprietarioGateway).atualizaProprietario(argThat(c ->
                c.getNome().equals(atualizaProprietarioDTO.nome()) &&
                        c.getEmail().equals(atualizaProprietarioDTO.email()) &&
                        c.getLogin().equals(atualizaProprietarioDTO.login()) &&
                        c.getEndereco().equals(atualizaProprietarioDTO.endereco())
        ));
    }

    @Test
    @DisplayName("Deve lançar exceção quando proprietario não existir")
    void deveLancarExcecaoQuandoProprietarioNaoExistir() {
        // Arrange
        Long idProprietario = 1L;
        when(proprietarioGateway.buscaProprietarioPorId(idProprietario)).thenReturn(Optional.empty());
        AtualizaProprietarioDTO atualizaProprietarioDTO = new AtualizaProprietarioDTO(
                "João da Silva",
                "joao.silva@email.com",
                "joao.silva",
                "Rua Exemplo, 123"
        );

        when(proprietarioGateway.buscaProprietarioPorId(idProprietario)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(ProprietarioNotFoundException.class, () -> {
            atualizaProprietarioUseCase.run(atualizaProprietarioDTO, idProprietario);
        });

        verify(proprietarioGateway).buscaProprietarioPorId(idProprietario);
        verify(proprietarioGateway, never()).atualizaProprietario(any());
    }

}
