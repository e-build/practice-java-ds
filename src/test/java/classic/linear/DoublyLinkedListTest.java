package classic.linear;

import classic.Foo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.ListIterator;

import static org.assertj.core.api.Assertions.assertThat;

class DoublyLinkedListTest {

    private final DoublyLinkedList<Foo> sut = new DoublyLinkedList<>();

    @Nested
    @DisplayName("Given: 크기 확인")
    class Context0 {
        @Test
        @DisplayName("Then: size()")
        void tc1() {
            assertThat(sut.size()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("Given: 원소 검색")
    class Context1 {

        @Test
        @DisplayName("Then: getFirst()")
        void tc1() {
            sut.addFirst(new Foo("foo1", 1));
            sut.addFirst(new Foo("foo2", 2));

            Foo first = sut.getFirst();
            assertThat(first.getName()).isEqualTo("foo2");
            assertThat(first.getAge()).isEqualTo(2);
        }

        @Test
        @DisplayName("Then: getLast()")
        void tc2() {
            sut.addFirst(new Foo("foo1", 1));
            sut.addFirst(new Foo("foo2", 2));

            Foo last = sut.getLast();
            assertThat(last.getName()).isEqualTo("foo1");
            assertThat(last.getAge()).isEqualTo(1);
        }

        @Test
        @DisplayName("Then: contains()")
        void tc3() {
            sut.addFirst(new Foo("foo1", 1));
            sut.addFirst(new Foo("foo2", 2));
            sut.addFirst(new Foo("foo3", 3));

            assertThat(sut.contains(new Foo("foo2", 2))).isTrue();
        }
    }

    @Nested
    @DisplayName("Given: 원소 추가")
    class Context2 {

        @Test
        @DisplayName("Then: addFirst()")
        void tc1() {
            sut.addFirst(new Foo("foo", 1));

            assertThat(sut.size()).isEqualTo(1);
        }

        @Test
        @DisplayName("Then: multi addFirst()")
        void tc2() {
            for (int i = 0; i < 100; i++) {
                sut.addFirst(new Foo("foo" + i, i));
            }

            assertThat(sut.size()).isEqualTo(100);
        }

        @Test
        @DisplayName("Then: addLast()")
        void tc3() {
            sut.addLast(new Foo("foo", 1));

            assertThat(sut.size()).isEqualTo(1);
        }

        @Test
        @DisplayName("Then: multi addLast()")
        void tc4() {
            for (int i = 0; i < 100; i++) {
                sut.addLast(new Foo("foo" + i, i));
            }

            assertThat(sut.size()).isEqualTo(100);
        }
    }

    @Nested
    @DisplayName("Given: 원소 제거")
    class Context3 {

        @Test
        @DisplayName("Then: removeFirst()")
        void tc1() {
            for (int i = 0; i < 100; i++) {
                sut.addFirst(new Foo("foo" + i, i));
            }

            sut.removeFirst();

            assertThat(sut.getFirst().getName()).isEqualTo("foo98");
        }

        @Test
        @DisplayName("Then: removeLast()")
        void tc2() {
            for (int i = 0; i < 100; i++) {
                sut.addFirst(new Foo("foo" + i, i));
            }

            sut.removeLast();

            assertThat(sut.getLast().getName()).isEqualTo("foo1");
        }
    }

    @Nested
    @DisplayName("Given: Iterator 구현")
    class Context4 {

        private DoublyLinkedList<String> list;


        @BeforeEach
        void setup(){
            list = new DoublyLinkedList<>();
        }

        @Test
        @DisplayName("Then: iterator().hasNext() return true")
        void tc1() {
            list.addFirst("foo1");
            list.addFirst("foo2");

            ListIterator<String> sut = list.iterator();

            assertThat(sut.hasNext()).isTrue();
        }

        @Test
        @DisplayName("Then: iterator().hasNext() return false")
        void tc2() {
            ListIterator<String> sut = list.iterator();
            while (sut.hasNext()) {
                sut.next();
            }

            assertThat(sut.hasNext()).isFalse();
        }

        @Test
        @DisplayName("Then: iterator().next()")
        void tc3() {
            list.addFirst("foo1");
            list.addFirst("foo2");

            ListIterator<String> sut = list.iterator();

            assertThat(sut.next()).isEqualTo("foo2");
            assertThat(sut.next()).isEqualTo("foo1");
        }

        @Test
        @DisplayName("양방향 반복 테스트")
        void tc4() {
            list.addLast("A");
            list.addLast("B");
            list.addLast("C");

            ListIterator<String> sut = list.iterator();

            assertThat(sut.hasNext()).isTrue();
            assertThat(sut.next()).isEqualTo("A");
            assertThat(sut.hasNext()).isTrue();
            assertThat(sut.next()).isEqualTo("B");
            assertThat(sut.hasNext()).isTrue();
            assertThat(sut.next()).isEqualTo("C");
            assertThat(sut.hasNext()).isFalse();

            assertThat(sut.hasPrevious()).isTrue();
            assertThat(sut.previous()).isEqualTo("C");
            assertThat(sut.hasPrevious()).isTrue();
            assertThat(sut.previous()).isEqualTo("B");
            assertThat(sut.hasPrevious()).isTrue();
            assertThat(sut.previous()).isEqualTo("A");
            assertThat(sut.hasPrevious()).isFalse();
        }

        @Test
        @DisplayName("add()")
        void testAddWithListIterator() {
            ListIterator<String> sut = list.iterator();

            sut.add("A");
            sut.add("B");
            sut.add("C");

            assertThat(list.size()).isEqualTo(3);
            assertThat(list.getFirst()).isEqualTo("A");
            assertThat(list.getLast()).isEqualTo("C");
        }

        @Test
        @DisplayName("remove()")
        void testRemoveWithListIterator() {
            list.addLast("A");
            list.addLast("B");
            list.addLast("C");

            ListIterator<String> sut = list.iterator();

            sut.next(); // "A"
            sut.next(); // "B"
            sut.remove(); // "B" 삭제

            assertThat(list.size()).isEqualTo(2);
            assertThat(list.getFirst()).isEqualTo("A");
            assertThat(list.getLast()).isEqualTo("C");
        }

        @Test
        @DisplayName("set()")
        void testSetWithListIterator() {
            list.addLast("A");
            list.addLast("B");
            list.addLast("C");

            ListIterator<String> sut = list.iterator();

            sut.next(); // "A"
            sut.set("D"); // "A"를 "D"로 변경

            assertThat(list.getFirst()).isEqualTo("D");
            assertThat(sut.previous()).isEqualTo("D");
        }
    }

}
