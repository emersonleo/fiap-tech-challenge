package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.core.ports;

public interface AuthenticationServicePort {
    void authenticate(String login, String senha);
    String getCurrentUserLogin();
    void validateCurrentPassword(String login, String senha);
    void updatePassword(String login, String novaSenha);
}
