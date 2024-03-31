package classic;

import java.util.EmptyStackException;

public class Stack<E> {

    private int size;
    private int capacity;
    private E[] elements;

    public Stack() {
        this.size = 0;
        this.elements = createElements(0);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(E e){
        ensureCapacity();
        elements[size -1] = e;
        size++;
    }

    private void ensureCapacity() {
        if (size < elements.length) {
            return;
        }
        initElements();
    }

    private void initElements() {
        E[] newElements = createElements(capacity+5);
        if (size >= 0) {
            System.arraycopy(elements, 0, newElements, 0, size);
        }
        elements = newElements;
    }
    public E peek() {
        return elements[elements.length-1];
    }

    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E element = elements[size - 1];
        elements[size - 1] = null;
        size--;
        return element;
    }

    private E[] createElements(int length) {
        return (E[]) new Object[length];
    }
}
