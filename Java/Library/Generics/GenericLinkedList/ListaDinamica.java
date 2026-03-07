public class ListaDinamica<T> {
    No<T> primeiro;
    No<T> ultimo;
    int cont;

    public ListaDinamica() {
        this.primeiro = null;
        this.ultimo = null;
        this.cont = 0;
    }

    public void add(T data) {
        No<T> novoNo = new No<>(data);
        if (primeiro == null) {
            primeiro = novoNo;
            ultimo = novoNo;
        } else {
            ultimo.proximo = novoNo;
            ultimo = novoNo;
        }
        cont++;
    }

    public void add(int index, T data) {
        if (index < 0 || index > this.cont) {
            throw new IndexOutOfBoundsException("(!) índice fora do limite (!)");
        }
        if (index == this.cont) {
            add(data);
            return;
        }

        No<T> novoNo = new No<>(data);
        if (index == 0) {
            novoNo.proximo = this.primeiro;
            this.primeiro = novoNo;

        } else {
            No<T> aux = this.primeiro;

            for (int i = 0; i < index - 1; i++) {
                aux = aux.proximo;
            }
            novoNo.proximo = aux.proximo;
            aux.proximo = novoNo;
        }
        this.cont++;
    }

    public T remove(int indice) {
        if (indice < 0 || indice >= this.cont) {
            throw new IndexOutOfBoundsException("(!) Índice fora do limite (!)");
        }
        No<T> removido;

        if (indice == 0) {
            removido = this.primeiro;
            this.primeiro = this.primeiro.proximo;

            if (this.cont == 1) {
                this.ultimo = null;
            }
        } else {
            No<T> aux = this.primeiro;

            for (int i = 0; i < indice - 1; i++) {
                aux = aux.proximo;
            }
            removido = aux.proximo;
            aux.proximo = removido.proximo;

            if (removido == this.ultimo) {
                this.ultimo = aux;
            }
        }
        this.cont--;
        return removido.data;
    }

    public boolean remove(T elemento) {
        int indice = indexOf(elemento);
        if (indice != -1) {
            remove(indice);
            return true;
        }
        return false;
    }

    public T get(int indice) {
        if (indice < 0 || indice >= cont) {
            throw new IndexOutOfBoundsException("(!) Índice fora de limite (!)");
        }
        No<T> aux = this.primeiro;

        for (int i = 0; i < indice; i++) {
            aux = aux.proximo;
        }
        return aux.data;
    }

    public T get(T elemento) {
        int indice = indexOf(elemento);
        return get(indice);
    }

    public int size() {
        return cont;
    }

    public boolean contains(T data) {
        return indexOf(data) >= 0;
    }

    public int indexOf(T data) {
        No<T> aux = primeiro;
        int cont = 0;

        while (aux != null) {
            if (aux.data.equals(data)) {
                return cont;
            }
            aux = aux.proximo;
            cont++;
        }
        return -1;
    }

    public void clear() {
        this.primeiro = null;
        this.ultimo = null;
        this.cont = 0;
    }

    public boolean isEmpty() {
        return this.cont == 0;
    }

    @Override
    public String toString() {
        if (cont == 0) return "[]";

        StringBuilder sb = new StringBuilder("[");
        No<T> aux = this.primeiro;

        while (aux != null) {
            sb.append(aux.data);

            aux = aux.proximo;

            if (aux != null) sb.append(", ");
        }
        return sb.append("]").toString();
    }
}

class No<T> {
    T data;
    No<T> proximo;

    No(T data) {
        this.data = data;
        proximo = null;
    }
}