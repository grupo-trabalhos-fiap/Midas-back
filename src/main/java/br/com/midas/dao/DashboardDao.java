package br.com.midas.dao;

import br.com.midas.exception.DBException;

public interface DashboardDao {

    double getTotalGanhos(int codigoUsuario) throws DBException;

    double getTotalGastos(int codigoUsuario) throws DBException;

//    double getTotalInvestimentos(int codigoUsuario) throws DBException;

    String getNomeUsuario(int codigoUsuario) throws DBException;
}
