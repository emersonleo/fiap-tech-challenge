package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.ProprietarioDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.common.PresenterMasks;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.usuario.ProprietarioPresenter;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProprietarioPresenterTest {

    @Test
    void shouldConvertProprietarioToDTO() {
        Date testDate = new Date();
        Proprietario proprietario = new Proprietario(1L, "Carlos Silva", "carlos@restaurante.com", "carlos123", "senha123", "Av. Paulista, 1000");
        proprietario.setDataUltimaAlteracao(testDate);

        try (MockedStatic<PresenterMasks> mockedMasks = mockStatic(PresenterMasks.class)) {
            mockedMasks.when(() -> PresenterMasks.maskEmail("carlos@restaurante.com")).thenReturn("masked-email");
            mockedMasks.when(() -> PresenterMasks.maskValue("Av. Paulista, 1000")).thenReturn("masked-address");

            ProprietarioDTO result = ProprietarioPresenter.toDTO(proprietario);

            assertNotNull(result);
            assertEquals(1L, result.id());
            assertEquals("Carlos Silva", result.nome());
            assertEquals("masked-email", result.email());
            assertEquals("carlos123", result.login());
            assertEquals("masked-address", result.endereco());
            assertEquals(testDate, result.dataUltimaAlteracao());
        }
    }

    @Test
    void shouldCallMaskEmailMethod() {
        Proprietario proprietario = new Proprietario(2L, "Ana", "ana@exemplo.com", "ana", "senha", "Endereco");

        try (MockedStatic<PresenterMasks> mockedMasks = mockStatic(PresenterMasks.class)) {
            mockedMasks.when(() -> PresenterMasks.maskEmail(anyString())).thenReturn("masked-email");
            mockedMasks.when(() -> PresenterMasks.maskValue(anyString())).thenReturn("masked-address");

            ProprietarioPresenter.toDTO(proprietario);

            mockedMasks.verify(() -> PresenterMasks.maskEmail("ana@exemplo.com"));
        }
    }

    @Test
    void shouldCallMaskValueMethod() {
        Proprietario proprietario = new Proprietario(3L, "Pedro", "pedro@test.com", "pedro", "senha", "Rua Central, 555");

        try (MockedStatic<PresenterMasks> mockedMasks = mockStatic(PresenterMasks.class)) {
            mockedMasks.when(() -> PresenterMasks.maskEmail(anyString())).thenReturn("masked-email");
            mockedMasks.when(() -> PresenterMasks.maskValue(anyString())).thenReturn("masked-address");

            ProprietarioPresenter.toDTO(proprietario);

            mockedMasks.verify(() -> PresenterMasks.maskValue("Rua Central, 555"));
        }
    }
}