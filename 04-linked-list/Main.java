public class Main {
    public static void main(String[] args) {

        Linkedlist list = new Linkedlist(1);

        list.append(1);
        list.append(1);
        list.append(0);

        // list.printlist();

        // list.removeLast();
        // list.printlist();

        // list.removeLast();
        // list.printlist();

        // list.removeLast();
        // list.printlist();

        // list.removeLast(); // empty list — should not crash
        // list.printlist();

        list.reverse();
        list.printlist();

        list.findNOde(2);
        list.binaryToDecimal();
    }
}