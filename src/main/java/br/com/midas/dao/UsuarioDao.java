package br.com.midas.dao;

import br.com.midas.exception.DBException;
import br.com.midas.model.Usuario;

public interface UsuarioDao {

    void cadastrar(Usuario usuario) throws DBException;
    void atualizar(Usuario usuario) throws DBException;
    Usuario buscarPorId(Long id) throws DBException;
    void deletar(Long id) throws DBException;
    boolean validarUsuario(Usuario usuario);
    int getCodigoUsuarioByEmail(String email) ;
}
