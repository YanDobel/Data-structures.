package AVL;

public class Node <T extends Comparable<T>> {

    private Node<T> left, right;
    private T data;
    private int height;
    private int size;
    private int count;

    public Node(T data) {
        this.left = null;
        this.right = null;
        this.data = data;
        this.height = 0;
        this.count = 1;
        this.size = 1;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getSize() {
        return this.size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Node<T> getLeft() {
        return left;
    }

    public void setLeft(Node<T> left) {
        this.left = left;
    }

    public Node<T> getRight() {
        return right;
    }

    public void setRight(Node<T> right) {
        this.right = right;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public T getData() {
        return this.data;
    }

    public void setData(T other) {
        this.data = other;
    }
}