package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        while (!in.isEmpty()) {
            out.pushIn(in.pop());
        }
        return out.pop();
    }

    public void pushIn(T value) {
        in.pushIn(value);
    }
}