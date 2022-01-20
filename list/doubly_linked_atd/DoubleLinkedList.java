package doubly_linked_atd;

import main.IncorrectElementPosition;
import main.ListElement;

public class DoubleLinkedList implements Representable {
    private Node head;
    private Node tail;

    public DoubleLinkedList(){
        this.head=null;
        this.tail=null;
    }

    @Override
    public void insert(Position p, ListElement x) {

        //Список пуст
        if(head == tail){
            //Вставка в начало списка
            if(head==null){
                tail = head = new Node(x);
            } else {
                //В списке один элемент в начале
                Node curr1 = new Node(x);
                head=curr1;
                curr1.setNext(tail);
                curr1.getNext().setPrevious(curr1);
            }
            return;
        }

        //Вставка в начало списка когда больше одного элемента в листе
        if(p.getNode()==head){
            Node curr = p.getNode();
            Node nodeX = new Node(x);
            curr.setPrevious(nodeX);
            nodeX.setNext(curr);
            head = nodeX;
            return;
        }

        //Вставка в конец
        if(p.getNode()==tail){
            Node currPrev = tail.getPrevious();
            currPrev.setNext(new Node(x));
            currPrev.getNext().setPrevious(currPrev);
            currPrev.getNext().setNext(tail);
            tail.setPrevious(currPrev.getNext());
            return;
        }

        //Вставка в серидину списка
        Node tmp;
        if(isInList(p)) tmp=p.getNode();
        else return;

        Node next = tmp.getNext();
        next.setNext(new Node(x));
        next.getNext().setPrevious(next);
        next.getNext().setNext(tmp);
        tmp.setPrevious(tmp.getNext());
    }

    @Override
    public void delete(Position p) {

        //Список пуст, или элемент "неправильный"
        if(head==null || tail==null || p.getNode()==null) return;

        //Один элемент в списке
        if (tail==head && p.getNode()==head){
            head=null;
            tail=null;
        }

        //Элемент не один и он в начале
        if(p.getNode()==head){
            head=head.getNext();
            head.setPrevious(null);
            p.setNode(head);
            return;
        }

        //Элемент не один и он в конце
        if(p.getNode()==tail){
            tail=tail.getPrevious();
            tail.setNext(null);
            p.setNode(null);
            return;
        }

        //Элемент есть в непустом списке
        if(isInList(p)){
            Node tmpNext = p.getNode().getNext(); //Правый сосед
            Node tmpPrev = p.getNode().getPrevious(); //Левый сосед

            tmpPrev.setNext(p.getNode().getNext());
            tmpNext.setPrevious(tmpPrev);
            p.setNode(tmpPrev.getNext());
        }

    }

    private boolean isInList(Position p){
        Node tmp = head;

        while(tmp!=null){
            if (tmp.equals(p.getNode())) return true;
            tmp=tmp.getNext();
        }
        return false;
    }

    @Override
    public Position locate(ListElement x) throws IncorrectElementPosition {
        if(x==null)
            throw new IncorrectElementPosition("Неправильно выбрана позиция: такого объекта нет");
        return new Position(findNode(x));
    }

    private Node findNode(ListElement listElement){
        Node curr = head;
        while(curr!=null){
            if(curr.getListElement().equals(listElement))
                return curr;
            curr=curr.getNext();
        }
        return null;
    }

    @Override
    public ListElement retrieve(Position p) throws IncorrectElementPosition {
        if(p==null || p.equals(end()))
            throw new IncorrectElementPosition("Неправильно выбрана позиция: такого объекта нет");
        return p.getNode().getListElement();
    }

    @Override
    public Position previous(Position p) throws IncorrectElementPosition {
        if(p== null)
            throw new IncorrectElementPosition("Неправильно выбрана позиция: вы ничего не подали");
        if(p.getNode()==head)
            throw new IncorrectElementPosition("Неправильно выбрана позиция: до первой позиции ничего нет");
        else{
            if(p.getNode()==tail)
                return new Position(tail.getPrevious());
            return new Position(p.getNode().getPrevious());
        }
    }

    @Override
    public Position next(Position p) throws IncorrectElementPosition {
        if(p==null || p.getNode() == null)
            throw new IncorrectElementPosition("Неправильно выбрана позиция: вы ничего не подали");

        if(p.getNode()==head) {
            return new Position(head.getNext());
        }
        else {
            if (p.getNode()==tail)
                return new Position(null);

            if(!isInList(p)) throw new IncorrectElementPosition("Неправильно выбрана позиция: такой позиции в списке нет");
            return new Position(p.getNode().getNext());
        }
    }

    @Override
    public Position end(){
        return new Position(null);
    }

    @Override
    public Position first(){
        return new Position(head);
    }

    @Override
    public void makeNull() {
        head=null;
        tail=null;
    }

    @Override
    public void printList() {
        if (head==null || tail==null)
            System.out.println("Список пуст.");
        else{
            Node curr = head;
            while(curr!=null){
                System.out.print("\n Name: "+curr.getListElement().addressToString()+" Address: "+curr.getListElement().nameToString()+"");
                curr=curr.getNext();
            }
        }
    }
}
