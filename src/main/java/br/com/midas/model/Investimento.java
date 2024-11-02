package br.com.midas.model;

import java.time.LocalDate;

public class Investimento {
    private Long codigoInvestimento;
    private String tipoInvestimento;
    private String nomeAplicacao;
    private String nomeBanco;
    private double valorAplicacao;
    private LocalDate dataInvestimento; // dd/mm/aaaa
    private LocalDate dataVencimento; // dd/mm/aaaa
    private String indicacoesInvestimentos;

    // Construtor padrão
    public Investimento() {
    }

    // Construtor com todos os atributos obrigatórios
    public Investimento(Long codigoInvestimento, String tipoInvestimento, String nomeAplicacao, String nomeBanco,
            double valorAplicacao, LocalDate dataInvestimento, LocalDate dataVencimento,
            String indicacoesInvestimentos) {
        this.codigoInvestimento = codigoInvestimento;
        this.tipoInvestimento = tipoInvestimento;
        this.nomeAplicacao = nomeAplicacao;
        this.nomeBanco = nomeBanco;
        this.valorAplicacao = valorAplicacao;
        this.dataInvestimento = dataInvestimento;
        this.dataVencimento = dataVencimento;
        this.indicacoesInvestimentos = indicacoesInvestimentos;
    }

    // Construtor com o código oculto
    public Investimento(String tipoInvestimento, String nomeAplicacao, String nomeBanco, double valorAplicacao,
            LocalDate dataInvestimento, LocalDate dataVencimento, String indicacoesInvestimentos) {
        this.tipoInvestimento = tipoInvestimento;
        this.nomeAplicacao = nomeAplicacao;
        this.nomeBanco = nomeBanco;
        this.valorAplicacao = valorAplicacao;
        this.dataInvestimento = dataInvestimento;
        this.dataVencimento = dataVencimento;
        this.indicacoesInvestimentos = indicacoesInvestimentos;
    }

    // Métodos getters e setters
    public Long getCodigoInvestimento() {
        return codigoInvestimento;
    }

    public void setCodigoInvestimento(Long codigoInvestimento) {
        this.codigoInvestimento = codigoInvestimento;
    }

    public String getTipoInvestimento() {
        return tipoInvestimento;
    }

    public void setTipoInvestimento(String tipoInvestimento) {
        this.tipoInvestimento = tipoInvestimento;
    }

    public String getNomeAplicacao() {
        return nomeAplicacao;
    }

    public void setNomeAplicacao(String nomeAplicacao) {
        this.nomeAplicacao = nomeAplicacao;
    }

    public String getNomeBanco() {
        return nomeBanco;
    }

    public void setNomeBanco(String nomeBanco) {
        this.nomeBanco = nomeBanco;
    }

    public double getValorAplicacao() {
        return valorAplicacao;
    }

    public void setValorAplicacao(double valorAplicacao) {
        this.valorAplicacao = valorAplicacao;
    }

    public LocalDate getDataInvestimento() {
        return dataInvestimento;
    }

    public void setDataInvestimento(LocalDate dataInvestimento) {
        this.dataInvestimento = dataInvestimento;
    }

    public LocalDate getDataVencimento() {
        return dataVencimento;
    }

    public void setDataVencimento(LocalDate dataVencimento) {
        this.dataVencimento = dataVencimento;
    }

    public String getIndicacoesInvestimentos() {
        return indicacoesInvestimentos;
    }

    public void setIndicacoesInvestimentos(String indicacoesInvestimentos) {
        this.indicacoesInvestimentos = indicacoesInvestimentos;
    }
}