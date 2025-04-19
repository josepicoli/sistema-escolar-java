package dev.josepicoli.sistemaescolar.view;

import dev.josepicoli.sistemaescolar.controller.ControleDeAlunos;
import dev.josepicoli.sistemaescolar.exceptions.AlunoNotFoundException;
import dev.josepicoli.sistemaescolar.model.Aluno;
import dev.josepicoli.sistemaescolar.model.Sexo;

import java.util.List;
import java.util.Scanner;

public class Menu {
    private static final ControleDeAlunos alunos = new ControleDeAlunos();

    public static void iniciarMenu() {
        Scanner scanner = new Scanner(System.in);

        final String menu = """
                1 - incluir aluno.
                2 - alterar aluno.
                3 - consultar aluno.
                4 - excluir aluno.
                5 - listar alunos.
                0 - encerrar programa.
                """;

        while (true) {
            System.out.println(menu);
            int opcao = pegarInt(">>> ", scanner);

            if (opcao == 0)
                break;

            switch (opcao) {
                case 1:
                    incluirAluno(scanner);
                    break;
                case 2:
                    alterarAluno(scanner);
                    break;
                case 3:
                    consultarAluno(scanner);
                    break;
                case 4:
                    excluirAluno(scanner);
                    break;
                case 5:
                    listarAlunos();
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente.");
                    break;
            }
        }
    }

    private static Aluno pegarDadosAluno(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Digite a matrícula: ");
                int matricula = scanner.nextInt();

                System.out.print("Digite o nome: ");
                String nome = scanner.next();

                System.out.print("Digite a idade: ");
                int idade = scanner.nextInt();

                Sexo sexo = pegarSexo(scanner);

                return new Aluno(matricula, nome, idade, sexo);
            } catch (Exception e) {
                System.out.println("Dados inválidos, tente novamente.");
                scanner.nextLine();
            }
        }
    }

    private static Sexo pegarSexo(Scanner scanner) {
        while (true) {
           try {
               System.out.print("""
                       Digite a opção do sexo:
                       1 - Masculino.
                       2 - Feminino.
                       3 - Não binário.
                       4 - Não informado.
                       """);
               int opcao = pegarInt(">>> ", scanner);

               switch (opcao) {
                   case 1:
                       return Sexo.MASCULINO;
                   case 2:
                       return Sexo.FEMININO;
                   case 3:
                       return Sexo.NAO_BINARIO;
                   case 4:
                       return Sexo.NAO_INFORMADO;
                   default:
                       System.out.println("Opção inválida, tente novamente.");
               }

           } catch (Exception e) {
               System.out.println("Dado inválido, tente novamente.");
               scanner.nextLine();
           }
        }
    }

    private static int pegarInt(String msg, Scanner scanner) {
        while (true) {
            try {
                System.out.print(msg);
                return scanner.nextInt();
            } catch (Exception e) {
                System.out.println("Dado inválido, tente novamente.");
                scanner.nextLine();
            }
        }
    }

    private static void mostrarAluno(Aluno aluno) {
        String msg = """
                Matrícula: %d
                Nome: %s
                Idade: %d
                Sexo: %s
                """.formatted(aluno.getMatricula(), aluno.getNome(), aluno.getIdade(), aluno.getSexo().getNome());
        System.out.println(msg);
    }

    private static void incluirAluno(Scanner scanner) {
        alunos.adicionarAluno(pegarDadosAluno(scanner));
        System.out.println("Aluno incluido com sucesso.");
    }

    private static void alterarAluno(Scanner scanner) {
        int matricula = pegarInt("Digite a matrícula do aluno a ser alterado: ", scanner);
        Aluno aluno = pegarDadosAluno(scanner);
        try {
            alunos.alterarAluno(matricula, aluno);
            System.out.println("Aluno alterado com sucesso.");
        } catch (AlunoNotFoundException e) {
            System.out.println("Aluno com a matrícula " + matricula + " não foi encontrado, alteração não realizada.");
        }
    }

    private static void consultarAluno(Scanner scanner) {
        int matricula = pegarInt("Digite a matrícula do aluno a ser consultado: ", scanner);
        try {
            Aluno aluno = alunos.consultarAluno(matricula);
            mostrarAluno(aluno);
        } catch (AlunoNotFoundException e) {
            System.out.println("Aluno com a matrícula " + matricula + " não foi encontrado.");
        }
    }

    private static void excluirAluno(Scanner scanner) {
        int matricula = pegarInt("Digite a matrícula do aluno a ser excluido: ", scanner);
        try {
            alunos.excluirAluno(matricula);
            System.out.println("Aluno excluído com sucesso.");
        } catch (AlunoNotFoundException e) {
            System.out.println("Aluno com a matrícula " + matricula + " não foi encontrado, exclução não realizada.");
        }
    }

    private static void listarAlunos() {
        List<Aluno> listaCopia = alunos.listarAlunos();

        if (listaCopia.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado");
            return;
        }

        for (Aluno aluno : listaCopia)
            mostrarAluno(aluno);
    }
}
