
// ==== Node class ====

#include <iostream>
#include <stdexcept>

using namespace std;

template <typename T>

class Node {
    T data;
    Node<T> *next;
    Node<T> *prev;

public:
    Node(T data) : data(data), next(nullptr), prev(nullptr) {}

// ==== Getters and Setters ====

    T getData() {
        return data;
    }
    void setData(T data) {
        (*this).data = data;
    }

    Node *getNext() {
        return next;
    }
    Node *getPrev() {
        return prev;
    }

    void setNext(Node *node) {
        next = node;
    }
    void setPrev(Node *node) {
        prev = node;
    }

};

// ==== Linked List class ====

template <typename T>

class LinkedList {
private:
    Node <T> *first;
    Node <T> *last;
    int size;

public:
    LinkedList() : first(nullptr), last(nullptr), size(0) {}
    void insertFirst(T data);
    void insertLast(T data);
    void remove(T data);
    void removeFirst();
    void removeLast();
    void clear();
    void showAll() const;
    bool contains(T data) const;
    T getFirst() const;
    T getLast() const;
    int getSize() const;
    bool isEmpty() const;
};

template <typename T>
void LinkedList<T>::insertFirst(T data) {
    Node<T> *newNode = new Node<T>(data);

    if (first == nullptr) {
        first = last = newNode;
    } else {
        newNode->setNext(first);
        first->setPrev(newNode);
        first = newNode;
    }
    size++;
}

template <typename T>

void LinkedList<T>::insertLast(T data) {
    Node<T> *newNode = new Node<T>(data);
    if (first == nullptr) {
        first = last = newNode;
    } else {
        last->setNext(newNode);
        newNode->setPrev(last);
        last = newNode;
    }
    size++;
}

template <typename T>

void LinkedList<T>::remove(T data) {

    Node<T> *aux = first;

    while (aux != nullptr) {
        if (aux->getData() == data) {
            if (aux == first) {
                removeFirst();
                return;
            }
            if (aux == last) {
                removeLast();
                return;
            } else {
                Node<T> *prev = aux->getPrev();
                Node<T> *next = aux->getNext();

                prev->setNext(next);
                next->setPrev(prev);

                delete aux;
                size--;
                return;
            }
        }
        aux = aux->getNext();
    }
    throw out_of_range("(!) Data not found (!)");
}

template <typename T>

void LinkedList<T>::removeFirst() {
    if (first == nullptr) {
        return;
    }
    if (first == last) {
        delete first;
        first = last = nullptr;
    } else {
        Node<T> *aux = first;
        first = first->getNext();
        first->setPrev(nullptr);
        delete aux;
    }
    size--;
}

template <typename T>
void LinkedList<T>::removeLast() {
    if (first == nullptr) {
        return;
    }
    if (first == last) {
        delete first;
        first = last = nullptr;
    } else {
        Node<T> *aux = last;
        last = last->getPrev();
        last->setNext(nullptr);
        delete aux;
    }
    size--;
}

template <typename T>
void LinkedList<T>::showAll() const {

    Node<T> *aux = first;

    while (aux != nullptr) {
        cout << aux->getData() << "\n";
        aux = aux->getNext();
    }
}

template <typename T>
void LinkedList<T>::clear() {

    Node<T> *aux = first;

    while (aux != nullptr) {
        Node<T> *next = aux->getNext();
        delete aux;
        aux = next;
    }
    first = last = nullptr;
    size = 0;
}

template <typename T>
bool LinkedList<T>::contains(T data) const {
    Node<T> *aux = first;

    while (aux != nullptr) {
        if (aux->getData() == data) {
            return true;
        }
        aux = aux->getNext();
    }
    return false;
}

template <typename T>
T LinkedList<T>::getFirst() const {
    if (first == nullptr) {
        throw std::out_of_range("(!) Empty List (!)");
    }
    return first->getData();
}

template <typename T>
T LinkedList<T>::getLast() const {
    if (last == nullptr) {
        throw std::out_of_range("(!) Empty List (!)");
    }
    return last->getData();
}

template <typename T>
int LinkedList<T>::getSize() const {
    return size;
}

template <typename T>
bool LinkedList<T>::isEmpty() const {
    return size == 0;
}