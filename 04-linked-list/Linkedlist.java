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
        if(length==0){ // if the list is empty we just return null 
            return null;
        }
        Node temp=head;
        head=head.next;
        temp.next=null;
        length--;
        if(length==0){ // if list length become zero make tail null (edge case )
            tail=null;
        }
        return temp;
    }

    public Node get(int index){
        if(length==0 || index >= length) return null;
        if(index==0){
            return head;
        }
        if(index==length){
            return tail;
        }
        Node temp=head;
        for(int i=0;i<length;i++){
            temp=temp.next;
        }
        return temp;
    }
    public void remove(int index){
        if(index<0 || index >=length) return ;
        if(index==0){
            removefrist();
        }
        if(index==length-1){
            removeLast();
        }
        Node temp=head;
        Node pre=head;

        for(int i=0;i<index;i++){
            pre=temp;
            temp=temp.next;
        }

        pre.next=temp.next;
        temp.next=null;
        length--;
    }

    public void reverse(){
        Node temp=head; // start at old head 
        head =tail; // swap head and tail 
        tail=temp;
        Node after=temp.next; //  variable for a next node after temp
        Node before=null; // before temp
        for(int i=0;i<length;i++){
            after=temp.next; // save next node before brakingk link 
            temp.next=before;// point temp.next to before which is in reverse direction 
            before=temp;// move this three variables ahead and do it until all linked list becomes reverse 
            temp=after;
        }
    }


    public void findNOde(int k){

        Node slow=head;
        Node fast=head;
        for(int i=0;i<k;i++){
            if(fast==null){
                return;
            }
            fast=fast.next;
        }

        while(fast!=null){
            fast=fast.next;
            slow=slow.next;
        }

        System.out.println(k+"th node is "+slow.data);

    }

   public void binaryToDecimal() {

    Node temp = head;

    int position = length - 1;
    int decimal = 0;

    while (temp != null) {

        if (temp.data != 0 && temp.data != 1) {
            System.out.println(
                temp.data + " is not a binary digit. Binary contains only 0 and 1."
            );
            return;
        }

        decimal = decimal +(temp.data * (int) Math.pow(2, position));

        temp = temp.next;
        position--;
    }

    System.out.println("Decimal number for given binary is " + decimal);
}
}