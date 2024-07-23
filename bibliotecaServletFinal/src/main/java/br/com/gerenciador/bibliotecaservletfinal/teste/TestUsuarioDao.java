package br.com.gerenciador.bibliotecaservletfinal.teste;

import br.com.gerenciador.bibliotecaservletfinal.dao.UsuarioDao;
import br.com.gerenciador.bibliotecaservletfinal.model.Usuario;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUsuarioDao {
    private UsuarioDao usuarioDao;

    @BeforeEach
    public void setUp() {
        usuarioDao = new UsuarioDao();
    }

    @Test
    public void testCadastrar() {
        Usuario usuario = new Usuario("Test User", "Test@User", "1234");
        usuarioDao.cadastrar(usuario);
        List<Usuario> usuarios = usuarioDao.listarUsuarios();
        assertEquals(1, usuarios.size());
        assertEquals("Test User", usuarios.get(0).getNome());
    }

    @Test
    public void testAtualizar() {
        Usuario usuario = new Usuario("Test User", "Test@User", "1234");
        usuarioDao.cadastrar(usuario);

        usuario.setNome("Updated User");
        usuarioDao.atualizar(usuario);

        List<Usuario> usuarios = usuarioDao.listarUsuarios();
        assertEquals(1, usuarios.size());
        assertEquals("Updated User", usuarios.get(0).getNome());
    }

    @Test
    public void testRemover() {
        Usuario usuario = new Usuario("Test User", "Test@User", "1234");
        usuarioDao.cadastrar(usuario);
        usuarioDao.remover(usuario.getId());
        List<Usuario> usuarios = usuarioDao.listarUsuarios();
        assertTrue(usuarios.isEmpty());
    }

    @Test
    public void testListarUsuarios() {
        Usuario usuario = new Usuario("Test User", "Test@User", "1234");
        Usuario usuario2 = new Usuario("Test User2", "Test@User2", "5678");

        usuarioDao.cadastrar(usuario);
        usuarioDao.cadastrar(usuario2);

        List<Usuario> usuarios = usuarioDao.listarUsuarios();
        assertEquals(2, usuarios.size());
    }
}
