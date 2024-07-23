package br.com.gerenciador.bibliotecaservletfinal.dao;

import br.com.gerenciador.bibliotecaservletfinal.model.Livro;
import br.com.gerenciador.bibliotecaservletfinal.util.InSession;

import java.util.List;

import static br.com.gerenciador.bibliotecaservletfinal.util.InSession.inSession;

public class LivroDao {

    List<Livro> livros;
    public void cadastrar(Livro livro){
        inSession(entityManager -> {
            entityManager.persist(livro);
        });
    }

    public void atualizar(Livro livro){
        inSession(entityManager -> {
            entityManager.merge(livro);
        });
    }

    public void remover(String isbn){
        inSession(entityManager -> {
            Livro livro = entityManager.find(Livro.class,isbn);
            if (livro != null) {
                entityManager.remove(livro);
            }
        });
    }

    public List<Livro> listarLivros() {
         InSession.inSession(entityManager -> {
            livros = entityManager.createQuery("select l from Livro l", Livro.class).getResultList();
        });
         return livros;
    }

}
