package br.com.midas.model;

import java.time.LocalDate;

public class Objetivo {
    private int codigoObjetivo;
    private Usuario usuario;
    private String nomeObjetivo;
    private double valorObjetivo;
    private LocalDate dataObjetivo; // dd/mm/aaaa
    private String descricaoObjetivo;

    // Construtor padrão
    public Objetivo() {
    }

    // Construtor com todos os atributos obrigatórios
    public Objetivo(int codigoObjetivo, String nomeObjetivo, double valorObjetivo, LocalDate dataObjetivo,
            String descricaoObjetivo) {
        this.codigoObjetivo = codigoObjetivo;
        this.nomeObjetivo = nomeObjetivo;
        this.valorObjetivo = valorObjetivo;
        this.dataObjetivo = dataObjetivo;
        this.descricaoObjetivo = descricaoObjetivo;
    }

    // Construtor com o código oculto
    public Objetivo(String nomeObjetivo, double valorObjetivo, LocalDate dataObjetivo, String descricaoObjetivo) {
        this.nomeObjetivo = nomeObjetivo;
        this.valorObjetivo = valorObjetivo;
        this.dataObjetivo = dataObjetivo;
        this.descricaoObjetivo = descricaoObjetivo;
    }

    // Métodos getters e setters
    public int getCodigoObjetivo() {
        return codigoObjetivo;
    }

    public void setCodigoObjetivo(int codigoObjetivo) {
        this.codigoObjetivo = codigoObjetivo;
    }

    public String getNomeObjetivo() {
        return nomeObjetivo;
    }

    public void setNomeObjetivo(String nomeObjetivo) {
        this.nomeObjetivo = nomeObjetivo;
    }

    public double getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(double valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }

    public LocalDate getDataObjetivo() {
        return dataObjetivo;
    }

    public void setDataObjetivo(LocalDate dataObjetivo) {
        this.dataObjetivo = dataObjetivo;
    }

    public String getDescricaoObjetivo() {
        return descricaoObjetivo;
    }

    public void setDescricaoObjetivo(String descricaoObjetivo) {
        this.descricaoObjetivo = descricaoObjetivo;
    }
}