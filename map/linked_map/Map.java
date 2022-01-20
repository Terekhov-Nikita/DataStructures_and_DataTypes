package linked_map;

public class Map {

    private Node head;

    private static class Node {

        private int d;
        private char r;
        private Node next;

        private Node(int d, char r){
            this.d=d;
            this.r=r;
        }

    }

    public Map(){
        this.head=null;
    }

    public void makeNull(){
        this.head=null;
    }

    public void assign(int d, char r){
        if(head==null){
            head = new Node(d,r);
            return;
        }
        Node t = getNodeByD(d);
        if(t!=null){
            t.r=r;
        }
        else{
            head.next = t;
            t = new Node(d,r);
        }
        /*
        Node t=head;
        while(t.next!=null){
            t=t.next;
            if(t==null)
                t.next = new Node(d,r);
        }*/
    }

    private Node getNodeByD(int d){
        Node c = head;
        while(c!=null){
            if(c.d==d){
                return c;
            }
            c=c.next;
        }
        return null;
    }

    public boolean compute(int d, char r){
        return getNodeByD(d) != null;
    }

    public void print(){
        Node t = head;
        while(t!=null){
            System.out.println("Ключ: "+t.d+"\tЗначение "+t.r);
            t=t.next;
        }

    }

}
