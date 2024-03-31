package classic.linear;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedList<T> implements Iterable<T> {

    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void addFirst(T value) {
        if (head == null) {
            Node<T> newNode = Node.of(null, value);
            head = newNode;
            tail = newNode;
            return;
        }
        head = Node.of(head.copy(), value);
    }

    public void addLast(T value) {
        Node<T> newNode = Node.of(null, value);
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
    }

    public int size() {
        return miningDepth(0, head);
    }

    public boolean contains(T value) {
        return searchRecursive(head, value);
    }

    private boolean searchRecursive(Node<T> node, T value) {
        if (node == null) {
            return false;
        }
        if (node.value.equals(value)) {
            return true;
        }
        return searchRecursive(node.next, value);
    }

    private int miningDepth(int count, Node<T> node) {
        if (node == null) {
            return count;
        }
        if (node.next == null) {
            return count + 1;
        }
        return miningDepth(count + 1, node.next);
    }

    public T getFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.value;
    }

    public T getLast() {
        if (tail == null) {
            throw new NoSuchElementException();
        }
        return tail.value;
    }

    public void removeFirst() {
        if (head == null) {
            return;
        }
        head = head.next;
        if (head == null) {
            tail = null;
        }
    }

    public void removeLast() {
        if (tail == null) {
            return;
        }
        Node<T> prevTail = head;
        for (int i = 0; i < size() - 2; i++) {
            prevTail = prevTail.next;
        }
        tail = prevTail;
        prevTail.next = null;
    }

    private static final class Node<T> {
        private Node<T> next;
        private final T value;

        private Node(Node<T> next, T value) {
            this.next = next;
            this.value = value;
        }

        private static <T> Node<T> of(Node<T> next, T value) {
            return new Node<>(next, value);
        }

        public Node<T> copy() {
            return Node.of(next, value);
        }

    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T returnValue = current.value;
            current = current.next;
            return returnValue;
        }
    }
}
