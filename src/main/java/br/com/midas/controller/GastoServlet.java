package br.com.midas.controller;

import br.com.midas.dao.GastoDao;
import br.com.midas.exception.DBException;
import br.com.midas.factory.DaoFactory;
import br.com.midas.model.Gasto;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/gastos")
public class GastoServlet extends HttpServlet {

    private GastoDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = DaoFactory.getGastoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String acaoGasto = req.getParameter("acaoGasto");

        HttpSession session = req.getSession();
        int codigoUsuario = (Integer) session.getAttribute("codigoUsuario");

        switch (acaoGasto) {
            case "cadastrar":
                cadastrarGasto(req, resp, codigoUsuario);
                break;
            case "editarr":
                editarGasto(req, resp);
                break;
            case "excluir":
                excluirGasto(req, resp);
                break;
        }
    }

    private void cadastrarGasto(HttpServletRequest req, HttpServletResponse resp, int codigoUsuario)
            throws ServletException, IOException {
        try {
            double valorGasto = Double.parseDouble(req.getParameter("valorGasto"));
            LocalDate dataGasto = LocalDate.parse(req.getParameter("dataGasto"));
            String categoria = req.getParameter("categoria");
            String descricaoGasto = req.getParameter("descricaoGasto");

            Gasto gasto = new Gasto(
                    0,
                    codigoUsuario,
                    valorGasto,
                    dataGasto,
                    categoria,
                    descricaoGasto
            );

            dao.cadastrarGasto(gasto);

            req.setAttribute("mensagem", "Gasto cadastrado!");
            List<Gasto> gastos = dao.getAllGasto(codigoUsuario);
            req.setAttribute("gastos", gastos);

        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados");
        }
        req.getRequestDispatcher("/resources/pages/Gastos.jsp").forward(req, resp);
    }

    private void editarGasto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigoGasto = Integer.parseInt(req.getParameter("codigoGasto"));
            double valorGasto = Double.parseDouble(req.getParameter("valorGasto"));
            LocalDate dataGasto = LocalDate.parse(req.getParameter("dataGasto"));
            String categoria = req.getParameter("categoria");
            String descricaoGasto = req.getParameter("descricaoGasto");

            Gasto gasto = new Gasto(
                    codigoGasto,
                    valorGasto,
                    dataGasto,
                    categoria,
                    descricaoGasto
            );

            dao.atualizarGasto(gasto);

            req.setAttribute("mensagem", "Gasto atualizado!");
        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados");
        }
        listarGasto(req, resp);
    }

    private void excluirGasto(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int codigoGasto = Integer.parseInt(req.getParameter("codigoExcluir"));
        try {
            dao.removerGasto(codigoGasto);
            req.setAttribute("mensagem", "Gasto removido!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");
        }
        listarGasto(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        listarGasto(req, resp);
    }

    private void listarGasto(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Obter o código do usuário da sessão
        HttpSession session = req.getSession();
        Integer codigoUsuario = (Integer) session.getAttribute("codigoUsuario");

        // Recuperar e exibir os ganhos do usuário logado
        List<Gasto> gastos = dao.getAllGasto(codigoUsuario);
        req.setAttribute("gastos", gastos);
        req.getRequestDispatcher("/resources/pages/Gastos.jsp").forward(req, resp);
    }

}