package br.com.midas.controller;

import br.com.midas.dao.DashboardDao;
import br.com.midas.exception.DBException;
import br.com.midas.factory.DaoFactory;
import br.com.midas.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(DashboardServlet.class.getName());
    private DashboardDao dashboardDao;

    @Override
    public void init() throws ServletException {
        dashboardDao = DaoFactory.getDashboardDAO();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Usuario usuarioVerificado = (Usuario) session.getAttribute("usuarioVerificado");

        if (usuarioVerificado == null) {
            logger.log(Level.WARNING, "Tentativa de acesso ao dashboard sem um usuário autenticado.");
            resp.sendRedirect(req.getContextPath() + "/resources/pages/login.jsp");
            return;
        }

        // Logando todas as informações do usuário ao acessar o dashboard
        logger.log(Level.INFO, "Acesso ao Dashboard - Informações do usuário:");
        logger.log(Level.INFO, "Código do usuário: {0}", usuarioVerificado.getCodigoUsuario());
        logger.log(Level.INFO, "Nome completo: {0}", usuarioVerificado.getNomeCompleto());
        logger.log(Level.INFO, "Data de nascimento: {0}", usuarioVerificado.getDataNascimento());
        logger.log(Level.INFO, "Gênero: {0}", usuarioVerificado.getGenero());
        logger.log(Level.INFO, "Email: {0}", usuarioVerificado.getEmail());

        try {
            // Carregando dados específicos do dashboard para o usuário autenticado
            String nomeUsuario = dashboardDao.getNomeUsuario(usuarioVerificado.getCodigoUsuario());
            double totalGanhos = dashboardDao.getTotalGanhos(usuarioVerificado.getCodigoUsuario());
            double totalGastos = dashboardDao.getTotalGastos(usuarioVerificado.getCodigoUsuario());

            // Log de dados do dashboard
            logger.log(Level.INFO, "TOTAL GANHO = {0}", totalGanhos);
            logger.log(Level.INFO, "TOTAL GASTO = {0}", totalGastos);
            logger.log(Level.INFO, "NOME USUARIO = {0}", nomeUsuario);

            // Definindo os atributos para exibir no JSP do dashboard
            req.setAttribute("nomeUsuario", nomeUsuario);
            req.setAttribute("totalGanhos", totalGanhos);
            req.setAttribute("totalGastos", totalGastos);

        } catch (DBException e) {
            logger.log(Level.SEVERE, "Erro ao carregar os dados do dashboard para o usuário: " + usuarioVerificado.getCodigoUsuario(), e);
            req.setAttribute("erro", "Erro ao carregar os dados do dashboard");
        }

        req.getRequestDispatcher("/resources/pages/dashboard.jsp").forward(req, resp);
    }
}