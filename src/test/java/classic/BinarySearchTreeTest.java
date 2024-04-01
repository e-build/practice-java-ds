package classic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

class BinarySearchTreeTest {

    private static class Foo implements Comparable<Foo> {
        private final String name;
        private final int age;

        public Foo(String name, int age) {
            this.name = name;
            this.age = age;
        }

        /*
        현재 객체가 target 보다 작으면 음수, 같으면 0, 크면 양수를 반환
         */
        @Override
        public int compareTo(Foo target) {
            int nameCompare = this.name.compareTo(target.name);
            if (nameCompare != 0) {
                return nameCompare;
            }
            return Integer.compare(this.age, target.age);
        }
    }

    private BinarySearchTree<Integer> sut;

    @BeforeEach
    void setUp() {
        sut = new BinarySearchTree<>();
    }

    @Nested
    @DisplayName("Given: 새로운 이진 검색 트리")
    class WhenNewTree {

        @Test
        @DisplayName("Then: 트리는 비어 있어야 함")
        void isEmpty() {
            assertThat(sut.isEmpty()).isTrue();
        }
    }

    @Nested
    @DisplayName("Given: 값이 추가된 이진 검색 트리")
    class WhenValuesAdded {

        @BeforeEach
        void setup() {
            sut.add(5);
            sut.add(3);
            sut.add(7);
        }

        @Test
        @DisplayName("Then: 트리는 비어 있지 않아야 함")
        void isNotEmpty() {
            assertThat(sut.isEmpty()).isFalse();
        }

        @ParameterizedTest
        @ValueSource(ints = {3, 7, 5})
        @DisplayName("And: 특정 값이 트리에 존재해야 함")
        void containsValue(int value) {
            assertThat(sut.contains(value)).isTrue();
        }

        @ParameterizedTest
        @ValueSource(ints = {4, 9, 10})
        @DisplayName("And: 특정 값이 트리에 존재하지 않아야 함")
        void containsNotValue(int value) {
            assertThat(sut.contains(value)).isFalse();
        }
    }


    @Nested
    @DisplayName("Given: 깊이가 5인 BST 에서")
    class f {
        private BinarySearchTree<Integer> bst;

        @BeforeEach
        void setUp() {
            bst = new BinarySearchTree<>();
            // 주어진 데이터로 트리 구성
            int[] nodes = {15, 10, 25, 5, 12, 20, 30, 3, 7, 22, 35, 1};
            for (int value : nodes) {
                bst.add(value);
            }
        }

        @Test
        @DisplayName("Then: 리프 노드 삭제")
        public void testDeleteLeafNode() {
            assertThat(bst.contains(1)).isTrue();
            bst.delete(1);
            assertThat(bst.contains(1)).isFalse();

            assertThat(bst.contains(7)).isTrue();
            bst.delete(7);
            assertThat(bst.contains(7)).isFalse();
        }

        @Test
        public void testDeleteNodeWithOneChild() {
            assertThat(bst.contains(30)).isTrue();
            bst.delete(30);
            assertThat(bst.contains(30)).isFalse();

            assertThat(bst.contains(35)).isTrue();
        }

        @Test
        public void testDeleteNodeWithTwoChildren() {
            assertThat(bst.contains(10)).isTrue();
            bst.delete(10);
            assertThat(bst.contains(10)).isFalse();

            assertThat(bst.contains(5)).isTrue();
            assertThat(bst.contains(12)).isTrue();
        }
    }


//
//    @Nested
//    @DisplayName("Given: 값이 중위 순회로 방문된 이진 검색 트리")
//    class WhenTraversedInOrder {
//
//        @BeforeEach
//        void setup() {
//            sut.add(5);
//            sut.add(3);
//            sut.add(7);
//            sut.add(2);
//            sut.add(4);
//        }
//
//        @Test
//        @DisplayName("Then: 순회 결과는 정렬된 순서로 반환되어야 함")
//        void traverseInOrder() {
//            assertThat(sut.traverseInOrder()).containsExactly(2, 3, 4, 5, 7);
//        }
//    }
}
