package dev.josepicoli.sistemaescolar.model;

public enum Sexo {
    MASCULINO("Masculino"),
    FEMININO("Feminino"),
    NAO_BINARIO("Não binário"),
    NAO_INFORMADO("Não informado");

    private final String nome;

    Sexo(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
