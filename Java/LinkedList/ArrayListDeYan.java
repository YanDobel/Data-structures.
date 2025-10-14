// ==== ArrayList gloriosamente feito por Yan Dobel xD =====

public class ArrayListDeYan<T> {

    private Object array[];
    private int tamanho;

    public ArrayListDeYan() {
        this.array = new Object[10];
        this.tamanho = 0;
    }

    public void add(T data) {

        if (this.tamanho == this.array.length) {
            Object aux[] = new Object[array.length * 2];

            for (int i = 0; i < array.length; i++) {
                aux[i] = this.array[i];
            }
            this.array = aux;
        }
        this.array[this.tamanho++] = data;
    }

    public T get (int indice) {
        if (indice < 0 || indice >= this.tamanho) {
            throw new Error("(!) Invalid Index (!)");
        }
        return (T) this.array[indice];
    }

    public int size() {
        return this.tamanho;
    }

    public void remove(int indice) {
        if (indice < 0 || indice >= this.tamanho) {
            throw new Error("(!) Invalid index (!)");
        }
        for (int i = indice; i < this.tamanho - 1; i++) {
            this.array[i] = this.array[i + 1];
        }
        this.array[--this.tamanho] = null;
    }
}