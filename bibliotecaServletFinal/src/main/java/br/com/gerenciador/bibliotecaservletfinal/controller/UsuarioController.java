package br.com.gerenciador.bibliotecaservletfinal.controller;

import br.com.gerenciador.bibliotecaservletfinal.dao.LivroDao;
import br.com.gerenciador.bibliotecaservletfinal.dao.UsuarioDao;
import br.com.gerenciador.bibliotecaservletfinal.model.Usuario;
import br.com.gerenciador.bibliotecaservletfinal.service.UserAuthenticator;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/login", "/novoUsuario", "/home", "/cadastrar", "/atualizar", "/excluir"})
public class UsuarioController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private LivroDao livroDao;
    private UsuarioDao usuarioDao;

    public void init() throws ServletException {
        livroDao = new LivroDao();
        usuarioDao = new UsuarioDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();
        UserAuthenticator authenticator = new UserAuthenticator();
        Usuario usuario;
        switch (action) {
            case "/login": {
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                usuario = new Usuario("", email, password);
                var authUsuario = authenticator.authenticate(usuario);
                if (authUsuario != null) {
                    request.getSession().setAttribute("usuario", authUsuario);
                    request.getRequestDispatcher("home.jsp").forward(request, response);
                } else {
                    request.getSession().setAttribute("ErrorMessage", "E-mail ou senha inválidos!");
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                }
            }
            case "/novoUsuario": {
                String nome = request.getParameter("nome");
                String email = request.getParameter("email");
                String password = request.getParameter("password");
                usuario = new Usuario(nome,email,password);
                Usuario alreadyInUse = usuarioDao.findUsuarioByEmail(usuario.getEmail());
                if(alreadyInUse == null){
                    usuarioDao.cadastrar(usuario);
                    request.getRequestDispatcher("index.jsp").forward(request, response);
                    response.sendRedirect("/login");
                }else{
                    request.setAttribute("ErrorMessage", "E-mail já em uso!");
                    request.getRequestDispatcher("newUser.jsp").forward(request, response);

                }
            }


        }
    }
}
