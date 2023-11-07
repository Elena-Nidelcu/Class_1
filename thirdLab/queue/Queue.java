package queue;

public interface Queue<T> {
    void enqueue(T item);    // Add an element to the back of the queue
    T dequeue();             // Remove and return the front element from the queue
    T peek();                // Return the front element without removing it
    boolean isEmpty();       // Check if the queue is empty
    int size();              // Return the number of elements in the queue
}
