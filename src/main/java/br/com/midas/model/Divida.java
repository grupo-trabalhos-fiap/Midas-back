package br.com.midas.model;

import java.time.LocalDate;

public class Divida {
    private Long codigoDivida;
    private double valorDivida;
    private LocalDate dataPagamento; // dd/mm/aaaa
    private boolean paga;
    private double juros;
    private LocalDate dataDivida; // dd/mm/aaaa

    // Construtor padrão
    public Divida() {
    }

    // Construtor com todos os atributos obrigatórios
    public Divida(Long codigo, double valorDivida, LocalDate dataPagamento, boolean paga, double juros,
            LocalDate dataDivida) {
        this.codigoDivida = codigo;
        this.valorDivida = valorDivida;
        this.dataPagamento = dataPagamento;
        this.paga = paga;
        this.juros = juros;
        this.dataDivida = dataDivida;
    }

    // Construtor com o código oculto
    public Divida(double valorDivida, LocalDate dataPagamento, boolean paga, double juros, LocalDate dataDivida) {
        this.valorDivida = valorDivida;
        this.dataPagamento = dataPagamento;
        this.paga = paga;
        this.juros = juros;
        this.dataDivida = dataDivida;
    }

    // Métodos getters e setters
    public Long getCodigoDivida() {
        return codigoDivida;
    }

    public void setCodigoDivida(Long codigoDivida) {
        this.codigoDivida = codigoDivida;
    }

    public double getValorDivida() {
        return valorDivida;
    }

    public void setValorDivida(double valorDivida) {
        this.valorDivida = valorDivida;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDate dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public boolean isPaga() {
        return paga;
    }

    public void setPaga(boolean paga) {
        this.paga = paga;
    }

    public double getJuros() {
        return juros;
    }

    public void setJuros(double juros) {
        this.juros = juros;
    }

    public LocalDate getDataDivida() {
        return dataDivida;
    }

    public void setDataDivida(LocalDate dataDivida) {
        this.dataDivida = dataDivida;
    }
}