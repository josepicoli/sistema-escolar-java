package dev.josepicoli.sistemaescolar.controller;

import dev.josepicoli.sistemaescolar.exceptions.AlunoNotFoundException;
import dev.josepicoli.sistemaescolar.model.Aluno;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ControleDeAlunos {
    private final List<Aluno> listaAlunos = new ArrayList<>();

    private int buscarAlunoPorMatricula(int matricula) {
        for (int i = 0; i < listaAlunos.size(); i++) {
            if (matricula == listaAlunos.get(i).getMatricula()) {
                return i;
            }
        }
        return -1;
    }

    public void adicionarAluno(Aluno aluno) {
        listaAlunos.add(aluno);
    }

    public void alterarAluno(int matricula, Aluno aluno) throws AlunoNotFoundException {
        int i = buscarAlunoPorMatricula(matricula);

        if (i == -1)
            throw new AlunoNotFoundException("Aluno não encontrado");

        listaAlunos.set(i, aluno);
    }

    public Aluno consultarAluno(int matricula) throws AlunoNotFoundException {
        int i = buscarAlunoPorMatricula(matricula);

        if (i == -1)
            throw new AlunoNotFoundException("Aluno não encontrado");

        Aluno copy = new Aluno();
        copy.setMatricula(listaAlunos.get(i).getMatricula());
        copy.setNome(listaAlunos.get(i).getNome());
        copy.setIdade(listaAlunos.get(i).getIdade());

        return copy;
    }

    public void excluirAluno(int matricula) throws AlunoNotFoundException {
        int i = buscarAlunoPorMatricula(matricula);

        if (i == -1)
            throw new AlunoNotFoundException("Aluno não encontrado");

        listaAlunos.remove(i);
    }

    public List<Aluno> listarAlunos() {
        return new ArrayList<>(listaAlunos);
    }
}
