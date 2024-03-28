package classic.linear;

public class DynamicArray<T> {

    private int capacity;
    private int size;
    private T[] elements;

    public DynamicArray(int capacity) {
        this.elements = createElements(capacity);
        this.capacity = capacity;
    }

    public int size() {
        return size;
    }

    public void add(T element) {
        ensureCapacity();
        this.elements[size] = element;
        size++;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
        return elements[index];
    }

    public void remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }

        for (int i = index; i < size - 1; i++) {
            elements[i] = elements[i + 1];
        }

        elements[size - 1] = null;
        size -= 1;
        trimCapacity();
    }

    private void ensureCapacity() {
        if (capacity != size) {
            return;
        }
        capacity = size + (size >> 1);
        initElements();
    }

    private void trimCapacity(){
        if (capacity/2 <= size) {
            return;
        }
        capacity /= 2;
        initElements();
    }

    private void initElements() {
        T[] newElements = createElements(capacity);
        for (int i = 0; i < size; i++) {
            newElements[i] = elements[i];
        }
        elements = newElements;
    }

    private T[] createElements(int capacity) {
        return (T[]) new Object[capacity];
    }
}
