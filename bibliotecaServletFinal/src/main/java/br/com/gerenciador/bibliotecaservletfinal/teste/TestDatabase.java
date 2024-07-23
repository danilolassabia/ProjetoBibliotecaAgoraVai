package br.com.gerenciador.bibliotecaservletfinal.teste;

import br.com.gerenciador.bibliotecaservletfinal.model.Livro;
import br.com.gerenciador.bibliotecaservletfinal.model.Usuario;

import static br.com.gerenciador.bibliotecaservletfinal.util.InSession.inSession;

public class TestDatabase {
    public static void main(String[] args) {

        Livro livro = new Livro("5673488", "sdfgh sdfgdfgseryhrtdfg", "hrtrfj ryrthr", 5);

        Usuario user = new Usuario("fsdfwfw","dfgdhkjul@gmail.com","56865457457989");


        inSession(entityManager -> {
            entityManager.persist(user);
            entityManager.persist(livro);
        });
    }

    // Teste simples para persistir um objeto e verificar se a tabela é criada



    // Teste simples para persistir um objeto e verificar se a tabela é criada

}