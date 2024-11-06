package br.com.midas.dao;

import br.com.midas.model.Usuario;
import java.sql.SQLException;

public interface UsuarioDao {

    void cadastrar(Usuario usuario) throws SQLException;
    void atualizar(Usuario usuario) throws SQLException;
    Usuario buscarPorId(Long id) throws SQLException;
    void deletar(Long id) throws SQLException;
    boolean validarUsuario(Usuario usuario);

    int getCodigoUsuarioByEmail(String email);
