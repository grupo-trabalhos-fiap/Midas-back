package br.com.midas.controller;

import br.com.midas.bo.EmailBo;
import br.com.midas.dao.UsuarioDao;
import br.com.midas.exception.EmailException;
import br.com.midas.factory.DaoFactory;
import br.com.midas.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private UsuarioDao usuarioDao;
    private EmailBo bo;

    public LoginServlet() {
        usuarioDao = DaoFactory.getUsuarioDAO();
        bo = new EmailBo();
    }

    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        Usuario usuario = new Usuario(email, senha);

        if (usuarioDao.validarUsuario(usuario)) {
            int codigoUsuario = usuarioDao.getCodigoUsuarioByEmail(usuario.getEmail()); // Obter o código do usuário

            HttpSession session = request.getSession();
            // session.setAttribute("user", email); // Remova esta linha
            session.setAttribute("codigoUsuario", codigoUsuario); // Armazena o código
            String mensagem =
                    "Um login foi realizado na plataforma em " + LocalDate.now();
            request.getRequestDispatcher("/resources/pages/dashboard.jsp").forward(request, response);
            try {
                bo.enviarEmail(email, "Login Realizado", mensagem);
            } catch (EmailException e) {
                e.printStackTrace();
            }

        } else {
            request.setAttribute("erro", "Usuário e/ou senha inválidos");
            request.getRequestDispatcher("/resources/pages/login.jsp").forward(request, response);
        }
    }

    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("/resources/pages/dashboard.jsp").forward(request, response);

    }
}