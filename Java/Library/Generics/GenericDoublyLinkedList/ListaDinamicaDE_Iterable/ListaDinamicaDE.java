package Listas;

import java.util.*;

class ListaDinamicaDE<T> implements Iterable<T> {
    No<T> primeiro;
    No<T> ultimo;
    int cont;

    public ListaDinamicaDE() {
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
            novoNo.anterior = ultimo;
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

        if (index == 0) {
            addFirst(data);
            return;
        }
        No<T> novoNo = new No<>(data);
        No<T> aux = this.primeiro;

        for (int i = 0; i < index; i++) {
            aux = aux.proximo;
        }
        No<T> anterior = aux.anterior;

        anterior.proximo = novoNo;
        novoNo.anterior = anterior;

        novoNo.proximo = aux;
        aux.anterior = novoNo;

        this.cont++;
    }

    public void addFirst(T data) {
        No<T> novoNo = new No<>(data);
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
        No<T> removido;
        if (indice == cont - 1) {
            removido = this.ultimo;
        }
        else if (indice == 0) {
            removido = this.primeiro;
        } else {
            No<T> aux = this.primeiro;

            for (int i = 0; i < indice; i++) {
                aux = aux.proximo;
            }
            removido = aux;
        }
        return unlink(removido);
    }

    public T remove(T elemento) {
        No<T> aux = this.primeiro;
        while (aux != null) {
            if (Objects.equals(aux.data, elemento)) {
                return unlink(aux);
            }
            aux = aux.proximo;
        }
        throw new NoSuchElementException("(!) Elemento nao encontrado (!)");
    }
    private T unlink(No<T> no) {
        if (isEmpty()) {
            throw new NoSuchElementException("(!) Empty List (!)");
        }
        T data = no.data;
        No<T> prox = no.proximo;
        No<T> ant = no.anterior;

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

    @Override
    public Iterator<T> iterator() {
        return new Iterador();
    }

    private class Iterador implements Iterator<T> {
        public No<T> aux = primeiro;

        @Override
        public boolean hasNext() {
            return aux != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("(!) Empty list (!)");
            }
            T data = aux.data;
            aux = aux.proximo;
            return data;
        }

        @Override
        public void remove() {
            Iterator.super.remove();
        }
    }
}