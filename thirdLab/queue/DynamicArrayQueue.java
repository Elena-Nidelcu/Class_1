package queue;

import java.util.NoSuchElementException;

public class DynamicArrayQueue<T> implements Queue<T> {
    private Object[] array;
    private int size;
    private int front;
    private int rear;
    private static final int CAPACITY = 5;

    public DynamicArrayQueue() {
        array = new Object[CAPACITY];
        size = 0;
        front = 0;
        rear = -1;
    }

    @Override
    public void enqueue(T item) {
        if (size == CAPACITY) {
            throw new IllegalStateException("Queue is full");
        }
        rear = (rear + 1) % CAPACITY;
        array[rear] = item;
        size++;
    }

    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        T item = (T) array[front];
        array[front] = null; // Help with garbage collection
        front = (front + 1) % CAPACITY;
        size--;
        return item;
    }

    @Override
    public T peek() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        return (T) array[front];
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
