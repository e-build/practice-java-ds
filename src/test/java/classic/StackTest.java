package classic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.EmptyStackException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StackTest {

    private Stack<String> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @Nested
    @DisplayName("스택에 요소 추가")
    class Push {

        @Test
        @DisplayName("요소를 추가하면 스택이 비어있지 않음")
        void pushToEmptyStack() {
            stack.push("Java");
            assertThat(stack.isEmpty()).isFalse();
        }

        @Test
        @DisplayName("여러 요소를 추가하면 가장 마지막에 추가된 요소가 맨 위에 있음")
        void pushMultipleElements() {
            stack.push("Java");
            stack.push("Python");
            stack.push("C++");
            assertThat(stack.peek()).isEqualTo("C++");
        }
    }

    @Nested
    @DisplayName("스택에서 요소 제거")
    class Pop {

        @Test
        @DisplayName("스택에서 요소를 제거하면 그 요소가 반환됨")
        void popFromStack() {
            stack.push("Java");
            assertThat(stack.pop()).isEqualTo("Java");
            assertThat(stack.isEmpty()).isTrue();
        }

        @Test
        @DisplayName("스택이 비어 있을 때 pop을 호출하면 예외 발생")
        void popFromEmptyStack() {
            assertThrows(EmptyStackException.class, () -> {
                stack.pop();
            });
        }
    }
//
//    @Nested
//    @DisplayName("스택 맨 위의 요소 확인")
//    class Peek {
//
//        @Test
//        @DisplayName("peek을 호출하면 스택 맨 위의 요소가 반환되지만 스택에서는 제거되지 않음")
//        void peekFromStack() {
//            stack.push("Java");
//            assertThat(stack.peek()).isEqualTo("Java");
//            assertThat(stack.size()).isEqualTo(1);
//        }
//
//        @Test
//        @DisplayName("스택이 비어 있을 때 peek을 호출하면 예외 발생")
//        void peekFromEmptyStack() {
//            assertThrows(EmptyStackException.class, () -> {
//                stack.peek();
//            });
//        }
//    }
//
//    @Nested
//    @DisplayName("스택이 비어 있는지 확인")
//    class IsEmpty {
//
//        @Test
//        @DisplayName("새 스택은 비어 있음")
//        void newStackIsEmpty() {
//            assertThat(stack.isEmpty()).isTrue();
//        }
//
//        @Test
//        @DisplayName("요소를 추가한 후 스택은 비어 있지 않음")
//        void stackIsNotEmptyAfterPush() {
//            stack.push("Java");
//            assertThat(stack.isEmpty()).isFalse();
//        }
//    }
//
//    @Nested
//    @DisplayName("스택의 크기 확인")
//    class Size {
//
//        @Test
//        @DisplayName("새 스택의 크기는 0임")
//        void newSizeIsZero() {
//            assertThat(stack.size()).isEqualTo(0);
//        }
//
//        @Test
//        @DisplayName("요소를 추가하면 스택의 크기가 증가함")
//        void sizeIncreasesOnPush() {
//            stack.push("Java");
//            assertThat(stack.size()).isEqualTo(1);
//        }
//    }
}
