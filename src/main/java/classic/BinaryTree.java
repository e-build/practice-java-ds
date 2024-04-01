package classic;

public class BinaryTree<E> {

    private Node<E> root;
    private int size;

    public BinaryTree() {
        this.root = new Node<>(null, null, null);
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
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
