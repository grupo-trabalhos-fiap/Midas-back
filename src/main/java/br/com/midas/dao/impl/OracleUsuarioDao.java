package br.com.midas.dao.impl;

import br.com.midas.dao.UsuarioDao;
import br.com.midas.factory.ConnectionFactory;
import br.com.midas.model.Usuario;

import java.sql.*;

public class OracleUsuarioDao implements UsuarioDao {

    private Connection conexao;

    public OracleUsuarioDao() {
        conexao = ConnectionFactory
                .getInstance()
                .getConnection();
    }

    public void cadastrarUsuario(Usuario usuario) throws SQLException {
        String sql = "INSERT INTO T_MSF_USUARIO"
                + " (cd_usuario, nm_usuario, dt_nascimento, genero, email, senha)"
                + " VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, usuario.getCodigoUsuario());
            stmt.setString(2, usuario.getNomeCompleto());
            stmt.setDate(3, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(4, String.valueOf(usuario.getGenero()));
            stmt.setString(5, usuario.getEmail());
            stmt.setString(6, usuario.getSenha());
            stmt.executeUpdate();
        }
    }

    @Override
    public void atualizarUsuario(Usuario usuario) throws SQLException {
        String sql = "UPDATE T_MSF_USUARIO SET nm_usuario = ?, dt_nascimento = ?, genero = ?, email = ?, senha = ? WHERE cd_usuario = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, usuario.getNomeCompleto());
            stmt.setDate(2, Date.valueOf(usuario.getDataNascimento()));
            stmt.setString(3, String.valueOf(usuario.getGenero()));
            stmt.setString(4, usuario.getEmail());
            stmt.setString(5, usuario.getSenha());
            stmt.setLong(6, usuario.getCodigoUsuario());
            stmt.executeUpdate();
        }
    }

    public Usuario buscarPorId(int id) throws SQLException {
        String sql = "SELECT * FROM T_MSF_USUARIO WHERE cd_usuario = ?";
        Usuario usuario = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("cd_usuario"),
                        rs.getString("nm_usuario"),
                        rs.getDate("dt_nascimento").toLocalDate(),
                        rs.getString("genero").charAt(0),
                        rs.getString("email"),
                        rs.getString("senha")
                );
            }
        }
        return usuario;
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM T_MSF_USUARIO WHERE cd_usuario = ?";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setLong(1, id);
            stmt.executeUpdate();
        }
    }

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
                assert stmt != null;
                stmt.close();
                assert rs != null;
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
