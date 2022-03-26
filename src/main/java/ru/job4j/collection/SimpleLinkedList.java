package ru.job4j.collection;

import ru.job4j.collection.list.List;
import ru.job4j.collection.list.Node;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleLinkedList<E> implements List<E> {

    private Node<E> last = null;

    private Node<E> first = null;

    private int modCount = 0;

    private int size = 0;

    @Override
    public void add(E value) {
        if (last == null) {
            Node<E> nodeOne = new Node<>(value, null, null);
            this.last = nodeOne;
            this.first = nodeOne;
            modCount++;
            size++;
            return;
        }
        Node<E> newNode = new Node<>(value, null, last);
        last.setNext(newNode);
        this.last = newNode;
        modCount++;
        size++;
    }

    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> currentNode = first;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.getNext();
        }
        return currentNode.getElement();
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            Node<E> header = first;

            final int expectedModCount = modCount;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return header != null;
            }

            @Override
            public E next() {
                if (!iterator().hasNext()) {
                    throw new NoSuchElementException();
                }
                E rsl = header.getElement();
                header = header.getNext();
                return rsl;
            }
        };
    }
}
