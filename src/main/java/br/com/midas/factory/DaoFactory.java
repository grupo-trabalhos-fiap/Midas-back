package br.com.midas.factory;

import br.com.midas.dao.ObjetivoDao;
import br.com.midas.dao.UsuarioDao;
import br.com.midas.dao.impl.OracleObjetivoDao;
import br.com.midas.dao.impl.OracleUsuarioDao;


public class DaoFactory {
    public static UsuarioDao getUsuarioDAO() {
        return new OracleUsuarioDao();
    }
    public static ObjetivoDao getObjetivoDAO() {
        return new OracleObjetivoDao();
    }
}