package br.com.midas.dao.impl;

import br.com.midas.dao.GanhoDao;
import br.com.midas.exception.DBException;
import br.com.midas.factory.ConnectionFactory;
import br.com.midas.model.Ganho;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OracleGanhoDao implements GanhoDao {

    private Connection conexao;

    @Override
    public void cadastrar(Ganho ganho) throws DBException {
        PreparedStatement stmt = null;

        String sql = "INSERT INTO T_MSF_GANHOS " +
                "(cd_usuario, vl_ganho, dt_ganho, ds_ganho) " +
                "VALUES (?, ?, ?, ?)";

        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, ganho.getCdUsuario());
            stmt.setDouble(2, ganho.getValorGanho());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataGanhoFormatada = ganho.getDataGanho().format(formatter);
            stmt.setString(3, dataGanhoFormatada);
            stmt.setString(4, ganho.getDescricaoGanho());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao cadastradar.");
        } finally {
            try {
                Objects.requireNonNull(stmt).close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Ganho> getAll(){
        PreparedStatement stmt = null;
        List<Ganho> ganhos = new ArrayList<>();
        ResultSet resultado;

        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            String sql = "SELECT * FROM T_MSF_GANHOS";
            stmt = conexao.prepareStatement(sql);
            resultado = stmt.executeQuery();

            while (resultado.next()) {
                int codigoGanho = resultado.getInt("cd_ganho");
                int codigoUsuario = resultado.getInt("cd_usuario"); // Adicione o código do usuário
                double valorGanho = resultado.getDouble("vl_ganho");
                // Convertendo String para LocalDate
                LocalDate dataGanho = LocalDate.parse(resultado.getString("dt_ganho"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String descricaoGanho = resultado.getString("ds_ganho");

                Ganho ganho = new Ganho(codigoGanho, codigoUsuario, valorGanho, dataGanho,
                        descricaoGanho);
                ganhos.add(ganho);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                Objects.requireNonNull(stmt).close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return ganhos;
    }

    @Override
    public void atualizar(Ganho ganho) throws DBException {
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionFactory.getInstance().getConnection();

            String sql = "UPDATE T_MSF_GANHOS SET " +
                    "vl_ganho = ?, " +
                    "dt_ganho = ?, " +
                    "ds_ganho = ? " +
                    "WHERE cd_ganho = ?";

            stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, ganho.getValorGanho());
            // Convertendo LocalDate para String no formato dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataGanhoFormatada = ganho.getDataGanho().format(formatter);
            stmt.setString(2, dataGanhoFormatada);
            stmt.setString(3, ganho.getDescricaoGanho());
            stmt.setInt(4, ganho.getCodigoGanho());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao atualizar.");
        } finally {
            try {
                Objects.requireNonNull(stmt).close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void remover(int codigoGanho) throws DBException {
        PreparedStatement stmt = null;

        try {
            conexao = ConnectionFactory.getInstance().getConnection();
            String sql = "DELETE FROM T_MSF_GANHOS WHERE cd_ganho = ?";
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, codigoGanho);
            stmt.executeUpdate();
            System.out.println("Ganho removido.");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new DBException("Erro ao remover.");
        } finally {
            try {
                Objects.requireNonNull(stmt).close();
                conexao.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}