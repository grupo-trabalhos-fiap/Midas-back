package br.com.midas.controller;

import br.com.midas.dao.ObjetivoDao;
import br.com.midas.exception.DBException;
import br.com.midas.factory.DaoFactory;
import br.com.midas.model.Objetivo;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/objetivos")
public class ObjetivoServlet extends HttpServlet {

    private ObjetivoDao dao;

    @Override
    public void init(ServletConfig config) throws ServletException {

        super.init(config);
        dao = DaoFactory.getObjetivoDAO();

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

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

        }
    }

    private void cadastrar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String nomeObjetivo = req
                    .getParameter("nomeObjetivo");
            double valorObjetivo = Integer
                    .parseInt(req.getParameter("valorObjetivo"));
            LocalDate dataObjetivo = LocalDate
                    .parse(req.getParameter("dataObjetivo"));
            String descricaoObjetivo = req
                    .getParameter("descricaoObjetivo");

            Objetivo objetivo = new Objetivo(
                    0,
                    nomeObjetivo,
                    valorObjetivo,
                    dataObjetivo,
                    descricaoObjetivo
            );

            dao.cadastrar(objetivo);

            req.setAttribute("mensagem", "Objetivo cadastrado!");

        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao cadastrar");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados");
        }
        req.getRequestDispatcher("Objetivos.jsp").forward(req, resp);
    }

    private void editar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int codigoObjetivo = Integer.parseInt(req.getParameter("codigo"));
            String nomeObjetivo = req.getParameter("nomeObjetivo");
            double valorObjetivo = Integer.parseInt(req.getParameter("valorObjetivo"));
            LocalDate dataObjetivo = LocalDate
                    .parse(req.getParameter("dataObjetivo"));
            String descricaoObjetivo = req.getParameter("descricaoObjetivo");

            Objetivo objetivo = new Objetivo(
                    codigoObjetivo, nomeObjetivo, valorObjetivo, dataObjetivo, descricaoObjetivo);

            dao.atualizar(objetivo);

            req.setAttribute("mensagem", "Objetivo atualizado!");
        } catch (DBException db) {
            db.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");
        } catch (Exception e) {
            e.printStackTrace();
            req.setAttribute("erro", "Por favor, valide os dados");
        }
        listar(req,resp);
    }

    private void excluir(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int codigoObjetivo = Integer.parseInt(req.getParameter("codigoExcluir"));
        try {
            dao.remover(codigoObjetivo);
            req.setAttribute("mensagem", "Produto removido!");
        } catch (DBException e) {
            e.printStackTrace();
            req.setAttribute("erro", "Erro ao atualizar");
        }
        listar(req,resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
                listar(req, resp);
        }


    private void listar(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("Objetivos.jsp").forward(req, resp);
    }
}
