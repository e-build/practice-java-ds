package classic.linear;

import java.util.ListIterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
    }

    public void addFirst(T value) {
        Node<T> newNode = Node.of(null, head, value);
        if (head != null) {
            head.prev = newNode;
        } else {
            tail = newNode;
        }
        head = newNode;
    }

    public void addLast(T value) {
        Node<T> newNode = Node.of(tail, null, value);
        if (tail != null) {
            tail.next = newNode;
        } else {
            head = newNode;
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
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }
    }

    public void removeLast() {
        if (tail == null) {
            return;
        }
        tail = tail.prev;
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }
    }

    private static final class Node<T> {
        private Node<T> prev; // 이전 노드를 가리키는 포인터 추가
        private Node<T> next;
        private T value;

        private Node(Node<T> prev, Node<T> next, T value) {
            this.prev = prev;
            this.next = next;
            this.value = value;
        }

        // Node 생성 메소드에 prev 인자 추가
        private static <T> Node<T> of(Node<T> prev, Node<T> next, T value) {
            return new Node<>(prev, next, value);
        }

    }

    @Override
    public ListIterator<T> iterator() {
        return new DoublyLinkedListIterator();
    }

    private class DoublyLinkedListIterator implements ListIterator<T> {
        private Node<T> lastReturned = null;
        private Node<T> next = head;
        private int nextIndex = 0;
        private int size = size();

        @Override
        public boolean hasNext() {
            return nextIndex < size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastReturned = next;
            next = next.next;
            nextIndex++;
            return lastReturned.value;
        }

        @Override
        public boolean hasPrevious() {
            return nextIndex > 0;
        }

        @Override
        public T previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            next = (next == null) ? tail : next.prev;
            lastReturned = next;
            nextIndex--;
            return lastReturned.value;
        }

        @Override
        public int nextIndex() {
            return nextIndex;
        }

        @Override
        public int previousIndex() {
            return nextIndex - 1;
        }

        @Override
        public void remove() {
            if (lastReturned == null)
                throw new IllegalStateException();

            Node<T> nextNode = lastReturned.next;
            Node<T> prevNode = lastReturned.prev;

            if (prevNode != null) {
                prevNode.next = nextNode;
                lastReturned.prev = null;
            } else {
                head = nextNode;
            }

            if (nextNode != null) {
                nextNode.prev = prevNode;
                lastReturned.next = null;
            } else {
                tail = prevNode;
            }

            if (next == lastReturned)
                next = nextNode;
            else
                nextIndex--;

            lastReturned = null;
            size--;
        }

        @Override
        public void set(T e) {
            if (lastReturned == null)
                throw new IllegalStateException();
            lastReturned.value = e;
        }

        public void add(T e) {
            Node<T> prevNode = (next == null) ? tail : next.prev;
            Node<T> newNode = new Node<>(prevNode, next, e);

            if (prevNode == null) {
                head = newNode;
            } else {
                prevNode.next = newNode;
            }

            if (next == null) {
                tail = newNode;
            } else {
                next.prev = newNode;
            }

            size++;
            nextIndex++;
            lastReturned = null;
        }

    }
}
