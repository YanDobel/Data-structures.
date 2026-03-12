import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ListaDinamicaOrdenada<T extends Comparable<T>> implements Iterable<T> {
    NoLO<T> primeiro;
    NoLO<T> ultimo;
    int cont;

    public ListaDinamicaOrdenada() {
        this.primeiro = null;
        this.ultimo = null;
        this.cont = 0;
    }

    public void add(T data) {
        if (isEmpty()) {
            addFirst(data);
            return;
        }
        if (data.compareTo(this.primeiro.data) <= 0) {
            addFirst(data);
            return;
        }
        if (data.compareTo(this.ultimo.data) >= 0) {
            addLast(data);
            return;
        }
        NoLO<T> aux = this.primeiro;
        while(data.compareTo(aux.data) > 0) {
            aux = aux.proximo;
        }

        NoLO<T> novoNo = new NoLO<>(data);
        NoLO<T> anterior = aux.anterior;

        anterior.proximo = novoNo;
        novoNo.anterior = anterior;

        novoNo.proximo = aux;
        aux.anterior = novoNo;

        cont++;
    }

    public void addPorTamanho(T data) {
        String s1 = (String) data;

        if (isEmpty()) {
            addFirst(data);
            return;
        }
        if (s1.length() < ((String) primeiro.data).length()) {
            addFirst(data);
            return;
        }
        if (s1.length() >= ((String)ultimo.data).length()) {
            addLast(data);
            return;
        }
        NoLO<T> aux = this.primeiro;
        while(s1.length() >= ((String)aux.data).length()) {
            aux = aux.proximo;
        }

        NoLO<T> novoNo = new NoLO<>(data);

        NoLO<T> ant = aux.anterior;
        ant.proximo = novoNo;
        novoNo.anterior = ant;
        novoNo.proximo = aux;
        aux.anterior = novoNo;
        cont++;
    }

    public void addLast(T data) {
        NoLO<T> novoNo = new NoLO<>(data);
        if (primeiro == null) {
            primeiro = novoNo;
            ultimo = novoNo;
        } else {
            ultimo.proximo = novoNo;
            novoNo.anterior = ultimo;
            ultimo = novoNo;
        }
        this.cont++;
    }

    private void addFirst(T data) {
        NoLO<T> novoNo = new NoLO<>(data);
        if (primeiro == null) {
            primeiro = novoNo;
            ultimo = novoNo;
        } else {
            novoNo.proximo = primeiro;
            primeiro.anterior = novoNo;
            primeiro = novoNo;
        }
        this.cont++;
    }


    public T remove(int indice) {
        if (indice < 0 || indice >= this.cont) {
            throw new IndexOutOfBoundsException("(!) Índice fora do limite (!)");
        }
        NoLO<T> removido;
        if (indice == cont - 1) {
            removido = this.ultimo;
        }
        else if (indice == 0) {
            removido = this.primeiro;
        } else {
            NoLO<T> aux = this.primeiro;

            for (int i = 0; i < indice; i++) {
                aux = aux.proximo;
            }
            removido = aux;
        }
        return unlink(removido);
    }

    public T remove(T elemento) {
        NoLO<T> aux = this.primeiro;
        while (aux != null) {
            if (Objects.equals(aux.data, elemento)) {
                return unlink(aux);
            }
            aux = aux.proximo;
        }
        throw new NoSuchElementException("(!) Elemento nao encontrado (!)");
    }
    private T unlink(NoLO<T> no) {
        if (isEmpty()) {
            throw new NoSuchElementException("(!) Empty List (!)");
        }
        T data = no.data;
        NoLO<T> prox = no.proximo;
        NoLO<T> ant = no.anterior;

        if (ant == null) {
            this.primeiro = prox;
        } else {
            ant.proximo = prox;
            no.anterior = null;
        }

        if (prox == null) {
            this.ultimo = ant;
        } else {
            prox.anterior = ant;
            no.proximo = null;
        }
        no.data = null;
        cont--;
        return data;
    }

    public T get(int indice) {
        if (indice < 0 || indice >= cont) {
            throw new IndexOutOfBoundsException("(!) Índice fora de limite (!)");
        }
        NoLO<T> aux = this.primeiro;

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
        NoLO<T> aux = primeiro;
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
        NoLO<T> aux = this.primeiro;

        while (aux != null) {
            sb.append(aux.data);

            aux = aux.proximo;

            if (aux != null) sb.append(", ");
        }
        return sb.append("]").toString();
    }
    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<T> {
        private NoLO<T> aux = primeiro;

        @Override
        public boolean hasNext() {
            return aux != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("(!) Empty List (!)");
            }
            T data = aux.data;
            aux = aux.proximo;
            return data;
        }
    }
}