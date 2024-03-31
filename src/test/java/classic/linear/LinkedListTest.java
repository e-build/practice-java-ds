package classic.linear;

import classic.Foo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.assertj.core.api.Assertions.assertThat;

class LinkedListTest {

    private final LinkedList<Foo> sut = new LinkedList<>();

    @Nested
    @DisplayName("Given: 크기 확인")
    class Conetxt0 {
        @Test
        @DisplayName("Then: size()")
        void tc1() {
            assertThat(sut.size()).isEqualTo(0);
        }
    }

    @Nested
    @DisplayName("Given: 원소 검색")
    class Conetxt1 {

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
    class Conetxt2 {

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
    class Conetxt3 {

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
    class Conetxt4 {

        @Test
        @DisplayName("Then: iterator().hasNext() return true")
        void tc1() {
            sut.addFirst(new Foo("foo1", 1));
            sut.addFirst(new Foo("foo2", 2));

            Iterator<Foo> iterator = sut.iterator();

            assertThat(iterator.hasNext()).isTrue();
        }

        @Test
        @DisplayName("Then: iterator().hasNext() return false")
        void tc2() {
            sut.addFirst(new Foo("foo1", 1));
            sut.addFirst(new Foo("foo2", 2));

            Iterator<Foo> iterator = sut.iterator();
            iterator.next();
            iterator.next();

            assertThat(iterator.hasNext()).isFalse();
        }

        @Test
        @DisplayName("Then: iterator().next()")
        void tc3() {
            sut.addFirst(new Foo("foo1", 1));
            sut.addFirst(new Foo("foo2", 2));

            Iterator<Foo> iterator = sut.iterator();
            Foo foo2 = iterator.next();
            Foo foo1 = iterator.next();

            assertThat(foo2.getName()).isEqualTo("foo2");
            assertThat(foo2.getAge()).isEqualTo(2);
            assertThat(foo1.getName()).isEqualTo("foo1");
            assertThat(foo1.getAge()).isEqualTo(1);
        }
    }
}