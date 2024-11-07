package br.com.midas.factory;

import br.com.midas.dao.UsuarioDao;
import br.com.midas.dao.GanhoDao;
import br.com.midas.dao.GastoDao;
import br.com.midas.dao.ObjetivoDao;
import br.com.midas.dao.InvestimentoDao;
import br.com.midas.dao.impl.*;

import java.sql.SQLException;


public class DaoFactory {
    public static UsuarioDao getUsuarioDAO() throws SQLException {
        return new OracleUsuarioDao();
    }
    public static ObjetivoDao getObjetivoDAO() {
        return new OracleObjetivoDao();
    }
    public static GanhoDao getGanhoDAO() {
        return new OracleGanhoDao();
    }
    public static GastoDao getGastoDAO() {
        return new OracleGastoDao();
    }
    public static InvestimentoDao getInvestimentoDAO() {
        return new OracleInvestimentoDao();
    }
}