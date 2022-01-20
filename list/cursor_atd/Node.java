package cursor_atd;

import main.ListElement;

public class Node {
    private ListElement listElement;
    private int next=-1;

    public Node(ListElement listElement){
        this.listElement = new ListElement(listElement.getName(),listElement.getAddress());
    }

    public Node(int i){
        next = i;
    }

    public Node(Position position) {
        this.next = position.getN();
    }

    public ListElement getListElement() {
        return listElement;
    }

    public int getNext() {
        return next;
    }

    public void setListElement(ListElement listElement) {
        this.listElement = listElement;
    }

    public void setNext(int i) {
        this.next = i;
    }

    public void setNode(int next, ListElement listElement){
        this.next=next;
        this.listElement=listElement;
    }

}

