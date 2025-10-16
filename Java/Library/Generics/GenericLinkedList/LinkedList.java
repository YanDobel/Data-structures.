
public class LinkedList<T> {
    private Node<T> first;
    private Node<T> last;
    private int size;

    public LinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void InsertFirst(T data) {
        Node<T> newNode = new Node<>(data);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            newNode.setNext(first);
            first.setPrev(newNode);
            first = newNode;
        }
        size++;
    }

    public void InsertLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (first == null) {
            first = newNode;
            last = newNode;
        } else {
            last.setNext(newNode);
            newNode.setPrev(last);
            last = newNode;
        }
        size++;
    }
    public void Remove(T data) {
        if (first == null) {
            throw new Error("(!) Empty List (!)");
        }
        Node<T> aux = first;

        while (aux != null) {
            if (aux.getData().equals(data)) {
                if (aux == first) {
                    RemoveFirst();
                    return;
                }
                if (aux == last) {
                    RemoveLast();
                    return;
                }
                Node<T> prev = aux.getPrev();
                Node<T> next = aux.getNext();
                prev.setNext(next);
                next.setNext(prev);
                size--;
                return;
            }
            aux = aux.getNext();
        }
        throw new Error("(!) Data not found (!)");
    }

    public void RemoveFirst() {
        if (first == null) {
            return;
        }
        if (first == last) {
            first = null;
            last = null;
        } else {
            first = first.getNext();
            first.setPrev(null);
        }
        size--;
    }

    public void RemoveLast() {
        if (first == null) {
            return;
        }
        if (first == last) {
            first = null;
            last = null;
        } else {
            last = last.getPrev();
            last.setNext(null);
        }
        size--;
    }

    public void showAll() {
        Node<T> aux = first;
        while (aux != null) {
            System.out.println(aux.getData());
            aux = aux.getNext();
        }
    }
    public void clear() {
        this.first = null;
        this.last = null;
        size = 0;
    }

    public boolean contains(T data) {
        Node<T> aux = first;
        while (aux != null) {
            if (aux.getData().equals(data)) {
                return true;
            }
            aux = aux.getNext();
        }
        return false;
    }

    public T getFirst() {
        if (this.first == null) {
            throw new Error("(!) Empty List (!)");
        }
        return first.getData();
    }
    public T getLast() {
        if (this.last == null) {
            throw new Error("(!) Empty List (!)");
        }
        return last.getData();
    }

    public int size() {
        return this.size;
    }

    public boolean isEmpty() {
        return this.first == null;
    }
}

// === Generic Node class ===

class Node<T> {
    private T data;
    private Node<T> next = null;
    private Node<T> prev = null;

    public Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Node<T> getNext() {
        return this.next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrev() {
        return this.prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }
}