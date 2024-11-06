package br.com.midas.controller;

import br.com.midas.dao.UsuarioDao;
import br.com.midas.factory.DaoFactory;
import br.com.midas.model.Usuario;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet("/usuarios")
public class UsuarioServlet extends HttpServlet {

    private UsuarioDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        try {
            dao = DaoFactory.getUsuarioDAO();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String acao = req.getParameter("acao");

        switch (acao) {
            case "cadastrar":
                cadastrar(req, resp);
                break;
            case "editar":
                editar(req, resp);
                break;
            case "excluir":
                excluir(req, resp);
                break;
            case "validar":
                validar(req, resp);
                break;
            default:
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação inválida.");
        }
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long codigoUsuario = Long.parseLong(req.getParameter("codigoUsuario"));
            String nomeCompleto = req.getParameter("nomeCompleto");
            LocalDate dataNascimento = LocalDate.parse(req.getParameter("dataNascimento"));
            char genero = req.getParameter("genero").charAt(0);
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");

            Usuario usuario = new Usuario(codigoUsuario, nomeCompleto, dataNascimento, genero, email, senha);
            dao.cadastrar(usuario);

            req.setAttribute("mensagem", "Usuário cadastrado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar usuário. Por favor, valide os dados.");
        }
        listar(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long codigoUsuario = Long.parseLong(req.getParameter("codigoUsuario"));
            String nomeCompleto = req.getParameter("nomeCompleto");
            LocalDate dataNascimento = LocalDate.parse(req.getParameter("dataNascimento"));
            char genero = req.getParameter("genero").charAt(0);
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");

            Usuario usuario = new Usuario(codigoUsuario, nomeCompleto, dataNascimento, genero, email, senha);
            dao.atualizar(usuario);

            req.setAttribute("mensagem", "Usuário atualizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar usuário. Por favor, valide os dados.");
        }
        listar(req, resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Long codigoUsuario = Long.parseLong(req.getParameter("codigoExcluir"));
            dao.deletar(codigoUsuario);

            req.setAttribute("mensagem", "Usuário excluído com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao excluir usuário.");
        }
        listar(req, resp);
    }

    private void validar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");

            Usuario usuario = new Usuario(email, senha);
            boolean valido = dao.validarUsuario(usuario);

            if (valido) {
                req.setAttribute("mensagem", "Usuário válido.");
            } else {
                req.setAttribute("erro", "Usuário inválido.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao validar usuário.");
        }
        listar(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        listar(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Usuarios.jsp").forward(req, resp);
    }
}
