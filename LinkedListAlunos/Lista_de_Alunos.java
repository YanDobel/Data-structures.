package LinkedListAlunos;

// === Classe no aluno com construtor do no com os atributos ===

class NoAluno {

    private String nome;
    private int matricula;

    Lista_de_notas notas;
    NoAluno prox;

    public NoAluno (String nome, int matricula) {

        this.nome = nome;
        this.matricula = matricula;
        this.notas = new Lista_de_notas();
        this.prox = null;
    }

    // === Metodos geters ===

    public int getMatricula() {
       return this.matricula;
    }

    public String getNome() {
        return this.nome;
    }
}

// === Classe Lista de alunos ===

public class Lista_de_Alunos {

    private NoAluno inicio;

    public Lista_de_Alunos() {
        this.inicio = null;
    }

    // === Metodos para inserir alunos e notas ===

    public void InserirAluno (String nome, int matricula) {

        NoAluno novo = new NoAluno(nome, matricula);
        novo.prox = this.inicio;
        this.inicio = novo;
    }

    public void InserirNota (int matricula, double nota) {

        NoAluno atual = this.inicio;

        while (atual != null) {

            if (atual.getMatricula() == matricula) {

                atual.notas.InserirNota(nota);
                return;
            }
            atual = atual.prox;
        }

    }

    // === Metodos para imprimir Alunos e Notas ===

    public void ImprimirAlunoEspecifico (int matricula) {

        NoAluno atual = this.inicio;

        while (atual != null) {

            if (atual.getMatricula() == matricula) {

                System.out.println("Nome: " + atual.getNome());
                System.out.println("Matricula: " + atual.getMatricula());
                ImprimirNotas(atual.getMatricula());
                System.out.println("Media: " + MediaDasNotas(atual.getMatricula()));
            }

            atual = atual.prox;
        }
        return;
    }

    public void ImprimirTodosAlunos() {

        NoAluno atual = this.inicio;

        while (atual != null) {

            System.out.println("Aluno: " + atual.getNome() + "\nMatrícula: " + atual.getMatricula());
            ImprimirNotas(atual.getMatricula());
            System.out.println("Media das notas: " + MediaDasNotas(atual.getMatricula()));
            System.out.println("-----------------------");

            atual = atual.prox;
        }
    }

    public void ImprimirNotas(int matricula) {

        NoAluno atual = this.inicio;

        while (atual != null) {

            if (atual.getMatricula() == matricula) {

                NoNota noNota = atual.notas.getInicioNotas();

                while (noNota != null) {

                    System.out.println("Notas: " + noNota.nota);
                    noNota = noNota.prox;
                }
                return;
            }
            atual = atual.prox;
        }
        System.out.println("----------------------");
    }

    // === Metodo para calcular a media das notas de um aluno especifico ===

    public double MediaDasNotas(int matricula) {

        NoAluno atual = this.inicio;

        while (atual != null) {

            if (atual.getMatricula() == matricula) {

               NoNota noNota = atual.notas.getInicioNotas();

               double soma = 0;
               int contador = 0;

               while (noNota != null) {

                   soma += noNota.nota;
                   contador ++;
                   noNota = noNota.prox;
               }

               if (contador == 0) {
                   return 0;
               }

               return soma / contador;
            }
            atual = atual.prox;
        }
        return -1;
    }
    public void removerTodosAlunos() {
        this.inicio = null;
    }
}