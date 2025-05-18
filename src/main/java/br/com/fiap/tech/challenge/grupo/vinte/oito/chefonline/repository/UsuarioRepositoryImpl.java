package br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.repository;

import br.com.fiap.tech.challenge.grupo.vinte.oito.chefonline.entity.Usuario;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepository {

    private final JdbcClient jdbcClient;

    public UsuarioRepositoryImpl(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Integer save(Usuario usuario) {
        return this.jdbcClient.sql("INSERT INTO usuarios (nome, email, login, senha, endereco, data_ultima_alteracao_registro, data_criacao_registro) VALUES (:nome, :email, :login, :senha, :endereco, :dataUltimaAlteracaoRegistro, :dataCriacaoRegistro)")
                .param("nome", usuario.getNome())
                .param("email", usuario.getEmail())
                .param("login", usuario.getLogin())
                .param("senha", usuario.getSenha())
                .param("endereco", usuario.getEndereco())
                .param("dataUltimaAlteracaoRegistro", null)
                .param("dataCriacaoRegistro", LocalDate.now())
                .update();
    }

    @Override
    public List<Usuario> findAllUsers(int size, int offset) {
        return this.jdbcClient.sql("SELECT * FROM usuarios LIMIT :size OFFSET :offset")
                .param("size", size)
                .param("offset", offset)
                .query(Usuario.class)
                .list();
    }
}
