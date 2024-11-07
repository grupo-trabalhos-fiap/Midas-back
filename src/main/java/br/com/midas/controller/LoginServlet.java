package br.com.midas.controller;

import br.com.midas.bo.EmailBo;
import br.com.midas.dao.UsuarioDao;
import br.com.midas.exception.DBException;
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
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(LoginServlet.class.getName());
    private UsuarioDao usuarioDao;
    private EmailBo emailBo;

    public LoginServlet() {
        this.usuarioDao = DaoFactory.getUsuarioDAO();
        this.emailBo = new EmailBo();
    }

    @Override
    protected void doPost(
            HttpServletRequest request,
            HttpServletResponse response
    )
            throws ServletException, IOException
    {

        String email = request.getParameter("email");
        String senha = request.getParameter("senha");

        try {
            if (validarUsuario(email, senha)) {
                int codigoUsuario = usuarioDao.getCodigoUsuarioByEmail(email);
                iniciarSessaoUsuario(request, codigoUsuario);

                response.sendRedirect(request.getContextPath() + "/resources/pages/dashboard.jsp");

                enviarNotificacaoLogin(email);

            } else {
                exibirMensagemErro(request, response, "Usuário e/ou senha inválidos");
            }
        } catch (DBException e) {
            logger.log(Level.SEVERE, "Erro ao validar o usuário", e);
            exibirMensagemErro(request, response, "Erro ao processar o login. Tente novamente.");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession();
        session.invalidate();  // Invalida a sessão para efetuar o logout
        response.sendRedirect(request.getContextPath() + "/resources/pages/login.jsp");  // Redireciona para a página de login
    }

    private boolean validarUsuario(String email, String senha) throws DBException {
        Usuario usuario = new Usuario(email, senha);
        return usuarioDao.validarUsuario(usuario);
    }

    private void iniciarSessaoUsuario(HttpServletRequest request, int codigoUsuario) {
        HttpSession session = request.getSession();
        session.setAttribute("codigoUsuario", codigoUsuario);
    }

    private void enviarNotificacaoLogin(String email) {
        String mensagem = "Um login foi realizado na plataforma em " + LocalDate.now();
        try {
            emailBo.enviarEmail(email, "Login Realizado", mensagem);
        } catch (EmailException e) {
            logger.log(Level.WARNING, "Falha ao enviar e-mail de notificação de login", e);
        }
    }

    private void exibirMensagemErro(HttpServletRequest request, HttpServletResponse response, String mensagem)
            throws ServletException, IOException {
        request.setAttribute("erro", mensagem);
        request.getRequestDispatcher("/resources/pages/login.jsp").forward(request, response);
    }
}