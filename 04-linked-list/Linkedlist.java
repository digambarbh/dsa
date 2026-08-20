class Linkedlist {
    // linked list are a dynamic in the nature

    private Node head;
    private Node tail;
    private int length;

    class Node { // node class that create a each node .means a blueprint for a each node
        int data;
        Node next;

        Node(int data) { // node class constructor
            this.data = data;
        }
    }

    Linkedlist(int data) { // linked list constructor which makes a actual linked list object
        Node newnode = new Node(data); // Here actual node is created
        head = newnode;
        tail = head;
        length = 1;

    }

    public void printlist() { // prints the linked list .
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + "->");
            temp = temp.next;
        }
    }

    public void getHead() { // print head data
        System.out.println("head is " + head.data);
    }

    public void getTail() { 
        System.out.println(tail.data);
    }

    public void append(int data) {  // insert the new node at the end of this list .
        Node newNode = new Node(data);
        Node temp = head;
        while(temp.next!=null){
            temp=temp.next;
        }
        temp.next=newNode;
    }
}