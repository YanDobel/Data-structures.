
// ==== Tabela hashing com senha tipo String que armazena todo tipo de dado :D ====

class HashNode<K, D> {

    private K key;
    private D data;
    private HashNode<K, D> next;

    public HashNode(K key, D data) {
        this.key = key;
        this.data = data;
        this.next = null;
    }
// ==== Getters and Setters ====

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public HashNode<K, D> getNext() {
        return this.next;
    }

    public void setNext(HashNode<K, D> next) {
        this.next = next;
    }
}

// ------------------------------------------------------------------

// ==== Hash List ====

class HashList<K, D> {

    private HashNode<K, D> first;

    public void Insert(K key, D data) {
        if (first == null) {
            first = new HashNode<>(key, data);
            return;
        }
        HashNode<K, D> aux = first;
        HashNode<K, D> prev = null;

        while (aux != null) {
            if (aux.getKey().equals(key)) {
                aux.setData(data);
                return;
            }
            prev = aux;
            aux = aux.getNext();
        }
        prev.setNext(new HashNode<>(key, data));
    }

    public D RemoveKey(K key) {
        HashNode<K, D> current = first;
        HashNode<K, D> prev = null;

        while (current != null) {
            if (current.getKey().equals(key)) {
                if (prev == null) {
                    first = current.getNext();
                } else {
                    prev.setNext(current.getNext());
                }
                return current.getData();
            }
            prev = current;
            current = current.getNext();
        }
        throw new Error("(!) Key not found - nothing was removed (!)");
    }
    public D Search(K key) {
        HashNode<K, D> aux = first;
        while (aux != null) {
            if (aux.getKey().equals(key)) {
                return aux.getData();
            }
            aux = aux.getNext();
        }
        throw new Error("(!) Data not found (!)");
    }

    public void ShowAll() {
        HashNode<K, D> aux = first;
        while (aux != null) {
            System.out.println(aux.getKey() + " " + aux.getData());
            aux = aux.getNext();
        }
    }
}

// --------------------------------------------------------

// ==== Hash table ====

class HashTable<K, D> {
    private int size;
    private final HashList<K, D>[] buckets;

    public HashTable(int size) {
        this.size = size;
        buckets = (HashList<K, D> []) new HashList[size];
        for (int i = 0; i < size; i++) {
            buckets[i] = new HashList<>();
        }
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % size;
    }

    public void insert(K key, D data) {
        int index = hash(key);
        buckets[index].Insert(key, data);
    }

    public D search(K key) {
        int index = hash(key);
        return buckets[index].Search(key);
    }

    public void showAll() {
        for (int i = 0; i < size; i++) {
            System.out.println("Bucket " + i + " :");
            buckets[i].ShowAll();
        }
    }
}

// ======================================================================
