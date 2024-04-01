package classic.linear;

import java.util.EmptyStackException;
import java.util.concurrent.ConcurrentLinkedQueue;

/*
# 배열을 사용한 스택 구현의 장단점
- 장점
    - 접근 시간: 배열을 사용하면 인덱스를 통해 빠르게 접근할 수 있어, 스택의 top 요소에 접근하는 시간 복잡도가 O(1)
    - 메모리 연속성: 배열은 메모리상에서 연속적인 공간에 데이터를 저장하기 때문에, 캐시 히트율을 높이는 데 유리할 수 있음
- 단점:
    - 크기 조정: 스택의 크기가 동적으로 변할 때 배열의 크기를 조정(증가 또는 감소)해야 하는데, 이 과정에서 추가적인 메모리 할당과 기존 요소들의 복사가 필요하고 이로 인해 성능 저하 가능성 존재함
    - 메모리 낭비: 스택의 최대 크기를 예측해 배열을 미리 할당해야 하는데, 사용하지 않는 공간에 대한 메모리 낭비가 발생함

# 연결 리스트를 사용한 스택 구현의 장단점
- 장점:
    - 동적 크기 조정: 연결 리스트를 사용하면 스택의 크기를 동적으로 조정할 수 있으며, 스택이 커지거나 줄어들 때 추가적인 메모리 복사가 필요 없음. 이는 스택의 크기를 예측하기 어려운 상황에 유리함
    - 메모리 효율성: 사용하는 만큼의 메모리만 할당되므로 불필요한 메모리 낭비가 없음
- 단점:
    - 추가 메모리 사용: 각 요소가 다음 요소를 가리키는 포인터를 추가로 저장해야 하므로, 배열 기반 구현에 비해 상대적으로 더 많은 메모리를 사용할 수 있음
    - 접근 시간: 연결 리스트를 사용한 구현에서는 인덱스를 통한 빠른 접근이 불가능하며, top 요소에 접근하는 것 외의 다른 위치의 요소에 접근할 일이 거의 없기 때문에 스택에서는 큰 단점은 아님
# 결론
- 성능이 중요하고 스택의 최대 크기를 예측할 수 있다면, 배열을 사용하는 것이 좋음
- 스택의 크기 변화가 잦거나 최대 크기를 예측하기 어려운 경우, 연결 리스트를 사용하는 것이 더 유연하고 메모리 효율적
 */
public class Stack<E> {

    private int size;
    private int capacity;
    private E[] elements;

    public Stack() {
        this.size = 0;
        this.elements = createElements(0);
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(E e){
        ensureCapacity();
        size++;
        elements[size - 1] = e;
    }

    private void ensureCapacity() {
        if (size < elements.length) {
            return;
        }
        initElements();
    }

    private void initElements() {
        E[] newElements = createElements(capacity+5);
        if (size >= 0) {
            System.arraycopy(elements, 0, newElements, 0, size);
        }
        elements = newElements;
    }
    public E peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[size-1];
    }

    public E pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        E element = elements[size - 1];
        elements[size - 1] = null;
        size--;
        return element;
    }

    public int size(){
        return size;
    }

    private E[] createElements(int length) {
        return (E[]) new Object[length];
    }
}
