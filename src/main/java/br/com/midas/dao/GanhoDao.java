package br.com.midas.dao;

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

public class GanhoDao {

    private Connection conexao;

    public GanhoDao() throws SQLException {
        conexao = ConnectionFactory
                .getInstance()
                .getConnection();
    }

    public void cadastrar(Ganho ganho) throws SQLException {
        String sql = "INSERT INTO T_MSF_GANHOS " +
                "(cd_ganho, cd_usuario, vl_ganho, dt_ganho, ds_ganho) " +
                "VALUES (SEQ_MSF_GANHOS.nextval, 1, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, ganho.getValorGanho());
            // Convertendo LocalDate para String no formato dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataGanhoFormatada = ganho.getDataGanho().format(formatter);
            stmt.setString(2, dataGanhoFormatada);
            stmt.setString(3, ganho.getDescricaoGanho());
            stmt.executeUpdate();
        }
    }

    public List<Ganho> getAll() throws SQLException {
        List<Ganho> ganhos = new ArrayList<>();

        String sql = "SELECT * FROM T_MSF_GANHOS";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Long codigoGanho = resultado.getLong("cd_ganho");
                double valorGanho = resultado.getDouble("vl_ganho");
                // Convertendo String para LocalDate
                LocalDate dataGanho = LocalDate.parse(resultado.getString("dt_ganho"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String descricaoGanho = resultado.getString("ds_ganho");

                Ganho ganho = new Ganho(codigoGanho, valorGanho, dataGanho, descricaoGanho);
                ganhos.add(ganho);
            }
        }

        return ganhos;
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}