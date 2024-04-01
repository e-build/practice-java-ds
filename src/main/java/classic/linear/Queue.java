package classic.linear;

import java.util.EmptyStackException;

public class Queue<E> {

    private Stack<E> inStack;
    private Stack<E> outStack;

    private int size;
    private int capacity;

    public Queue() {
        this.inStack = new Stack<>();
        this.outStack = new Stack<>();
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enqueue(E e) {
        inStack.push(e);
        size++;
    }

    public E dequeue() {
        shiftElements();
        size--;

        try {
            return outStack.pop();
        } catch (EmptyStackException e) {
            throw new IllegalStateException();
        }
    }

    private void shiftElements() {
        if (!outStack.isEmpty()) {
            return;
        }
        while (!inStack.isEmpty()) {
            outStack.push(inStack.pop());
        }
    }
}
