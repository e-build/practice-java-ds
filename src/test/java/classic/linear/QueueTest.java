package classic.linear;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QueueTest {
    private Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
    }

    @Nested
    @DisplayName("Given: 새로운 큐")
     class WhenNewQueue {

        @Test
        @DisplayName("Then: 큐는 비어 있어야 함")
        void isEmpty() {
            assertThat(queue.isEmpty()).isTrue();
        }
    }

    @Nested
    @DisplayName("Given: 요소가 추가된 큐")
    class WhenElementsEnqueued {

        @BeforeEach
        void enqueueElements() {
            queue.enqueue(1);
            queue.enqueue(2);
        }

        @Test
        @DisplayName("Then: 큐는 비어 있지 않아야 함")
        void isNotEmpty() {
            assertThat(queue.isEmpty()).isFalse();
        }

        @Test
        @DisplayName("Then: 순서대로 요소를 제거할 수 있어야 함")
        void dequeueInOrder() {
            assertThat(queue.dequeue()).isEqualTo(1);
            assertThat(queue.dequeue()).isEqualTo(2);
        }

        @Test
        @DisplayName("Then: 모든 요소를 제거한 후 큐는 비어 있어야 함")
        void isEmptyAfterDequeueAll() {
            queue.dequeue(); // 1 제거
            queue.dequeue(); // 2 제거
            assertThat(queue.isEmpty()).isTrue();
        }
    }

    @Nested
    @DisplayName("Given: 비어 있는 큐에서")
    class WhenEmptyQueue {

        @Test
        @DisplayName("Then: dequeue 시 예외 발생")
        void throwsExceptionOnDequeue() {
            assertThrows(IllegalStateException.class, () -> queue.dequeue());
        }
    }

}