package br.com.midas.dao;

import br.com.midas.factory.ConnectionFactory;
import br.com.midas.model.Calendario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CalendarioDao {

    private Connection conexao;

    public CalendarioDao() throws SQLException {
        conexao = ConnectionFactory
                .getInstance()
                .getConnection();
    }

    public void cadastrar(Calendario calendario) throws SQLException {
        String sql = "INSERT INTO T_MSF_CALENDARIO " +
                "(cd_calendario, cd_usuario, cd_comparacao, indicador_dia) " +
                "VALUES (SEQ_MSF_CALENDARIO.nextval, 1, 1, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, calendario.getIndicadorDia());
            stmt.executeUpdate();
        }
    }

    public List<Calendario> getAll() throws SQLException {
        List<Calendario> calendarios = new ArrayList<>();

        String sql = "SELECT * FROM T_MSF_CALENDARIO";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Long codigoCalendario = resultado.getLong("cd_calendario");
                String indicadorDia = resultado.getString("indicador_dia");

                Calendario calendario = new Calendario(codigoCalendario, indicadorDia);
                calendarios.add(calendario);
            }
        }

        return calendarios;
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}