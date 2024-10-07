package Queue;

public class DynamicQueue<T> extends Queue<T> {

    public DynamicQueue(int size) {
        super(size);
    }

    @Override
    public void enqueue(T data) {
        try {
            if (this.top == this.data.length - 1) {
                T[] temp = (T[]) new Object[this.data.length * 2];
                for (int i = 0; i < this.data.length; i++) {
                    temp[i] = this.data[i];
                }
                this.data = temp;
            }
            this.data[++top] = data;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public T dequeue() {
        try {
            if (isEmpty()) {
                throw new IllegalStateException("Queue is empty");
            }

            return this.data[++base];
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public boolean contains(T value) {
        for (int i = base + 1; i <= top; i++) {
            if (this.data[i].equals(value)) {
                return true;
            }
        }
        return false;
    }
}