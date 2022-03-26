package ru.job4j.collection.list;
public class Node<T> {

    private T element;

    private Node<T> next;

    private Node<T> previous;

    public Node(T element, Node<T> next, Node<T> previous) {
        this.element = element;
        this.next = next;
        this.previous = previous;
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public Node<T> getPrevious() {
        return previous;
    }

    public void setPrevious(Node<T> previous) {
        this.previous = previous;
    }
}
