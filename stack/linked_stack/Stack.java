package linked_stack;

public class Stack implements interfaces.Stack {

    private static class Node {

        private int value;
        private Node next;

        private Node(int v, Node n) {
            value = v;  //значение обьекта списка
            next = n;   //указатель на следующий элемент списка
        }
    }

    private Node head; //поле верхний элемент

    //при создании стека он создается пустым (верхний элемент указывает на null)
    public Stack() {
        head = null;
    }

    public void push(int x) {
        head = new Node(x, head);
    }

    public int pop() {
        int value = head.value;
        head = head.next;
        return value;
    }

    public int top() {
        return head.value;
    }

    //никогда не заполнен поскольку не предпологается что в реализации связным списком есть фиксированное колличество для заполнения
    public boolean full() {
        return false;
    }

    public boolean empty() {
        return head == null;
    }

    public void makeNull(){
        head=null;
    }
}
