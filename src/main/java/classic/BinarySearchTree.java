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
        } else if (comparTo < 0) {
            current.right = addRecursively(current.right, e);
        } else {
            return current;
        }
        return current;
    }

    public boolean contains(E e) {
        if (root == null) {
            return false;
        }
        Node<E> node = findRecursively(root, e);
        if (node == null) {
            return false;
        }
        return true;
    }

    private Node<E> findRecursively(Node<E> current, E e) {
        if (current == null) {
            return null;
        }
        if (current.value.equals(e)) {
            return current;
        }
        int compareTo = current.value.compareTo(e);
        if (compareTo > 0) {
            return findRecursively(current.left, e);
        }
        return findRecursively(current.right, e);
    }

    public void delete(E e) {
        if (root.value.equals(e)) {
            root = nextNode(root, e);
        }
        reArchitecture(root, e);
        size--;
    }

    private void reArchitecture(Node<E> parent, E e) {
        if (parent.left.value.equals(e)) {
            parent.left = nextNode(parent.left, e);
            return;
        }
        if (parent.right.value.equals(e)) {
            parent.right = nextNode(parent.right, e);
            return;
        }
        final int compareTo = parent.value.compareTo(e);
        if (compareTo > 0) {
            reArchitecture(parent.left, e);
            return;
        }
        reArchitecture(parent.right, e);
    }

    private Node<E> nextNode(Node<E> deleteTarget, E e) {
        if (deleteTarget.left == null && deleteTarget.right == null) {
            return null;
        }
        if (deleteTarget.left != null && deleteTarget.right == null) {
            return deleteTarget.left;
        }
        if (deleteTarget.left == null && deleteTarget.right != null) {
            return deleteTarget.right;
        }
        Node<E> successor = findMinValueFromRight(deleteTarget.right);
        successor.left = deleteTarget.left;
        successor.right = deleteTarget.right;
        return successor;
    }

    private Node<E> findMinValueFromRight(Node<E> right) {
        if (right.left == null) {
            return right;
        }
        Node<E> successorParent = right;
        Node<E> minNode = right.left;
        while (minNode.left != null) {
            successorParent = minNode;
            minNode = minNode.left;
        }
        successorParent.left = null;
        return minNode;
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
