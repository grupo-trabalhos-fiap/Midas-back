package br.com.midas.model;

import java.time.LocalDate;

public class Gasto {
    private Long codigoGasto;
    private double valorGasto;
    private LocalDate dataGasto; // dd/mm/aaaa
    private String categoria;
    private String descricaoGasto;

    // Construtor padrão
    public Gasto() {
    }

    // Construtor com todos os atributos obrigatórios
    public Gasto(Long codigoGasto, double valorGasto, LocalDate dataGasto, String categoria, String descricaoGasto) {
        this.codigoGasto = codigoGasto;
        this.valorGasto = valorGasto;
        this.dataGasto = dataGasto;
        this.categoria = categoria;
        this.descricaoGasto = descricaoGasto;
    }

    // Construtor com o código oculto
    public Gasto(double valorGasto, LocalDate dataGasto, String categoria, String descricaoGasto) {
        this.valorGasto = valorGasto;
        this.dataGasto = dataGasto;
        this.categoria = categoria;
        this.descricaoGasto = descricaoGasto;
    }

    // Métodos getters e setters
    public Long getCodigoGasto() {
        return codigoGasto;
    }

    public void setCodigoGasto(Long codigoGasto) {
        this.codigoGasto = codigoGasto;
    }

    public double getValorGasto() {
        return valorGasto;
    }

    public void setValorGasto(double valorGasto) {
        this.valorGasto = valorGasto;
    }

    public LocalDate getDataGasto() {
        return dataGasto;
    }

    public void setDataGasto(LocalDate dataGasto) {
        this.dataGasto = dataGasto;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricaoGasto() {
        return descricaoGasto;
    }

    public void setDescricaoGasto(String descricaoGasto) {
        this.descricaoGasto = descricaoGasto;
    }
}