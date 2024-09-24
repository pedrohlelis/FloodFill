
public class Node<T> {
    public Node<T> next;
    public T data;
    public Node<T> previous;

    public Node(Node<T> previous, T value, Node<T> next) {
        this.previous = previous;
        this.data = value;
        this.next = next;
    }
}