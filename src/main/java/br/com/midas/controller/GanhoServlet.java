package br.com.midas.controller;

import br.com.midas.dao.GanhoDao;
import br.com.midas.exception.DBException;
import br.com.midas.factory.DaoFactory;
import br.com.midas.model.Ganho;
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

@WebServlet("/ganhos")
public class GanhoServlet extends HttpServlet {

    private GanhoDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        dao = DaoFactory.getGanhoDAO();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String acao = req.getParameter("acao");

        HttpSession session = req.getSession();
        int codigoUsuario = (Integer) session.getAttribute("codigoUsuario");

        switch (acao) {
            case "cadastrar":
                cadastrar(req, resp, codigoUsuario);
                break;
            case "editar":
                editar(req, resp);
                break;
            case "excluir":
                excluir(req, resp);
                break;
        }
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp, int codigoUsuario)
            throws ServletException, IOException {
        try {
            double valorGanho = Double.parseDouble(req.getParameter("valorGanho"));
            LocalDate dataGanho = LocalDate.parse(req.getParameter("dataGanho"));
            String descricaoGanho = req.getParameter("descricaoGanho");

            Ganho ganho = new Ganho(
                    0,
                    codigoUsuario,
                    valorGanho,
                    dataGanho,
                    descricaoGanho
            );

            dao.cadastrar(ganho);

            req.setAttribute("mensagem", "Objetivo cadastrado!");
            List<Ganho> ganhos = dao.getAll();
            req.setAttribute("ganhos", ganhos);

        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados");
        }
        req.getRequestDispatcher("/resources/pages/Ganhos.jsp").forward(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigoGanho = Integer.parseInt(req.getParameter("codigoGanho"));
            double valorGanho = Double.parseDouble(req.getParameter("valorGanho"));
            LocalDate dataGanho = LocalDate.parse(req.getParameter("dataGanho"));
            String descricaoGanho = req.getParameter("descricaoGanho");

            Ganho ganho = new Ganho(
                    codigoGanho,
                    valorGanho,
                    dataGanho,
                    descricaoGanho
            );

            dao.atualizar(ganho);

            req.setAttribute("mensagem", "Ganho atualizado!");
        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados");
        }
        listar(req, resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int codigoGanho = Integer.parseInt(req.getParameter("codigoExcluir"));
        try {
            dao.remover(codigoGanho);
            req.setAttribute("mensagem", "Ganho removido!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");
        }
        listar(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        listar(req, resp);
    }

    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
            List<Ganho> ganhos = dao.getAll();
            req.setAttribute("ganhos", ganhos);
            req.getRequestDispatcher("/resources/pages/Ganhos.jsp").forward(req, resp);
    }
}