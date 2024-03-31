package classic;

import classic.linear.DynamicArray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DynamicArrayTest {

    @Nested
    @DisplayName("Given: size()")
    class Size {

        @Test
        void tc1() {
            // then
            assertThat(new DynamicArray<>(10).size()).isEqualTo(0);
        }

    }

    @Nested
    @DisplayName("Given: add()")
    class Add {

        @Test
        @DisplayName("Then: 배열 끝에 새로운 요소 추가")
        void tc1() {
            // given
            DynamicArray<Foo> sut = new DynamicArray<>(5);

            // when
            sut.add(new Foo("foo1", 10));
            sut.add(new Foo("foo2", 11));
            sut.add(new Foo("foo3", 12));

            // then
            assertThat(sut.size()).isEqualTo(3);
            assertThat(sut.get(sut.size() - 1).getName()).isEqualTo("foo3");
        }

        @Test
        @DisplayName("Then: capacity 보다 많은 원소 추가 시, 2배씩 capacity 자동 증가")
        void tc2() {
            // given
            DynamicArray<Foo> sut = new DynamicArray<>(5);

            // when
            for (int i = 0; i < 100; i++) {
                sut.add(new Foo("foo"+i, i));
            }
            // then
            assertThat(sut.size()).isEqualTo(100);
            assertThat(sut.get(sut.size() - 1).getName()).isEqualTo("foo99");
        }

    }

    @Nested
    @DisplayName("Given: get()")
    class Get {

        @Test
        @DisplayName("Then: 인덱스를 통한 원소 조회")
        void tc1() {
            // given
            DynamicArray<Foo> sut = new DynamicArray<>(5);
            for (int i = 0; i < 100; i++) {
                sut.add(new Foo("foo"+i, i));
            }

            // then
            assertThat(sut.size()).isEqualTo(100);
            assertThat(sut.get(30).getName()).isEqualTo("foo30");
        }
    }

    @Nested
    @DisplayName("Given: remove()")
    class Remove {

        @Test
        @DisplayName("Then: 중간 인덱스 삭제 시, 하나씩 앞으로")
        void tc1() {
            // given
            DynamicArray<Foo> sut = new DynamicArray<>(5);
            for (int i = 0; i < 100; i++) {
                sut.add(new Foo("foo"+i, i));
            }

            // when
            sut.remove(30);

            // then
            assertThat(sut.size()).isEqualTo(99);
            assertThat(sut.get(29).getName()).isEqualTo("foo29");
            assertThat(sut.get(30).getName()).isEqualTo("foo31");
            assertThat(sut.get(31).getName()).isEqualTo("foo32");
            assertThat(sut.get(98).getName()).isEqualTo("foo99");
        }

        @Test
        @DisplayName("Then: 용량이 줄어들면 배열 크기 재조정")
        void tc2() {
            // given
            DynamicArray<Foo> sut = new DynamicArray<>(5);
            for (int i = 0; i < 30; i++) {
                sut.add(new Foo("foo"+i, i));
            }

            // when
            for (int i = 0; i < 10; i++) {
                sut.remove(0);
            }

            // then
            assertThat(sut.size()).isEqualTo(20);
        }
    }


}