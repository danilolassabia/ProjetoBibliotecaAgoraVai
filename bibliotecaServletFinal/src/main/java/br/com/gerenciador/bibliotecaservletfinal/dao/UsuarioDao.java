package br.com.gerenciador.bibliotecaservletfinal.dao;

import br.com.gerenciador.bibliotecaservletfinal.model.Usuario;
import br.com.gerenciador.bibliotecaservletfinal.util.InSession;

import java.util.List;

import static br.com.gerenciador.bibliotecaservletfinal.util.InSession.inSession;

public class UsuarioDao {
    List<Usuario> usuarios;
    public void cadastrar(Usuario usuario){
        inSession(entityManager -> {
            entityManager.persist(usuario);
        });
    }

    public void atualizar(Usuario usuario){
        inSession(entityManager -> {
            entityManager.merge(usuario);
        });
    }

    public void remover(Long id){
        inSession(entityManager -> {
            Usuario usuario = entityManager.find(Usuario.class,id);
            if (usuario != null) {
                entityManager.remove(usuario);
            }
        });
    }

    public List<Usuario> listarUsuarios() {
        InSession.inSession(entityManager -> {
            usuarios = entityManager.createQuery("select u from Usuario u", Usuario.class).getResultList();
        });
        return usuarios;
    }
}
