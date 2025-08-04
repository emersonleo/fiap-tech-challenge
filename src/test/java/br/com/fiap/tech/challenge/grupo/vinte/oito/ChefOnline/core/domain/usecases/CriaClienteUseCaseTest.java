package br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.usecases;

//import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Cliente;
//import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.domain.entities.usuario.Proprietario;
//import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IClienteDataSource;
//import br.com.fiap.tech.challenge.grupo.vinte.oito.ChefOnline.core.interfaces.usuario.IProprietarioDataSource;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.MockitoAnnotations;
//
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//import static org.junit.jupiter.api.Assertions.*;

class CriaClienteUseCaseTest {

//    @Mock
//    private IClienteDataSource clienteDataSource;
//
//    @Mock
//    private IProprietarioDataSource proprietarioDataSource;
//
//    private UsuarioService usuarioService;
//
//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//        usuarioService = new UsuarioService(clienteDataSource, proprietarioDataSource);
//    }
//
//    @Test
//    @DisplayName("Deve cadastrar cliente com sucesso")
//    void deveCadastrarClienteComSucesso() {
//        // arrange
//        Cliente cliente = new Cliente();
//        cliente.setNome("João Silva");
//        cliente.setEmail("joao@email.com");
//        cliente.setCpf("12345678900");
//        when(clienteDataSource.save(any())).thenReturn(cliente);
//
//        // act
//        Cliente resultado = usuarioService.cadastrarCliente(cliente);
//
//        // assert
//        assertNotNull(resultado);
//        assertEquals("João Silva", resultado.getNome());
//        verify(clienteDataSource).save(cliente);
//    }
//
//    @Test
//    @DisplayName("Deve cadastrar proprietário com sucesso")
//    void deveCadastrarProprietarioComSucesso() {
//        // arrange
//        Proprietario proprietario = new Proprietario();
//        proprietario.setNome("Maria Santos");
//        proprietario.setEmail("maria@email.com");
//        proprietario.setCnpj("12345678000100");
//        when(proprietarioDataSource.save(any())).thenReturn(proprietario);
//
//        // act
//        Proprietario resultado = usuarioService.cadastrarProprietario(proprietario);
//
//        // assert
//        assertNotNull(resultado);
//        assertEquals("Maria Santos", resultado.getNome());
//        verify(proprietarioDataSource).save(proprietario);
//    }
//
//    @Test
//    @DisplayName("Deve autenticar cliente com sucesso")
//    void deveAutenticarClienteComSucesso() {
//        // arrange
//        String email = "joao@email.com";
//        String senha = "senha123";
//        Cliente cliente = new Cliente();
//        when(clienteDataSource.findByEmailAndSenha(email, senha)).thenReturn(Optional.of(cliente));
//
//        // act
//        Optional<Cliente> resultado = usuarioService.autenticarCliente(email, senha);
//
//        // assert
//        assertTrue(resultado.isPresent());
//        verify(clienteDataSource).findByEmailAndSenha(email, senha);
//    }
}
