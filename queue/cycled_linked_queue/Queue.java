package cycled_linked_queue;

import main.Representable;

public class Queue implements Representable {

    private Node tail;

    public Queue(){
        tail = null;
    }

    private static class Node {

        private int value;
        private Node next;

        private Node(int v) {
            this.value = v;
        }
    }


    @Override
    public void enQueue(int x) {
        //Очередь пуста
        if(tail==null){
            tail = new Node(x);
            tail.next = tail;
            return;
        }

            Node n = new Node(x);
            n.next = tail.next;
            tail.next = n;
            tail=n;
    }

    @Override
    public int deQueue() {
        int t = tail.next.value;
        Node n = tail.next;
        tail.next = n.next;
        return t;
    }

    @Override
    public int front() {
        return tail.next.value;
    }

    @Override
    public void makeNull() {
        tail=null;
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public boolean empty() {
        return tail ==null;
    }
}
