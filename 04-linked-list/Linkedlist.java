class Linkedlist {

    private Node head; // 
    private Node tail;
    private int length;

    class Node {

        int data;
        Node next;

        Node(int data) {
            this.data = data;
        }
    }

    Linkedlist(int data) { // constructor of the linked list 

        Node newnode = new Node(data); // node calss object initlized here 

        head = newnode;
        tail = head;
        length = 1;
    }

    public void printlist() { // prints the list 

        Node temp = head;

        while (temp != null) { //O(n) time 
            System.out.print(temp.data + "->");
            temp = temp.next;
        }

        System.out.println("null");
    }

    public void getHead() { //O(1) time complexity 
        System.out.println("head is " + head.data);
    }

    public void getTail() { //O(1) time complexity
        System.out.println("tail is " + tail.data);
    }

    public void append(int data) { 

        Node newNode = new Node(data);

        if (length == 0) {// if list is enpty make newnode head 
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode; //set new node to tail 
        }

        length++;
    }

    public void removeLast() {

        if (length == 0) { //corner cases 
            return;
        }

        if (length == 1) { // corner case where only one node remining to remove 
            head = null;
            tail = null;
            length = 0;
            return;
        }

        Node pre = head;// node before last node 
        Node temp = head; 

        while (temp.next != null) {
            pre = temp;
            temp = temp.next;
        }

        tail = pre;
        tail.next = null;

        length--;
    }


    public Node removefrist(){
        if(length==0){
            return null;
        }

        Node next=head.next;
        head=head.next;
        next=head;
    }
}