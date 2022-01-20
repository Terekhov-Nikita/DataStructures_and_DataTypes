package atd_linked_map;


public class Map implements Representable{
    private Node head;

    public Map(){this.head=null;}

    public Map(Node head){
        this.head = head;
    }

    @Override
    public void insert(Position p, ListElement x) {
        //Если список пустой и узел позиции ничего не содержит
        if(head==null && p.getNode()==null){
            head = new Node(x);
            return;
        }
        //Находим первый свободный и назначаем ему что подали в метод
        if(p.getNode()==null){
            Position p1 = getPreEnd();
            p1.getNode().setNext(new Node(x));
            return;
        }
        //Если список не пустой и позиция указана первой
        if(p.getNode()==head){
            Node tmp = head;
            head = new Node(x);
            head.setNext(tmp);
            return;
        } else {
            Position tmp = new Position(getPrevious(p));
            if(tmp.getNode() != null){
                if(tmp.getNode().getNext()==null)
                    tmp.getNode().getNext().setNext(new Node(x));
                Node tmp1 = tmp.getNode().getNext();
                tmp.getNode().setNext(new Node(x));
                tmp.getNode().getNext().setNext(tmp1);
            }
        }
    }

    @Override
    public void delete(Position p){
        if(p==null||head==null)
            return;
        if(p.getNode()==head)
            head=null;
        Position curr = new Position(getPrevious(p));
        if(curr.getNode()!=null){
            Node tmp = curr.getNode().getNext();
            curr.getNode().setNext(tmp.getNext());
            p.setNode(curr.getNode().getNext());
        }
    }

    private boolean isInList(Position p){
        Node tmp = head;

        while (tmp!=null){
            if(tmp.equals(p.getNode())) return true;
            tmp=tmp.getNext();
        }
        return false;
    }

    @Override
    public Position locate(ListElement x) throws IncorrectElementPosition {
        if(x==null)
            throw new IncorrectElementPosition("Неправильно выбрана позиция");
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
        if ( p == null || p.equals(getPreEnd()))
            throw new IncorrectElementPosition("Неправильно выбрана позиция : такой позиции нет");
        return p.getNode().getListElement();
    }

    @Override
    public Position next(Position p) throws IncorrectElementPosition {
        if (p == null || p.getNode()==null)
            throw new IncorrectElementPosition("Неправильно выбрана позиция : вы ничего не подали " + p);

        if (p.getNode() == head) {
            return new Position(head.getNext());
        }
        else{
            if(p.getNode()==getPreEnd().getNode())
                return new Position(null);

            if(!isInList(p)) throw new IncorrectElementPosition("Неправильно выбрана позиция");
            return new Position(p.getNode().getNext());
        }
    }

    @Override
    public Position previous(Position p) throws IncorrectElementPosition {
        if(p==null || p.getNode()==null || p.getNode()==this.head)
            throw new IncorrectElementPosition("Неправильно выбрана позиция");
        if (getPrevious(p)!=null) return new Position(getPrevious(p));
        else throw new IncorrectElementPosition("Неправильно выбрана позиция");
    }

    private Node getPrevious(Position p){
        Node curr = this.head;
        Node tmp = null;

        while(curr != null){
            if(p.getNode()==curr)
                return tmp;
            tmp=curr;
            curr = curr.getNext();
        }
        return null;
    }

    @Override
    public Position end() {
        return new Position(null);
    }

    private Position getPreEnd(){
        Node curr = head;
        if(curr==null)
            return null;
        while(curr.getNext() != null){
            curr=curr.getNext();
        }
        return new Position(curr);
    }

    @Override
    public Position first() {
        return new Position(head);
    }

    @Override
    public void makeNull() {
        head=null;
    }

    @Override
    public void printList(){
        if (head==null)
            return;
        else{
            Node curr = head;
            System.out.println("Двусвязный список: ");
            while(curr!=null){
                System.out.println(" Name: "+curr.getListElement().addressToString()+" Address: "+curr.getListElement().nameToString()+" \n");
                curr=curr.getNext();
            }
            return;
        }
    }

    public void assign(char[] d, char[] r){
        Node t = findNode(new ListElement(d,r));
        if(t!=null)
            t.listElement.setAddress(r);
        else end().setNode(new Node(new ListElement(d,r)));
    }

    public boolean compute(char[] d,char[] r){
        Node t = findNode(new ListElement(d,r));
        if(t!=null){
            return true;
        }
        else return false;
    }
}
