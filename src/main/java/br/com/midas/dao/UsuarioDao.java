package br.com.midas.dao;

import br.com.midas.model.Usuario;
import java.sql.SQLException;

public interface UsuarioDao {

    void cadastrarUsuario(Usuario usuario) throws SQLException;
    void atualizarUsuario(Usuario usuario) throws SQLException;
    Usuario buscarPorId(int id) throws SQLException;
    void deletar(int id) throws SQLException;
    boolean validarUsuario(Usuario usuario);

    int getCodigoUsuarioByEmail(String email);
}