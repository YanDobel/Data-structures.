import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayList<T> implements Iterable<T> {
    private Object array[];
    private int tamanho;
    private int capacidadePadrao = 10;

    public ArrayList() {

        this.array = new Object[capacidadePadrao];
        this.tamanho = 0;
    }

    public void add(T valor) {
        if (tamanho == this.array.length) {
            redimensionar();
        }

        this.array[this.tamanho] = valor;
        this.tamanho++;
    }

    public void remove(int indice) {
        if (indice < 0 || indice >= this.tamanho) {
            throw new IndexOutOfBoundsException("(!) Indice fora dos limites (!)");
        }

        for (int i = indice; i < this.tamanho - 1; i++) {
            this.array[i] = this.array[i + 1];
        }

        this.array[this.tamanho - 1] = null;
        this.tamanho--;
        encolher();
    }

    public void redimensionar() {
        int novaCapacidade = this.array.length * 2;
        Object novoArray[] = new Object[novaCapacidade];

        for (int i = 0; i < this.array.length; i++) {
            novoArray[i] = this.array[i];
        }

        this.array = novoArray;
    }

    private void encolher() {
        if (this.array.length > capacidadePadrao && this.tamanho <= this.array.length / 4) {
            int novaCapacidade = this.array.length / 2;
            Object novoArray[] = new Object[novaCapacidade];

            for (int i = 0; i < tamanho; i++) {
                novoArray[i] = this.array[i];
            }
            this.array = novoArray;
        }
    }
    public boolean contains(T valor) {
        for (int i = 0; i < this.tamanho; i++) {
            if (array[i].equals(valor)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        return this.tamanho == 0;
    }

    @SuppressWarnings("unchecked")
    public T get(int indice) {
        if (indice < 0 || indice >= this.tamanho) {
            throw new IndexOutOfBoundsException("(!) Indice fora dos limites (!)");
        }
        return (T) this.array[indice];
    }
    public int size() {
        return this.tamanho;
    }

    @Override
    public String toString() {
        if (tamanho == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < tamanho; i++) {
            sb.append(array[i]);

            if (i < tamanho - 1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
    public Iterator<T> iterator() {
        return new ALIterator();
    }

    private class ALIterator implements Iterator<T> {
        private int indiceAtual = 0;

        @Override
        public boolean hasNext() {
            return indiceAtual < tamanho;
        }

        @Override
        @SuppressWarnings("unchecked")
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("(!) Empty List (!)");
            }
            return (T) array[indiceAtual++];
        }
    }
}
