package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PresenterMasksTest {

    @Test
    void shouldMaskValueCorrectly() {
        String input = "123456789";
        String result = PresenterMasks.maskValue(input);
        assertEquals("1...9", result);
    }

    @Test
    void shouldMaskLongerValueCorrectly() {
        String input = "Rua das Flores, 123, Apt 456";
        String result = PresenterMasks.maskValue(input);
        assertEquals("Rua ... 456", result);
    }

    @Test
    void shouldMaskMediumValueCorrectly() {
        String input = "AbcDef";
        String result = PresenterMasks.maskValue(input);
        assertEquals("A...f", result);
    }

    @Test
    void shouldHandleShortValue() {
        String input = "ABC";
        String result = PresenterMasks.maskValue(input);
        assertEquals("...", result);
    }

    @Test
    void shouldHandleVeryShortValue() {
        String input = "AB";
        String result = PresenterMasks.maskValue(input);
        assertEquals("...", result);
    }

    @Test
    void shouldHandleSingleCharacterValue() {
        String input = "A";
        String result = PresenterMasks.maskValue(input);
        assertEquals("...", result);
    }

    @Test
    void shouldHandleEmptyValue() {
        String input = "";
        String result = PresenterMasks.maskValue(input);
        assertEquals("", result);
    }

    @Test
    void shouldMaskEmailCorrectly() {
        String input = "joao@email.com";
        String result = PresenterMasks.maskEmail(input);
        assertEquals("...@email.com", result);
    }

    @Test
    void shouldMaskComplexEmailCorrectly() {
        String input = "maria.silva@exemplo.com.br";
        String result = PresenterMasks.maskEmail(input);
        assertEquals("m...a@exemplo.com.br", result);
    }

    @Test
    void shouldMaskShortEmailCorrectly() {
        String input = "a@b.com";
        String result = PresenterMasks.maskEmail(input);
        assertEquals("...@b.com", result);
    }

    @Test
    void shouldMaskVeryLongEmailCorrectly() {
        String input = "very.long.email.address@example.domain.com";
        String result = PresenterMasks.maskEmail(input);
        assertEquals("ver...ess@example.domain.com", result);
    }

    @Test
    void shouldHandleEmailWithoutAtSymbol() {
        String input = "notanemail";
        String result = PresenterMasks.maskEmail(input);
        assertEquals("n...l", result);
    }

    @Test
    void shouldHandleEmptyEmail() {
        String input = "";
        String result = PresenterMasks.maskEmail(input);
        assertEquals("", result);
    }

    @Test
    void shouldHandleEmailWithMultipleAtSymbols() {
        String input = "user@@domain.com";
        String result = PresenterMasks.maskEmail(input);
        assertEquals("...@@domain.com", result);
    }

    @Test
    void shouldHandleEmailStartingWithAt() {
        String input = "@domain.com";
        String result = PresenterMasks.maskEmail(input);
        assertEquals("@domain.com", result);
    }

    @Test
    void shouldHandleEmailEndingWithAt() {
        String input = "user@";
        String result = PresenterMasks.maskEmail(input);
        assertEquals("...@", result);
    }
}