package stack;

public interface Stack<T> {
    void push(T item);    // Add an element to the top of the stack
    T pop();              // Remove and return the top element from the stack
    T peek();             // Return the top element without removing it
    boolean isEmpty();    // Check if the stack is empty
    int size();           // Return the number of elements in the stack
}
