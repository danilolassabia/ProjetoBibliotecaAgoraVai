package br.com.gerenciador.bibliotecaservletfinal.service;

import br.com.gerenciador.bibliotecaservletfinal.dao.UsuarioDao;
import br.com.gerenciador.bibliotecaservletfinal.model.Usuario;

import java.util.List;
import java.util.Objects;

public class UserAuthenticator {
    UsuarioDao usuarioDao;
    public Usuario authenticate(Usuario usuario) {
        Usuario foundUser = usuarioDao.findUsuarioByEmail(usuario.getEmail());
        if ( foundUser == null || !Objects.equals(usuario.getPassword(), foundUser.getPassword())){
            return null;
        }else{
            return foundUser;
        }
    }
}
