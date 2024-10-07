package Queue;

public interface IQueue<T> {
    void enqueue(T data);
    T dequeue();
    void clear();
    boolean contains(T value);
    boolean isEmpty();
    boolean isFull();
}
