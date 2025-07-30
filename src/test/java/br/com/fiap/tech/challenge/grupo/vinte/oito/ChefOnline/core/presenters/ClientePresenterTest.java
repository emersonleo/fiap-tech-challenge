package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters;

import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.dtos.usuario.ClienteDTO;
import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.common.PresenterMasks;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ClientePresenterTest {

    @Test
    void shouldConvertClienteToDTO() {
        Date testDate = new Date();
        Cliente cliente = new Cliente(1L, "João Silva", "joao@email.com", "joao123", "senha123", "Rua A, 123");
        cliente.setDataUltimaAlteracao(testDate);

        try (MockedStatic<PresenterMasks> mockedMasks = mockStatic(PresenterMasks.class)) {
            mockedMasks.when(() -> PresenterMasks.maskEmail("joao@email.com")).thenReturn("masked-email");
            mockedMasks.when(() -> PresenterMasks.maskValue("Rua A, 123")).thenReturn("masked-address");

            ClienteDTO result = ClientePresenter.toDTO(cliente);

            assertNotNull(result);
            assertEquals(1L, result.id());
            assertEquals("João Silva", result.nome());
            assertEquals("masked-email", result.email());
            assertEquals("joao123", result.login());
            assertEquals("masked-address", result.endereco());
            assertEquals(testDate, result.dataUltimaAlteracao());
        }
    }

    @Test
    void shouldCallMaskEmailMethod() {
        Cliente cliente = new Cliente(2L, "Maria", "maria@test.com", "maria", "senha", "Endereco");

        try (MockedStatic<PresenterMasks> mockedMasks = mockStatic(PresenterMasks.class)) {
            mockedMasks.when(() -> PresenterMasks.maskEmail(anyString())).thenReturn("masked-email");
            mockedMasks.when(() -> PresenterMasks.maskValue(anyString())).thenReturn("masked-address");

            ClientePresenter.toDTO(cliente);

            mockedMasks.verify(() -> PresenterMasks.maskEmail("maria@test.com"));
        }
    }

    @Test
    void shouldCallMaskValueMethod() {
        Cliente cliente = new Cliente(3L, "Pedro", "pedro@test.com", "pedro", "senha", "Rua das Flores, 789");

        try (MockedStatic<PresenterMasks> mockedMasks = mockStatic(PresenterMasks.class)) {
            mockedMasks.when(() -> PresenterMasks.maskEmail(anyString())).thenReturn("masked-email");
            mockedMasks.when(() -> PresenterMasks.maskValue(anyString())).thenReturn("masked-address");

            ClientePresenter.toDTO(cliente);

            mockedMasks.verify(() -> PresenterMasks.maskValue("Rua das Flores, 789"));
        }
    }
}