public class ListaEstatica<T> {
    private Object v[];
    private int cont = 0;

    ListaEstatica() {
        v = new Object[10];
    }

    ListaEstatica(int tam) {
        v = new Object[tam];
    }

    public void add(T data) {
        if (cont == v.length) {
            throw new IndexOutOfBoundsException("(!) Sem espaço na lista (!)");
        }
        v[cont] = data;
        cont++;
    }

    public void add(int pos, T data) {
        if (pos < 0 || pos >= cont) {
            throw new IndexOutOfBoundsException("(!) Posição inválida (!)");
        }
        if (cont == v.length) {
            throw new IndexOutOfBoundsException("(!) Sem espaço na lista (!)");
        }

        for (int i = cont; i > pos; i--) {
            v[i] = v[i - 1];
        }
        v[pos] = data;
        cont++;
    }

    public boolean contains(T elemento) {
        return indexOf(elemento) >= 0;
    }

    public int indexOf(T elemento) {
        for (int i =0; i < cont; i++) {
            if (elemento == null) {
                if (v[i] == null) {
                    return i;
                }
            }
            else if (elemento.equals(v[i])) {
                return i;
            }
        }
        return -1;
    }

    @SuppressWarnings("unchecked")
    public T remove(int pos) {
        if (pos < 0 || pos >= cont) {
            throw new IndexOutOfBoundsException("(!) Posição inválida (!)");
        }
        T removido =  (T) v[pos];

        for (int i = pos; i < cont - 1; i++) {
            v[i] = v[i + 1];
        }
        v[cont - 1] = null;
        cont--;

        return removido;
    }

    public boolean remove(T elemento) {
        int indice = indexOf(elemento);
        if (indice != -1) {
            remove(indice);
            return true;
        }
        return false;
    }

    public void clear() {
        for (int i =0; i < cont; i++) {
            v[i] = null;
        }
        cont = 0;
    }
    public boolean isEmpty() {
        return cont == 0;
    }

    @SuppressWarnings("unchecked")
    public T get(int pos) {
        if (pos < 0 || pos >= cont) {
            throw new IndexOutOfBoundsException("(!) Posição inválida (!)");
        }
        return (T) v[pos];
    }

    public int size() {
        return cont;
    }

    @Override
    public String toString() {
        if (cont == 0) {
            return "[]";
        }
        StringBuilder sb = new StringBuilder("[");

        for (int i = 0; i < cont; i++) {
            sb.append(v[i]);

            if (i < cont - 1) {
                sb.append(", ");
            }
        }
        return sb.append("]").toString();
    }
}
