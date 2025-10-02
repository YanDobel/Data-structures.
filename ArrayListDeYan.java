public class ArrayListDeYan<type> {
    private Object array[];
    private int tamanho;

    public ArrayListDeYan() {
        this.array = new Object[10];
        this.tamanho = 0;
    }

    public void add(type data) {

        if (this.tamanho == this.array.length) {
            Object aux[] = new Object[array.length * 2];

            for (int i = 0; i < array.length; i++) {
                aux[i] = this.array[i];
            }
            this.array = aux;
        }
        this.array[this.tamanho++] = data;
    }

    public type get (int indice) {
        if (indice < 0 || indice > this.tamanho) {
            System.out.println("(!) Indice invalido (!)");
        }
        return (type) this.array[indice];
    }

    public int size() {
        return this.tamanho;
    }
}
