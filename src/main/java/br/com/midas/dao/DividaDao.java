package br.com.midas.dao;

import br.com.midas.factory.ConnectionFactory;
import br.com.midas.model.Divida;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class DividaDao {

    private Connection conexao;

    public DividaDao() throws SQLException {
        conexao = ConnectionFactory
                .getInstance()
                .getConnection();
    }

    public void cadastrar(Divida divida) throws SQLException {
        String sql = "INSERT INTO T_MSF_DIVIDAS " +
                "(cd_divida, cd_usuario, vl_divida, dt_pagamento, ds_paga, vl_juros, dt_dividas) " +
                "VALUES (SEQ_MSF_DIVIDAS.nextval, 1, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setDouble(1, divida.getValorDivida());
            // Convertendo LocalDate para String no formato dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataPagamentoFormatada = divida.getDataPagamento().format(formatter);
            stmt.setString(2, dataPagamentoFormatada);
            stmt.setString(3, divida.isPaga() ? "S" : "N");
            stmt.setDouble(4, divida.getJuros());
            String dataDividaFormatada = divida.getDataDivida().format(formatter);
            stmt.setString(5, dataDividaFormatada);
            stmt.executeUpdate();
        }
    }

    public List<Divida> getAll() throws SQLException {
        List<Divida> dividas = new ArrayList<>();

        String sql = "SELECT * FROM T_MSF_DIVIDAS";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Long codigoDivida = resultado.getLong("cd_divida");
                double valorDivida = resultado.getDouble("vl_divida");
                // Convertendo String para LocalDate
                LocalDate dataPagamento = LocalDate.parse(resultado.getString("dt_pagamento"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                boolean paga = resultado.getString("ds_paga").equals("S");
                double juros = resultado.getDouble("vl_juros");
                LocalDate dataDivida = LocalDate.parse(resultado.getString("dt_dividas"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

                Divida divida = new Divida(codigoDivida, valorDivida, dataPagamento, paga, juros, dataDivida);
                dividas.add(divida);
            }
        }

        return dividas;
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}