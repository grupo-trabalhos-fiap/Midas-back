package br.com.midas.dao;
import br.com.midas.exception.DBException;
import br.com.midas.model.Ganho;

import java.util.List;

public interface GanhoDao {
    void cadastrar(Ganho ganho) throws DBException;
    void atualizar(Ganho ganho) throws DBException;
    void remover(int codigoGanho) throws DBException;
    List<Ganho> getAll();
}
