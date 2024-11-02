package br.com.midas.dao;

import br.com.midas.factory.ConnectionFactory;
import br.com.midas.model.Investimento;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class InvestimentoDao {

    private Connection conexao;

    public InvestimentoDao() throws SQLException {
        conexao = ConnectionFactory
                .getInstance()
                .getConnection();
    }

    public void cadastrar(Investimento investimento) throws SQLException {
        String sql = "INSERT INTO T_MSF_INVESTIMENTOS " +
                "(cd_investimento, cd_usuario, tipo_investimento, nm_aplicacao, nm_banco, vl_aplicacao, dt_investimento, dt_vencimento, indicacoes_investimentos) "
                +
                "VALUES (SEQ_MSF_INVESTIMENTOS.nextval, 1, ?, ?, ?, ?, ?, ?, ?)"; // Utilizando 1 para cd_usuario no
                                                                                  // teste

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, investimento.getTipoInvestimento());
            stmt.setString(2, investimento.getNomeAplicacao());
            stmt.setString(3, investimento.getNomeBanco());
            stmt.setDouble(4, investimento.getValorAplicacao());
            // Convertendo LocalDate para String no formato dd/MM/yyyy
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataInvestimentoFormatada = investimento.getDataInvestimento().format(formatter);
            stmt.setString(5, dataInvestimentoFormatada);
            if (investimento.getDataVencimento() != null) {
                String dataVencimentoFormatada = investimento.getDataVencimento().format(formatter);
                stmt.setString(6, dataVencimentoFormatada);
            } else {
                stmt.setNull(6, java.sql.Types.VARCHAR);
            }
            stmt.setString(7, investimento.getIndicacoesInvestimentos());
            stmt.executeUpdate();
        }
    }

    public List<Investimento> getAll() throws SQLException {
        List<Investimento> investimentos = new ArrayList<>();

        String sql = "SELECT * FROM T_MSF_INVESTIMENTOS";

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            ResultSet resultado = stmt.executeQuery();
            while (resultado.next()) {
                Long codigoInvestimento = resultado.getLong("cd_investimento");
                String tipoInvestimento = resultado.getString("tipo_investimento");
                String nomeAplicacao = resultado.getString("nm_aplicacao");
                String nomeBanco = resultado.getString("nm_banco");
                double valorAplicacao = resultado.getDouble("vl_aplicacao");
                // Convertendo String para LocalDate
                LocalDate dataInvestimento = LocalDate.parse(resultado.getString("dt_investimento"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
                LocalDate dataVencimento = resultado.getDate("dt_vencimento") != null
                        ? resultado.getDate("dt_vencimento").toLocalDate()
                        : null;
                String indicacoesInvestimentos = resultado.getString("indicacoes_investimentos");

                Investimento investimento = new Investimento(codigoInvestimento, tipoInvestimento, nomeAplicacao,
                        nomeBanco, valorAplicacao, dataInvestimento, dataVencimento, indicacoesInvestimentos);
                investimentos.add(investimento);
            }
        }

        return investimentos;
    }

    public void fecharConexao() throws SQLException {
        conexao.close();
    }
}