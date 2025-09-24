package LinkedListAlunos;
// === No nota e seu construtor para armazenar as notas dos alunos ===

public class NoNota {

    double nota;
    NoNota prox;

    public NoNota(double nota) {

        this.nota = nota;
        this.prox = null;
    }
}

// === Lista de notas e suas operacoes ===

class Lista_de_notas {

    private NoNota inicio;

    public Lista_de_notas() {
        this.inicio = null;
    }

    // === Geter de notas ===

    public NoNota getInicioNotas() {
        return this.inicio;
    }

    // === Metodo para Inserir as notas dos alunos ===

    public void InserirNota(double nota) {

        NoNota novo = new NoNota(nota);
        novo.prox = this.inicio;
        this.inicio = novo;
    }

    // === Metodo para remover tudo e zerar a lista ===

    public void removerTudo() {
        this.inicio = null;
    }
}