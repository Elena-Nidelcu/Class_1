package stack;

import java.util.EmptyStackException;

public class DynamicArrayStack<T> implements Stack<T> {
    private Object[] array;
    private int size;
    private static final int CAPACITY = 5;

    public DynamicArrayStack() {
        array = new Object[CAPACITY];
        size = 0;
    }

    @Override
    public void push(T item) {
        if (size == CAPACITY) {
            throw new IllegalStateException("Stack is full");
        }
        array[size++] = item;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T item = (T) array[--size];
        array[size] = null; // Help with garbage collection
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return (T) array[size - 1];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }
}
