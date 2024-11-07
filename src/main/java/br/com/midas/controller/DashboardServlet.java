package br.com.midas.controller;

import br.com.midas.dao.DashboardDao;
import br.com.midas.exception.DBException;
import br.com.midas.factory.DaoFactory;
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        Integer codigoUsuario = (Integer) session.getAttribute("codigoUsuario");

        if (codigoUsuario == null) {
            logger.log(Level.WARNING, "Tentativa de acesso ao dashboard sem um usuário autenticado.");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
            return;
        }

        try {
            String nomeUsuario = dashboardDao.getNomeUsuario(codigoUsuario);
            double totalGanhos = dashboardDao.getTotalGanhos(codigoUsuario);
            double totalGastos = dashboardDao.getTotalGastos(codigoUsuario);
//            double totalInvestimentos = dashboardDao.getTotalInvestimentos(codigoUsuario);

            req.setAttribute("nomeUsuario", nomeUsuario);
            req.setAttribute("totalGanhos", totalGanhos);
            req.setAttribute("totalGastos", totalGastos);
//            req.setAttribute("totalInvestimentos", totalInvestimentos);

        } catch (DBException e) {
            logger.log(Level.SEVERE, "Erro ao carregar os dados do dashboard para o usuário: " + codigoUsuario, e);
            req.setAttribute("erro", "Erro ao carregar os dados do dashboard");
        }

        req.getRequestDispatcher("/resources/pages/Dashboard.jsp").forward(req, resp);
    }
}
