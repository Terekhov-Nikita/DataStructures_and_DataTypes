package main;

//import array_list.*;
//import linked_list.*;
//import doubly_linked_atd.*;
import cursor_atd.*;

public class Main {
    public static void main(String[] args) throws IncorrectElementPosition {
        Position p,q;
//      ArrayList l = new ArrayList();
//      LinkedList l = new LinkedList();
//      DoubleLinkedList l = new DoubleLinkedList();
      CursorList l = new CursorList();

        setElementIntoList(l);
        l.printList();

        p = l.first();
        while(!p.equals(l.end())){
            q = l.next(p);
            while(!q.equals(l.end())){
                if(l.retrieve(p).equals(l.retrieve(q)))
                    l.delete(q);
                else
                    q = l.next(q);
            }
            p = l.next(p);
        }
        System.out.print("\nПосле преобразований: ");
        l.printList();
    }

    private static void setElementIntoList(
//            ArrayList l
//            LinkedList l
//            DoubleLinkedList l
            CursorList l
    ) throws IncorrectElementPosition {
        l.insert( l.first(), new ListElement("x".toCharArray(),"X".toCharArray()));
        l.insert( l.first(), new ListElement("a".toCharArray(),"a".toCharArray()));
        l.insert( l.first(), new ListElement("b".toCharArray(),"B".toCharArray()));
        l.insert( l.first(), new ListElement("x".toCharArray(),"X".toCharArray()));
        /*l.insert( l.first(), new ListElement("x".toCharArray(),"X".toCharArray()));
        l.insert( l.first(), new ListElement("x".toCharArray(),"X".toCharArray()));
        l.insert( l.first(), new ListElement("x".toCharArray(),"X".toCharArray()));*/
        System.out.print("Изначальный список: ");
        //l.printList();
    }
}
