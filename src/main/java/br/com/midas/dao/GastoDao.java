package br.com.midas.dao;

import br.com.midas.factory.ConnectionFactory;
import br.com.midas.model.Gasto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class GastoDao {

    private Connection conexao;

    public GastoDao() throws SQLException {
        conexao = ConnectionFactory
                .getInstance()
                .getConnection();
    }

    public void cadastrar(Gasto gasto) throws SQLException {
        String sql = "INSERT INTO T_MSF_GASTOS " +
                "(cd_gasto, cd_usuario, vl_gasto, dt_gasto, categoria, ds_gasto) " +
                "VALUES (SEQ_MSF_GASTOS.nextval, 1, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, gasto.getValorGasto());
            // Convertendo LocalDate para String no formato dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataGastoFormatada = gasto.getDataGasto().format(formatter);
            stmt.setString(2, dataGastoFormatada);
            stmt.setString(3, gasto.getCategoria());
            stmt.setString(4, gasto.getDescricaoGasto());
            stmt.executeUpdate();
        }
    }

    public List<Gasto> getAll() throws SQLException {
        List<Gasto> gastos = new ArrayList<>();

        String sql = "SELECT * FROM T_MSF_GASTOS";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Long codigoGasto = resultado.getLong("cd_gasto");
                double valorGasto = resultado.getDouble("vl_gasto");
                // Convertendo String para LocalDate
                LocalDate dataGasto = LocalDate.parse(resultado.getString("dt_gasto"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                String categoria = resultado.getString("categoria");
                String descricaoGasto = resultado.getString("ds_gasto");

                Gasto gasto = new Gasto(codigoGasto, valorGasto, dataGasto, categoria, descricaoGasto);
                gastos.add(gasto);
            }
        }

        return gastos;
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}