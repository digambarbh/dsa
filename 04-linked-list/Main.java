public class Main {
    public static void main(String[] args) {

        Linkedlist list = new Linkedlist(4);

        list.append(10);
        list.append(20);
        list.append(30);

        list.printlist();

        list.removeLast();
        list.printlist();

        list.removeLast();
        list.printlist();

        list.removeLast();
        list.printlist();

        list.removeLast(); // empty list — should not crash
        list.printlist();
    }
}