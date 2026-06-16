package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Promocao implements Serializable {
    private static final long serialVersionUID = 7L;

    private String nome;
    private double percentagemDesconto;
    private LocalDate dataInicio;
    private LocalDate dataFim;
    private String observacoes;

    public Promocao(String nome, double percentagemDesconto, LocalDate dataInicio,
                    LocalDate dataFim, String observacoes) {
        this.nome = nome;
        this.percentagemDesconto = percentagemDesconto;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.observacoes = observacoes;
    }

    public boolean estaAtiva() {
        LocalDate hoje = LocalDate.now();
        return !hoje.isBefore(dataInicio) && !hoje.isAfter(dataFim);
    }

    public double aplicarDesconto(double precoOriginal) {
        return precoOriginal * (1 - percentagemDesconto / 100.0);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPercentagemDesconto() {
        return percentagemDesconto;
    }

    public void setPercentagemDesconto(double percentagemDesconto) {
        this.percentagemDesconto = percentagemDesconto;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public String getObservacoes() {
        return observacoes;
    }

    public void setObservacoes(String observacoes) {
        this.observacoes = observacoes;
    }

    @Override
    public String toString() {
        return "Promoção: " + nome + " | Desconto: " + percentagemDesconto + "% | "
                + dataInicio + " a " + dataFim + " | Ativa: " + estaAtiva();
    }
}
