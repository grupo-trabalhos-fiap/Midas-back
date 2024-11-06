package br.com.midas.factory;

import br.com.midas.dao.GanhoDao;
import br.com.midas.dao.ObjetivoDao;
import br.com.midas.dao.UsuarioDao;
import br.com.midas.dao.impl.OracleGanhoDao;
import br.com.midas.dao.impl.OracleObjetivoDao;
import br.com.midas.dao.impl.OracleUsuarioDao;

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
}