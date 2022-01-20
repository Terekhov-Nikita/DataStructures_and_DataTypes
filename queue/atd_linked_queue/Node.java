package atd_linked_queue;

public class Node {
    ListElement listElement;
    private Node next = null;
    private Node previous = null;

    public Node(ListElement listElement){
        this.listElement = new ListElement(listElement.getName(),listElement.getAddress());
    }

    public ListElement getListElement() {
        return listElement;
    }

    public Node getNext() {
        return this.next;
    }

    public Node getPrevious() {
        return this.previous;
    }

    public void setListElement(char[] name, char[] address) {
        this.listElement = new ListElement(name,address);
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }
}
