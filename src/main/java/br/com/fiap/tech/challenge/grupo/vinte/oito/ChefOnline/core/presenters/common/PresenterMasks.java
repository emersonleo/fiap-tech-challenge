package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.presenters.common;

public class PresenterMasks {
    public static String maskValue(String value) {
        if (value.isEmpty()) {
            return value;
        }

        int unmaskedCharCount = value.length() / 3;

        return String.format(
                "%s...%s",
                value.substring(0, unmaskedCharCount / 2),
                value.substring(value.length() - (unmaskedCharCount / 2))
        );
    }

    public static String maskEmail(String email) {
        if (!email.contains("@")) return maskValue(email);

        int at = email.indexOf("@");
        String maskedEmailUser = maskValue(email.substring(0, at));
        String remaining = email.substring(at);
        return maskedEmailUser + remaining;
    }
}
