package main;

import atd_linked_stack.ListElement;
import atd_linked_stack.Stack;
import java.util.Random;

public class TestStack {

    public static void main(String[] args) {

        Random obj = new Random();
        interfaces.Stack[] stk = new interfaces.Stack[2];

        stk[0] = new fixed_array_stack.Stack(5);
        stk[1] = new linked_stack.Stack();

        int i, j;

        for (i = 0; i < stk.length; i++) {
            for (j = 1; j < 10; j++) {
                if (stk[i].full()) {
                    break;
                }
                stk[i].push(obj.nextInt() % 100);
            }
        }

        for (i = 0; i < stk.length; i++) {
            while (!stk[i].empty()) {
                System.out.printf("% 4d", stk[i].pop());
            }
            System.out.println();
        }




        atd_linked_stack.Stack l = new Stack();
        setElementIntoList(l);

        System.out.println("Stack is empty: "+l.empty());
        System.out.println("Top: "+l.top().nameToString()+" ");
        l.printNamePopingStack();
        System.out.println("Stack is empty: "+l.empty());
        l.push(new char[]{'N','i','k','i'},new char[]{'T','e','r'});
        l.printNamePopingStack();

//        System.out.println(l.full()); //Заполнен ли список полностью - всегда нет поскольку стек на списках - динамическая коллекция?
//        System.out.println(l.empty()); //Пуст ли список?
//        System.out.println("top os stack is: "+l.top().nameToString()+" "+l.top().addressToString());
//        System.out.println("first from stack is: " + l.pop().nameToString()+" "+l.pop().addressToString());
//        System.out.println("top os stack is: "+l.top().nameToString()+" "+l.top().addressToString());

    }
    private static void setElementIntoList(atd_linked_stack.Stack l){
        l.insert( l.first(), new ListElement("x_name".toCharArray(),"X_Address".toCharArray()));
        l.insert( l.first(), new ListElement("y_name".toCharArray(),"Y_Address".toCharArray()));
        l.insert( l.first(), new ListElement("z_name".toCharArray(),"Z_Address".toCharArray()));
        l.insert( l.first(), new ListElement("a_name".toCharArray(),"A_Address".toCharArray()));
        l.insert( l.first(), new ListElement("b_name".toCharArray(),"B_Address".toCharArray()));
        l.insert( l.first(), new ListElement("g_name".toCharArray(),"G_Address".toCharArray()));
        l.insert( l.first(), new ListElement("d_name".toCharArray(),"D_Address".toCharArray()));
        l.printList();
    }
}
