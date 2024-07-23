package br.com.gerenciador.bibliotecaservletfinal.teste;

import br.com.gerenciador.bibliotecaservletfinal.dao.LivroDao;
import br.com.gerenciador.bibliotecaservletfinal.model.Livro;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLivroDao {
    private LivroDao livroDao;

    @BeforeEach
    public void setUp() {
        livroDao = new LivroDao();
    }

    @AfterEach
    public void cleanUpEach() {
        livroDao.remover("12345");
        livroDao.remover("67890");
    }

    @Test
    public void testCadastrar() {
        Livro livro = new Livro("12345", "Test Book", "Test Author", 10);
        livroDao.cadastrar(livro);

        List<Livro> livros = livroDao.listarLivros();
        assertEquals(1, livros.size());
        assertEquals("Test Book", livros.get(0).getTitulo());
    }

    @Test
    public void testAtualizar() {
        Livro livro = new Livro("12345", "Test Book", "Test Author", 10);
        livroDao.cadastrar(livro);

        livro.setTitulo("Updated Book");
        livroDao.atualizar(livro);

        List<Livro> livros = livroDao.listarLivros();
        assertEquals(1, livros.size());
        assertEquals("Updated Book", livros.get(0).getTitulo());
    }

    @Test
    public void testRemover() {
        Livro livro = new Livro("12345", "Test Book", "Test Author", 10);
        livroDao.cadastrar(livro);

        livroDao.remover("12345");

        List<Livro> livros = livroDao.listarLivros();
        assertTrue(livros.isEmpty());
    }

    @Test
    public void testListarLivros() {
        Livro livro1 = new Livro("12345", "Test Book 1", "Test Author 1", 10);
        Livro livro2 = new Livro("67890", "Test Book 2", "Test Author 2", 5);

        livroDao.cadastrar(livro1);
        livroDao.cadastrar(livro2);

        List<Livro> livros = livroDao.listarLivros();
        assertEquals(2, livros.size());
    }

}
