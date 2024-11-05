package br.com.midas.dao;

import br.com.midas.model.Usuario;

public interface UsuarioDao {

    boolean validarUsuario(Usuario usuario);

    int getCodigoUsuarioByEmail(String email);
}