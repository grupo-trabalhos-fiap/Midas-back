package br.com.midas.model;

import java.time.LocalDate;

public class Ganho {
    private Long codigoGanho;
    private double valorGanho;
    private LocalDate dataGanho; // dd/mm/aaa
    private String descricaoGanho;

    // Construtor padrão
    public Ganho() {
    }

    // Construtor com todos os atributos obrigatórios
    public Ganho(Long codigoGanho, double valorGanho, LocalDate dataGanho, String descricaoGanho) {
        this.codigoGanho = codigoGanho;
        this.valorGanho = valorGanho;
        this.dataGanho = dataGanho;
        this.descricaoGanho = descricaoGanho;
    }

    // Construtor com o código oculto
    public Ganho(double valorGanho, LocalDate dataGanho, String descricaoGanho) {
        this.valorGanho = valorGanho;
        this.dataGanho = dataGanho;
        this.descricaoGanho = descricaoGanho;
    }

    // Métodos getters e setters
    public Long getCodigoGanho() {
        return codigoGanho;
    }

    public void setCodigoGanho(Long codigoGanho) {
        this.codigoGanho = codigoGanho;
    }

    public double getValorGanho() {
        return valorGanho;
    }

    public void setValorGanho(double valorGanho) {
        this.valorGanho = valorGanho;
    }

    public LocalDate getDataGanho() {
        return dataGanho;
    }

    public void setDataGanho(LocalDate dataGanho) {
        this.dataGanho = dataGanho;
    }

    public String getDescricaoGanho() {
        return descricaoGanho;
    }

    public void setDescricaoGanho(String descricaoGanho) {
        this.descricaoGanho = descricaoGanho;
    }
}