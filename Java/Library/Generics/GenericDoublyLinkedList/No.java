package Listas;

class No<T> {
    T data;
    No<T> proximo;
    No<T> anterior;

    No(T data) {
        this.data = data;
        this.proximo = null;
        this.anterior = null;
    }
}
