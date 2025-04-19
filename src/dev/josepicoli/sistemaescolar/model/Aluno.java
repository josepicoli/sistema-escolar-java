package dev.josepicoli.sistemaescolar.model;

public class Aluno {
    private int matricula;
    private String nome;
    private int idade;
    private Sexo sexo;

    public Aluno() {

    }

    public Aluno(int matricula, String nome, int idade, Sexo sexo) {
        this.matricula = matricula;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
    }

    /* Getters */
    public int getMatricula() {
        return matricula;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public Sexo getSexo() {
        return sexo;
    }

    /* Setters */
    public void setMatricula(int matricula) {
        this.matricula = matricula;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public void setSexo(Sexo sexo) {
        this.sexo = sexo;
    }
}
