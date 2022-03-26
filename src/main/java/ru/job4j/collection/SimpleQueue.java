package ru.job4j.collection;

public class SimpleQueue<T> {
    private final SimpleStack<T> in = new SimpleStack<>();
    private final SimpleStack<T> out = new SimpleStack<>();

    public T poll() {
        while (!out.isEmpty()) {
            in.push(out.pop());
        }
        T result = in.pop();
        while (!in.isEmpty()) {
            out.push(in.pop());
        }
        return result;
    }

    public void push(T value) {
        out.push(value);
    }
}