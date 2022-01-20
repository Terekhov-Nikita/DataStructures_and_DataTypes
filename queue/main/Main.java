package main;

import java.util.Random;

public class Main {
    public static void main(String[] args) {

        Random rand = new Random();
        cycled_array_queue.Queue queue = new cycled_array_queue.Queue();

        queue.enQueue(1);
        queue.enQueue(2);
        queue.enQueue(3);
        queue.enQueue(4);
        queue.enQueue(5);
        queue.printQueue();

        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());
        System.out.println(queue.deQueue());

        atd_linked_queue.Queue queue2 = new atd_linked_queue.Queue();

        queue2.enQueue(new char[]{'c','a','t'},new char[]{'s','e','a','m'});
        queue2.enQueue(new char[]{'d','o','g'},new char[]{'P','i','t','b','u','l','l'});
        queue2.enQueue(new char[]{'b','i','r','d'},new char[]{'p','a','r','r','o','t'});

        queue2.printList();

        System.out.println();
        System.out.println(queue2.deQueue().getListElement().nameToString());
        System.out.println(queue2.deQueue().getListElement().nameToString());
        System.out.println(queue2.deQueue().getListElement().nameToString());



        queue2.enQueue(new char[]{'h','e','l','l','o'}, new char[]{'t','h','e','r','e'});
        queue2.enQueue(new char[]{'g','e','n','e','r','l'}, new char[]{'k','e','n','o','b','i'});
        System.out.println(queue2.empty());

        System.out.println(queue2.deQueue().getListElement().nameToString());
        System.out.println(queue2.deQueue().getListElement().nameToString());
        System.out.println(queue2.empty());


        cycled_linked_queue.Queue queue3 = new cycled_linked_queue.Queue();

        queue3.enQueue(100);
        queue3.enQueue(20);
        queue3.enQueue(2);
        queue3.enQueue(1);

        System.out.println(queue3.deQueue());
        System.out.println(queue3.deQueue());
        System.out.println(queue3.deQueue());
        System.out.println(queue3.deQueue());
    }
}
