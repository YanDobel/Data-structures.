package Listas;

public class NoLO<T extends Comparable> {
    T data;
    NoLO<T> proximo;
    NoLO<T> anterior;

    NoLO(T data) {
        this.data = data;
        this.proximo = null;
        this.anterior = null;
    }
}