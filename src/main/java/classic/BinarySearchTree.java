package classic;

/*
- 각 노드의 왼쪽 자식 노드는 노드의 값보다 작아야 함.
- 각 노드의 오른쪽 자식 노드는 노드의 값보다 커야 함.
- 왼쪽 및 오른쪽 자식 노드 모두 이진 검색 트리여야 함.
 */
public class BinarySearchTree<E extends Comparable<E>> {

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

    /*
    이진 탐색 트리(Binary Search Tree, BST)에서 노드를 삭제할 때, 삭제한 노드를 대체하기 위한 규칙은 삭제하려는 노드의 자식 노드의 수에 따라 달라집니다. 크게 세 가지 경우를 고려해야 함.

    1. 삭제하려는 노드가 자식 노드를 가지지 않는 경우 (잎 노드)
        - 가장 간단한 경우. 해당 노드를 단순히 제거하고, 해당 노드의 부모 노드에서 이 노드로의 연결을 끊음
    2. 삭제하려는 노드가 하나의 자식 노드만 가지는 경우
        - 삭제하려는 노드를 제거하고, 삭제한 노드의 부모 노드와 삭제한 노드의 자식 노드를 직접 연결
    3. 삭제하려는 노드가 두 개의 자식 노드를 가지는 경우
        - 이 경우가 가장 복잡합니다. 삭제하려는 노드를 대체할 노드를 찾아야 하는데, 일반적으로 두 가지 방법 중 하나를 사용
        - 후계자(successor) 찾기: 삭제하려는 노드의 오른쪽 서브트리에서 가장 작은 값을 가진 노드를 찾음. 이 노드를 후계자라고 하며, 이 노드는 삭제하려는 노드를 대체함. 후계자는 삭제하려는 노드보다 큰 값들 중 가장 작은 값이기 때문에, 이 위치로 이동해도 이진 탐색 트리의 조건을 만족
        - 전임자(predecessor) 찾기: 삭제하려는 노드의 왼쪽 서브트리에서 가장 큰 값을 가진 노드를 찾음. 이 노드를 전임자라고 하며, 이 노드는 삭제하려는 노드를 대체함. 전임자는 삭제하려는 노드보다 작은 값들 중 가장 큰 값이기 때문에, 이 위치로 이동해도 이진 탐색 트리의 조건을 만족
        - 후계자(또는 전임자)를 찾은 후, 이 노드를 삭제하려는 노드의 위치로 이동시키고, 원래 후계자(또는 전임자)의 위치에 있던 노드를 제거. 만약 후계자(또는 전임자)에게 자식 노드가 있다면, 이 자식 노드를 후계자(또는 전임자)의 원래 부모 노드와 연결해야 함. 이 과정은 이진 탐색 트리의 구조적인 특성을 유지하면서 노드를 안전하게 삭제할 수 있도록 해줌.
     */
    public void delete(E e) {
        root = deleteRecursively(root, e);
    }

    private Node<E> deleteRecursively(Node<E> current, E e) {
        if (current == null) {
            return null;
        }

        final int compare = current.value.compareTo(e);
        if (compare > 0) {
            current.left = deleteRecursively(current.left, e);
        } else if (compare < 0) {
            current.right = deleteRecursively(current.right, e);
        } else {
            if (current.left != null && current.right != null) {
                Node<E> successor = findMinValueFromRight(current.right);
                current.value = successor.value;
                current.right = deleteRecursively(current.right, successor.value);
            } else {
                current = (current.left != null) ? current.left : current.right;
            }
        }
        return current;
    }

    private Node<E> findMinValueFromRight(Node<E> node) {
        Node<E> current = node;
        while (current.left != null) {
            current = current.left;
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
