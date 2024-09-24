public class LinkedList<T> {


    private Node<T> head;

    public LinkedList() {
        this.head = null;
    }


    public void add(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }


    public void remove(T data) {
        if (head == null) {
            System.out.println("The list is empty.");
            return;
        }


        if (head.data.equals(data)) {
            head = head.next;
            return;
        }


        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(data)) {
            current = current.next;
        }

        if (current.next == null) {
            System.out.println("Element not found.");
        } else {
            current.next = current.next.next;  // Remove o nó
        }
    }


    public void display() {
        if (head == null) {
            System.out.println("The list is empty.");
            return;
        }

        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int size = 0;
        Node<T> current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }
}
