package br.com.midas.dao;

import br.com.midas.exception.DBException;
import br.com.midas.model.Usuario;

public interface UsuarioDao {

    void cadastrarUsuario(Usuario usuario) throws DBException;
    void atualizarUsuario(Usuario usuario) throws DBException;
    Usuario buscarPorId(int id) throws DBException;
    void deletarUsuario(int id) throws DBException;
    boolean validarUsuario(Usuario usuario);
    int getCodigoUsuarioByEmail(String email);
}