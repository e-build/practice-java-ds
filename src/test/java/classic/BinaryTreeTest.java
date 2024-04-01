package classic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class BinaryTreeTest {

    private BinaryTree<Integer> sut;

    @BeforeEach
    void setUp() {
        sut = new BinaryTree<>();
    }

    @Nested
    @DisplayName("Given: 새로운 이진 트리")
    class WhenNewTree {

        @Test
        @DisplayName("Then: 트리는 비어 있어야 함")
        void isEmpty() {
            assertThat(sut.isEmpty()).isTrue();
        }
    }

//    @Nested
//    @DisplayName("Given: 노드가 추가된 이진 트리")
//    class WhenNodesAdded {
//
//        @BeforeEach
//        void setup() {
//            sut.add(5);
//            sut.add(3);
//            sut.add(7);
//        }
//
//        @Test
//        @DisplayName("Then: 트리는 비어 있지 않아야 함")
//        void isNotEmpty() {
//            assertThat(sut.isEmpty()).isFalse();
//        }
//
//        @Nested
//        @DisplayName("And: 전위 순회")
//        class AndPreOrderTraversal {
//
//            @Test
//            @DisplayName("Then: 순회 결과는 5 -> 3 -> 7 이어야 함")
//            void preOrder() {
//                assertThat(sut.preOrderTraversal()).containsExactly(5, 3, 7);
//            }
//        }
//
//        @Nested
//        @DisplayName("And: 중위 순회")
//        class AndInOrderTraversal {
//
//            @Test
//            @DisplayName("Then: 순회 결과는 3 -> 5 -> 7 이어야 함")
//            void inOrder() {
//                assertThat(sut.inOrderTraversal()).containsExactly(3, 5, 7);
//            }
//        }
//
//        @Nested
//        @DisplayName("And: 후위 순회")
//        class AndPostOrderTraversal {
//
//            @Test
//            @DisplayName("Then: 순회 결과는 3 -> 7 -> 5 이어야 함")
//            void postOrder() {
//                assertThat(sut.postOrderTraversal()).containsExactly(3, 7, 5);
//            }
//        }
//    }
//
//    @Nested
//    @DisplayName("Given: 특정 값을 가진 노드가 있는 이진 트리")
//    class WhenSearchingNode {
//
//        @BeforeEach
//        void setup() {
//            sut.add(5);
//            sut.add(3);
//            sut.add(7);
//        }
//
//        @Test
//        @DisplayName("Then: 해당 값이 있는 노드를 찾을 수 있어야 함")
//        void findNode() {
//            assertThat(sut.find(3)).isNotNull();
//            assertThat(sut.find(7)).isNotNull();
//        }
//
//        @Test
//        @DisplayName("Then: 없는 값을 찾으려고 하면 null 이어야 함")
//        void findNonExistingNode() {
//            assertThat(sut.find(10)).isNull();
//        }
//    }
}
