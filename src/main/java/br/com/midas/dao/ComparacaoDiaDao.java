package br.com.midas.dao;

import br.com.midas.factory.ConnectionFactory;
import br.com.midas.model.ComparacaoDia;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ComparacaoDiaDao {

    private Connection conexao;

    public ComparacaoDiaDao() throws SQLException {
        conexao = ConnectionFactory
                .getInstance()
                .getConnection();
    }

    public void cadastrar(ComparacaoDia comparacaoDia) throws SQLException {
        String sql = "INSERT INTO T_MSF_COMPARACAO_DIA " +
                "(cd_comparacao, cd_ganho, cd_gasto, cd_calendario, dt_comparacao, vl_soma_ganhos, vl_soma_gastos, vl_saldo_dia) "
                +
                "VALUES (SEQ_MSF_COMPARACAO_DIA.nextval, 1, 1, 1, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            // Convertendo LocalDate para String no formato dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataComparacaoFormatada = comparacaoDia.getDataComparacao().format(formatter);
            stmt.setString(1, dataComparacaoFormatada);
            stmt.setDouble(2, comparacaoDia.getSomaGanhos());
            stmt.setDouble(3, comparacaoDia.getSomaGastos());
            stmt.setDouble(4, comparacaoDia.getSaldoDia());
            stmt.executeUpdate();
        }
    }

    public List<ComparacaoDia> getAll() throws SQLException {
        List<ComparacaoDia> comparacoesDia = new ArrayList<>();

        String sql = "SELECT * FROM T_MSF_COMPARACAO_DIA";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Long codigoComparacao = resultado.getLong("cd_comparacao");
                // Convertendo String para LocalDate
                LocalDate dataComparacao = LocalDate.parse(resultado.getString("dt_comparacao"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                double somaGanhos = resultado.getDouble("vl_soma_ganhos");
                double somaGastos = resultado.getDouble("vl_soma_gastos");
                double saldoDia = resultado.getDouble("vl_saldo_dia");

                ComparacaoDia comparacaoDia = new ComparacaoDia(codigoComparacao, dataComparacao, somaGanhos,
                        somaGastos, saldoDia);
                comparacoesDia.add(comparacaoDia);
            }
        }

        return comparacoesDia;
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}