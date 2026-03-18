package AVL;

import java.security.*;
import java.util.*;
import java.util.concurrent.atomic.*;
import java.util.function.*;
import java.util.stream.*;
import LinkedList.LinkedList;

public class AVL <T extends Comparable<T>> implements Iterable<T> {
    private Node<T> root;

    public AVL() {
        this.root = null;
    }

    public void add(T data) {
        root = add(root, data);
    }

    private Node<T> add(Node<T> current, T data) {
        if (current == null) return new Node<>(data);

        int cmp = data.compareTo(current.getData());
        if (cmp < 0) {
            current.setLeft(add(current.getLeft(), data));
        } else if (cmp > 0) {
            current.setRight(add(current.getRight(), data));
        } else {
            //current.setCount(current.getCount() + 1);
            updateMetadata(current);
            return balance(current);
        }
        updateMetadata(current);
        return balance(current);
    }

    public boolean contains(T data) {
        if (this.root == null) {
            return false;
        }
        return contains(data, this.root);
    }

    private boolean contains(T data, Node<T> root) {
        if (data == root.getData()) {
            return true;
        }
        int cmp = data.compareTo(root.getData());

        if (cmp < 0) {
            if (root.getLeft() == null) return false;
            return contains(data, root.getLeft());
        }
        if (cmp > 0) {
            if (root.getRight() == null) return false;
            return contains(data, root.getRight());
        }
        return false;
    }

    private T search(T data, Node<T> root) {
        if (data == root.getData()) return root.getData();

        int cmp = data.compareTo(root.getData());

        if (cmp < 0) {
            if (root.getLeft() == null) return null;
            return search(data, root.getLeft());
        }
        if (cmp > 0) {
            if (root.getRight() == null) return null;
            return search(data, root.getRight());
        }
        return null;
    }

    private T searchIndex(Node<T> node, int index) {
        int leftSize = getSize(node.getLeft());

        if (index < leftSize) {
            return searchIndex(node.getLeft(), index);
        } else if (index < leftSize + node.getCount()) {
            return node.getData();
        }
        return searchIndex(node.getRight(),
                index - leftSize - node.getCount());
    }

