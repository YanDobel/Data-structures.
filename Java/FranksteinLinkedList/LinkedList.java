package LinkedList;

import java.security.*;
import java.util.*;
import java.util.function.*;
import java.util.stream.*;

public class LinkedList<T> implements Iterable<T>{
    private Node<T> first;
    private Node<T> last;
    private int size;

    public LinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }

    public void push(T data) {
        insertLast(data);
    }

    public T pop() {
        return removeLast();
    }

    public T peak() {
       return getLast();
    }

    public T getLast() {
        if (isEmpty()) {
            throw new NoSuchElementException("(!) Empty Stack (!)");
        }
        return last.getData();
    }

    public T getFirst() {
        if (isEmpty()) {
            throw new NoSuchElementException("(!) Empty List (!)");
        }
        return first.getData();
    }


    public void add(T data) {
        insertLast(data);
    }

    public void add(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("(!) Index: " + index + "Size :" + this.size + "(!)");
        }
        if (index == 0) {
            insertFirst(data);
            return;
        }
        Node<T> node = new Node<>(data);
        Node<T> target;

        if (index < size / 2) {
            target = first;

            for (int i = 0; i < index; i++) {
                target = target.getNext();
            }
        } else {
            target = last;

            for (int i = size - 1; i > index; i--) {
                target = target.getPrev();
            }
        }
        Node<T> prev = target.getPrev();
        prev.setNext(node);
        node.setPrev(prev);

        node.setNext(target);
        target.setPrev(node);

        size++;
    }
    public void insertFirst(T data) {
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

    public void insertLast(T data) {
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

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("(!) Index: " + index + "Size :" + this.size + "(!)");
        }

        if (index == 0) return removeFirst();
        if (index == size - 1) return removeLast();

        Node<T> target;
        if (index < size / 2) {
            target = first;

            for (int i = 0; i < index; i++) {
                target = target.getNext();
            }
        } else {
            target = last;

            for (int j = size - 1; j > index; j--) {
                target = target.getPrev();
            }

        }
        return unlink(target);
    }

    public T remove(T data) {

        Node<T> aux = first;

        while (aux != null) {
            if (Objects.equals(aux.getData(), data)) {
                return unlink(aux);
            }
            aux = aux.getNext();
        }
        throw new NoSuchElementException("(!) Element not found (!)");
    }

    public T removeFirst() {
        return unlink(first);
    }

    public T removeLast() {
        return unlink(last);
    }

    private T unlink(Node<T> node) {
        if (isEmpty() || node == null) {
            throw new NoSuchElementException("(!) Empty List (!)");
        }

        T data = node.getData();
        Node<T> next = node.getNext();
        Node<T> prev = node.getPrev();

        if (prev == null) {
            first = next;
        } else {
            prev.setNext(next);
        }

        if (next == null) {
            last = prev;
        } else {
            next.setPrev(prev);
        }
        size--;
        return data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return this.size;
    }

    // ===== MÉTODOS ITERABLE =====

    @Override
    public void forEach(Consumer<? super T> action) {
        Node<T> aux = first;
        while (aux != null) {
            action.accept(aux.getData());
            aux = aux.getNext();
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> aux = first;

            @Override
            public boolean hasNext() {
                return aux != null;
            }
            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T data = aux.getData();
                aux = aux.getNext();
                return data;
            }
        };
    }

    // ===== METODOS FUNCIONAIS =====

    public LinkedList<T> filter(Predicate<T> test) {
        LinkedList<T> listAux = new LinkedList<T>();
        Node<T> nodeAux = first;
        while (nodeAux != null) {
            if (test.test(nodeAux.getData())) {
                listAux.insertLast(nodeAux.getData());
            }
            nodeAux = nodeAux.getNext();
        }
        return listAux;
    }

    public <R> LinkedList<R> map(Function<T, R> transformacao) {
        LinkedList<R> newList = new LinkedList<R>();

        Node<T> aux = first;
        while (aux != null) {
            newList.insertLast(transformacao.apply(aux.getData()));
            aux = aux.getNext();
        }
        return newList;
    }


    public Optional<T> find (Predicate<T> test) {
        return this.stream().filter(test).findFirst();
    }

    public Stream<T> stream() {
        return StreamSupport.stream(this.spliterator(), false);
    }

    @Override
    public String toString() {
        if (size == 0) return "[]";

        StringBuilder sb = new StringBuilder("[");
        Node<T> aux = this.first;

        while (aux != null) {
            sb.append(aux.getData());

            aux = aux.getNext();

            if (aux != null) sb.append(", ");
        }
        return sb.append("]").toString();
    }

}

