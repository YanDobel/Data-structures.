package LinkedListAlunos;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // === Input dos valores ===

        Lista_de_Alunos lista = new Lista_de_Alunos();

        int matricula = 0;

        boolean continuar = true;

        while(continuar) {

            System.out.println("Digite o nome do aluno (ou 'sair'): ");
            String nome = sc.nextLine();

            if (nome.equalsIgnoreCase("sair")) {
                break;
            }

            System.out.println("Digite o matricula do aluno: ");
            matricula = sc.nextInt();

            sc.nextLine();

            lista.InserirAluno(nome, matricula);

            for (int j = 0; j < 5; j++) {

                System.out.println("Digite a nota " + (j + 1) + " do aluno: ");
                double nota = sc.nextDouble();
                lista.InserirNota(matricula, nota);
            }
            sc.nextLine();
        }

        // === Imprimir resultados ===

        while (continuar) {
            System.out.println("Digite: \n1 -> Imprimir todos os alunos \n2 -> Imprimir um aluno especifico " + "\n3 -> Remover todos os alunos" +
                    "\n4 -> Sair do programa");
            int op = sc.nextInt();

            if (op == 1) {
                lista.ImprimirTodosAlunos();
            }
            else if (op == 2) {

                System.out.println("Digite a matricula do aluno: ");

                int matriculaBuscada = sc.nextInt();
                lista.ImprimirAlunoEspecifico(matriculaBuscada);
            }
            else if (op == 3) {
                lista.removerTodosAlunos();
            }
            else if (op == 4) {
                break;
            }
        }
        sc.close();
    }
}
