package br.com.midas.dao.impl;

import br.com.midas.dao.UsuarioDao;
import br.com.midas.factory.ConnectionFactory;
import br.com.midas.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OracleUsuarioDao implements UsuarioDao {

    private Connection conexao;

    @Override
    public boolean validarUsuario(Usuario usuario) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionFactory
                    .getInstance()
                    .getConnection();

            String sql = "SELECT * FROM T_MSF_USUARIO " +
                    "WHERE EMAIL = ? AND SENHA = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            rs = stmt.executeQuery();

            if (rs.next()) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public int getCodigoUsuarioByEmail(String email) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            String sql = "SELECT cd_usuario FROM T_MSF_USUARIO WHERE EMAIL = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, email);
            rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("cd_usuario");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
                rs.close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1; // Retorna -1 se o usuário não for encontrado
    }
}