    public T get(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("(!) Index out of limit (!)");
        }
        return searchIndex(root, index);
    }

    public T get(T data) {
        if (data == null) {
            throw new InvalidParameterException("(!) The parameter can't be null (!)");
        }
        T aux = search(data, root);
        if (aux == null) {
            throw new NoSuchElementException("(!) Data not found (!)");
        }
        return aux;
    }

    public int size() {
        return (root == null) ? 0 : getSize(root);
    }

    public int nodeCount() {
        return nodeCount(root);
    }

    private int nodeCount(Node<T> node) {
        if (node == null) return 0;
        return 1 + nodeCount(node.getLeft()) + nodeCount(node.getRight());
    }

    public T remove(T data) {
        AtomicReference<T> removedData = new AtomicReference<>();
        root = remove(root, data, removedData);

        if (removedData.get() == null) {
            throw new NoSuchElementException("(!) Element not found (!)");
        }
        return removedData.get();
    }

    private Node<T> remove(Node<T> current, T data, AtomicReference<T> removed) {
        if (current == null) return null;

        int cmp = data.compareTo(current.getData());
        if (cmp < 0) {
            current.setLeft(remove(current.getLeft(), data, removed));
        } else if (cmp > 0) {
            current.setRight(remove(current.getRight(), data, removed));
        } else {
            removed.set(current.getData());

            if (current.getCount() > 1) {
                current.setCount(current.getCount() - 1);
                updateMetadata(current);
                return balance(current);
            }
            if (current.getLeft() == null) return current.getRight();
            if (current.getRight() == null) return current.getLeft();

            T successorData = minVal(current.getRight());
            current.setData(successorData);
            current.setRight(
                    remove(current.getRight(),
                            successorData, new AtomicReference<>()
                    )
            );
        }
        updateMetadata(current);
        return balance(current);
    }

    public int getHeight(Node<T> node) {
        return (node == null) ? -1 : node.getHeight();
    }

    public int getSize(Node<T> node) {
        return (node == null) ? 0 : node.getSize();
    }

    public int uniqueSize() {
        return countNodes(root);
    }

    private int countNodes(Node<T> node) {
        if (node == null) return 0;
        return 1 + countNodes(node.getLeft()) + countNodes(node.getRight());
    }

    private void updateMetadata(Node<T> node) {
        if (node == null) return;

        node.setHeight(1 + Math.max(
                        getHeight(node.getLeft()),
                        getHeight(node.getRight())
                )
        );
        node.setSize(
                node.getCount()
                        + getSize(node.getLeft())
                        + getSize(node.getRight())
        );
    }

    public int indexOf(T data) {
        return indexOf(root, data);
    }

    private int indexOf(Node<T> node, T data) {
        if (node == null) return -1;

        int cmp = data.compareTo(node.getData());

        if (cmp == 0) {
            return getSize(node.getLeft());
        } else if (cmp < 0) {
            return indexOf(node.getLeft(), data);
        } else {
            int rightRank = indexOf(node.getRight(), data);

            return (rightRank == -1) ? -1 :
                    getSize(node.getLeft()) + node.getCount() + rightRank;
        }
    }

    private T minVal(Node<T> root) {
        if (root.getLeft() == null) {
            return root.getData();
        }
        return minVal(root.getLeft());
    }

    private int getBalanceFactor(Node<T> node) {
        return (node == null) ? 0 :
                getHeight(node.getLeft()) - getHeight(node.getRight());
    }

    private Node<T> rotateRight(Node<T> y) {
        Node<T> x = y.getLeft();
        Node<T> t2 = x.getRight();

        x.setRight(y);
        y.setLeft(t2);

        updateMetadata(y);
        updateMetadata(x);
        return x;
    }

    private Node<T> rotateLeft(Node<T> x) {
        Node<T> y = x.getRight();
        Node<T> t2 = y.getLeft();

        y.setLeft(x);
        x.setRight(t2);

        updateMetadata(x);
        updateMetadata(y);
        return y;
    }

    private Node<T> balance(Node<T> node) {
        int fb = getBalanceFactor(node);

        if (fb > 1) {
            if (getBalanceFactor(node.getLeft()) < 0) {
                node.setLeft(rotateLeft(node.getLeft()));
            }
            return rotateRight(node);
        }
        if (fb < -1) {
            if (getBalanceFactor(node.getRight()) > 0) {
                node.setRight(rotateRight(node.getRight()));
            }
            return rotateLeft(node);
        }
        return node;
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    public void clear() {
        this.root = null;
    }

    // ========== FUNCTIONAL METHODS ==============

    public void forEach(Consumer<? super T> action) {
        forEach(root, action);
    }

    private void forEach(Node<T> node, Consumer<? super T> action) {
        if (node == null) return;

        forEach(node.getLeft(), action);

        for (int i = 0; i < node.getCount(); i++) {
            action.accept(node.getData());
        }
        forEach(node.getRight(), action);
    }

    private void forEachInRange(T min, T max, Consumer<? super T> action) {
        forEachInRange(root, min, max, action);
    }

    private void forEachInRange(Node<T> node, T min, T max, Consumer<? super T> action) {
        if (node == null) return;

        int cmpMin = min.compareTo(node.getData());
        int cmpMax = max.compareTo(node.getData());

        if (cmpMin < 0) forEachInRange(node.getLeft(), min, max, action);

        if (cmpMin <= 0 && cmpMax >= 0) {
            for (int i = 0; i < node.getCount(); i++) {
                action.accept(node.getData());
            }
        }
        if (cmpMax > 0) forEachInRange(node.getRight(), min, max, action);
    }

    public void filter(Predicate<T> condition, Consumer<? super T> action) {
        forEach(root, nodeData -> {
            if (condition.test(nodeData)) action.accept(nodeData);
        });
    }

    public LinkedList<T> inOrderLinkedList() {
        LinkedList<T> list = new LinkedList<T>();
        for (T element : this) {
            list.add(element);
        }
        return list;
    }

    public List<T> inOrder() {
        List<T> list = new ArrayList<>(size());
        for (T element : this) {
            list.add(element);
        }
        return list;
    }
    @Override
    public Iterator<T> iterator() {
        return new AVLIterator(root);
    }

    public List<T> preOrder() {
        List<T> list = new ArrayList<>(size());
        for (T element : preOrderIterator()) {
            list.add(element);
        }
        return list;
    }

    private Iterable<T> preOrderIterator() {
        return () -> new PreOrderIterator(root);
    }

    public List<T> postOrder() {
        List<T> list = new ArrayList<>(size());
        for (T element : postOrderIterator()) {
            list.add(element);
        }
        return list;
    }

    private Iterable<T> postOrderIterator() {
        return () -> new PostOrderIterator(root);
    }

    @Override
    public String toString() {
        return inOrder().toString();
    }

    // ============== ITERATOR CLASSES ==============

    private class AVLIterator implements Iterator<T> {
        private final Deque<Node<T>> stack = new ArrayDeque<>();
        private Node<T> current = null;
        private int count = 0;

        public AVLIterator(Node<T> root) {
            pushLeft(root);
        }

        public void pushLeft(Node<T> node) {
            while (node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }

        public boolean hasNext() {
            return !stack.isEmpty();
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("(!) Empty tree (!)");
            }
            if (current == null || count >= current.getCount()) {
                current = stack.pop();
                count = 0;
                if (current.getRight() != null) {
                    pushLeft(current.getRight());
                }
            }
            count++;
            return current.getData();
        }
    }

    private class PreOrderIterator implements Iterator<T> {
        private final Deque<Node<T>> stack = new ArrayDeque<>();
        private Node<T> current = null;
        private int count = 0;

        public PreOrderIterator(Node<T> root) {
            if (root != null) stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty() ||
                    (current != null && count < current.getCount());
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("(!) Empty tree (!)");
            }
            if (current == null || count >= current.getCount()) {
                current = stack.pop();
                count = 0;
                if (current.getRight() != null) stack.push(current.getRight());
                if (current.getLeft() != null) stack.push(current.getLeft());
            }
            count++;
            return current.getData();
        }
    }

    private class PostOrderIterator implements Iterator<T> {
        private final Deque<T> outputStack = new ArrayDeque<>();

        public PostOrderIterator(Node<T> root) {
            if (root == null) return;

            Deque<Node<T>> traverseStack = new ArrayDeque<>();
            traverseStack.push(root);

            while (!traverseStack.isEmpty()) {
                Node<T> node = traverseStack.pop();

                for (int i = 0; i < node.getCount(); i++) {
                    outputStack.push(node.getData());
                }

                if (node.getLeft() != null) {
                    traverseStack.push(node.getLeft());
                }
                if (node.getRight() != null) {
                    traverseStack.push(node.getRight());
                }
            }
        }

        @Override
        public boolean hasNext() {
            return !outputStack.isEmpty();
        }
        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException("(!) Empty tree (!)");
            }
            return outputStack.pop();
        }
    }
}