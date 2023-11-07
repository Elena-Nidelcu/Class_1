package stack;

import java.util.EmptyStackException;

public class LinkedListStack<T> implements Stack<T> {
    private Node<T> top;
    private int size;
    private static final int CAPACITY = 5;

    private class Node<T> {
        T data;
        Node<T> next;
    }

    public LinkedListStack() {
        top = null;
        size = 0;
    }

    @Override
    public void push(T item) {
        if (size == CAPACITY) {
            throw new IllegalStateException("Stack is full");
        }
        Node<T> newNode = new Node<>();
        newNode.data = item;
        newNode.next = top;
        top = newNode;
        size++;
    }

    @Override
    public T pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        T item = top.data;
        top = top.next;
        size--;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return top.data;
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
