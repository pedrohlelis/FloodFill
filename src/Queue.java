import java.awt.Color;
import java.awt.image.BufferedImage;
import java.util.LinkedList;


public class Queue<T> {
	private DoublyLinkedList<T> queue;
	
	public Queue() {

        queue = new DoublyLinkedList<>();
    }

    public void enqueue(T value) {
    	if(value == null) {
    		return;
    	}
        queue.add(value);
    }

    public T dequeue() throws Exception {
        if (isEmpty()) {
            throw new Exception("Queue is empty");
        }
        return queue.pop();
    }

    public boolean isEmpty() {
        return queue.size() == 0;
    }
    
    public void printQueue() {
        queue.printList();
    }
    
    public boolean contains(T value) throws Exception {
        for (int i = 0; i < queue.size(); i++) {
            if (queue.get(i).equals(value)) {
                return true;
            }
        }
        return false;
    }
}
