package atd_linked_map;

import java.util.Objects;

public class Node {
        ListElement listElement;
        private Node next=null;

        public Node(ListElement listElement){
            this.listElement = new ListElement(listElement.getName(),listElement.getAddress());
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public ListElement getListElement(){
            return listElement;
        }

        public void setListElement(char[] name, char[] address){
            this.listElement = new ListElement(name,address);
        }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return listElement.equals(node.listElement) && Objects.equals(next, node.next);
    }

}
