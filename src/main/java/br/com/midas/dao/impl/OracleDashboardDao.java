package br.com.midas.dao.impl;

import br.com.midas.dao.DashboardDao;
import br.com.midas.exception.DBException;
import br.com.midas.factory.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OracleDashboardDao implements DashboardDao {

    private static final Logger logger = Logger.getLogger(OracleDashboardDao.class.getName());
    private Connection conexao;

    @Override
    public double getTotalGanhos(int codigoUsuario) throws DBException {
        return executarConsultaSoma("SELECT SUM(vl_ganho) AS total FROM T_MSF_GANHOS WHERE cd_usuario = ?", codigoUsuario);
    }

    @Override
    public double getTotalGastos(int codigoUsuario) throws DBException {
        return executarConsultaSoma("SELECT SUM(vl_gasto) AS total FROM T_MSF_GASTOS WHERE cd_usuario = ?", codigoUsuario);
    }

//    @Override
//    public double getTotalInvestimentos(int codigoUsuario) throws DBException {
//        return executarConsultaSoma("SELECT SUM(vl_investimento) AS total FROM T_MSF_INVESTIMENTOS WHERE cd_usuario = ?", codigoUsuario);
//    }

    @Override
    public String getNomeUsuario(int codigoUsuario) throws DBException {
        String sql = "SELECT NM_USUARIO FROM T_MSF_USUARIO WHERE CD_USUARIO = ?";
        PreparedStatement stmt = null;
        ResultSet resultado = null;
        String nomeUsuario = null;

        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoUsuario);
            resultado = stmt.executeQuery();

            if (resultado.next()) {
                nomeUsuario = resultado.getString("nome");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao buscar o nome do usuário para o código do usuário: " + codigoUsuario, e);
            throw new DBException("Erro ao buscar o nome do usuário.");
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Erro ao fechar recursos da conexão", e);
            }
        }

        return nomeUsuario;
    }

    private double executarConsultaSoma(String sql, int codigoUsuario) throws DBException {
        PreparedStatement stmt = null;
        ResultSet resultado = null;
        double total = 0;

        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoUsuario);
            resultado = stmt.executeQuery();

            if (resultado.next()) {
                total = resultado.getDouble("total");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Erro ao executar a consulta de soma para o código do usuário: " + codigoUsuario, e);
            throw new DBException("Erro ao executar a consulta de soma.");
        } finally {
            try {
                if (resultado != null) resultado.close();
                if (stmt != null) stmt.close();
                if (conexao != null) conexao.close();
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Erro ao fechar recursos da conexão", e);
            }
        }

        return total;
    }
}
