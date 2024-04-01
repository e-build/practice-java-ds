package classic;

/*
- 각 노드의 왼쪽 자식 노드는 노드의 값보다 작아야 함.
- 각 노드의 오른쪽 자식 노드는 노드의 값보다 커야 함.
- 왼쪽 및 오른쪽 자식 노드 모두 이진 검색 트리여야 함.
 */
public class BinarySearchTree<E extends Comparable> {

    private Node<E> root;
    private int size;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(E e) {
        root = addRecursively(root, e);
        size++;
    }

    private Node<E> addRecursively(Node<E> current, E e) {
        if (current == null) {
            return new Node<>(e, null, null);
        }
        int comparTo = current.value.compareTo(e);
        if (comparTo > 0) {
            current.left = addRecursively(current.left, e);
        } else if (comparTo < 0){
            current.right = addRecursively(current.right, e);
        } else {
            return current;
        }
        return current;
    }

    private static class Node<E> {
        private E value;
        private Node<E> left;
        private Node<E> right;

        private Node(E value, Node<E> left, Node<E> right) {
            this.value = value;
            this.left = left;
            this.right = right;
        }
    }
}
