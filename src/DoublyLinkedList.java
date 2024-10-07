public class DoublyLinkedList<T> {
    private Node<T> top;
    private Node<T> base;
    private int size;


    public Node<T> getBase(){
    	return this.base;
    }
    
    public DoublyLinkedList() {
        this.top = null;
        this.base = null;
        this.size = 0;
    }


    public void add(T value) {
        Node<T> node = new Node<>(null, value, null);
        if (size > 0) {
            top.next = node;
            node.previous = top;
            top = node;
        } else {

            top = node;
            base = node;
        }
        size++;
    }


    public T get(int pos) throws Exception {
	
        Node<T> node = getNode(pos);

        return node.data;
    }

    public void remove(int pos) throws Exception {
        if (pos < 0 || pos >= size) {
            throw new IndexOutOfBoundsException("Invalid position");
        }

        Node<T> nodeToRemove = getNode(pos);

        if (nodeToRemove.previous != null) {
            nodeToRemove.previous.next = nodeToRemove.next;
        } else {

            base = nodeToRemove.next;
        }

        if (nodeToRemove.next != null) {
            nodeToRemove.next.previous = nodeToRemove.previous;
        } else {

            top = nodeToRemove.previous;
        }

        size--;
    }

    public T pop() throws Exception{
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("Cannot pop from an empty list.");
        }

        T data = base.data;
        remove(0);
        return data;
    }
    

    public int getSize() {
        return this.size;
    }

    public boolean isEmpty() {
        return size == 0;
    }


    public void clear() {
        top = null;
        base = null;
        size = 0;
    }


    public void printList() {
        Node<T> current = base;
        while (current != null) {
            System.out.print(current.data + ", ");
            current = current.next;
        }
        System.out.println();
    }


    private Node<T> getNode(int pos) throws Exception {
        if (pos >= 0 && pos < this.size) {
            if (pos <= size / 2) {

                Node<T> node = base;
                for (int i = 0; i < pos; i++) {
                    node = node.next;
                }
                return node;
            } else {

                Node<T> node = top;
                for (int i = this.size - 1; i > pos; i--) {
                    node = node.previous;
                }
                return node;
            }
        }
        throw new Exception("Position out of bounds");
    }
}