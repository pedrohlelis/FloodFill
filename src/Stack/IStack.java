package Stack;

public interface IStack<T> {
    void push(T data);
    T pop();
    boolean contains(T value);
    void clear();
    boolean isEmpty();
}
