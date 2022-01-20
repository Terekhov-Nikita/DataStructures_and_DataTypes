package atd_linked_queue;

public class Queue implements Representable {
    private Node front;
    private Node rear;

    public Queue(){
        this.front =null;
        this.rear =null;
    }

    @Override
    public void insert(Position p, ListElement x) {

        //Список пуст
        if(front == rear){
            //Вставка в начало списка
            if(front ==null){
                rear = front = new Node(x);
            } else {
                //В списке один элемент в начале
                Node curr1 = new Node(x);
                front =curr1;
                curr1.setNext(rear);
                curr1.getNext().setPrevious(curr1);
            }
            return;
        }

        //Вставка в начало списка когда больше одного элемента в листе
        if(p.getNode()== front){
            Node curr = p.getNode();
            Node nodeX = new Node(x);
            curr.setPrevious(nodeX);
            nodeX.setNext(curr);
            front = nodeX;
            return;
        }

        //Вставка в конец
        if(p.getNode()== rear){
            Node currPrev = rear.getPrevious();
            currPrev.setNext(new Node(x));
            currPrev.getNext().setPrevious(currPrev);
            currPrev.getNext().setNext(rear);
            rear.setPrevious(currPrev.getNext());
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
        if(front ==null || rear ==null || p.getNode()==null) return;

        //Один элемент в списке
        if (rear == front && p.getNode()== front){
            front =null;
            rear =null;
        }

        //Элемент не один и он в начале
        if(p.getNode()== front){
            front = front.getNext();
            front.setPrevious(null);
            p.setNode(front);
            return;
        }

        //Элемент не один и он в конце
        if(p.getNode()== rear){
            rear = rear.getPrevious();
            rear.setNext(null);
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
        Node tmp = front;

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
        Node curr = front;
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
        if(p.getNode()== front)
            throw new IncorrectElementPosition("Неправильно выбрана позиция: до первой позиции ничего нет");
        else{
            if(p.getNode()== rear)
                return new Position(rear.getPrevious());
            return new Position(p.getNode().getPrevious());
        }
    }

    @Override
    public Position next(Position p) throws IncorrectElementPosition {
        if(p==null || p.getNode() == null)
            throw new IncorrectElementPosition("Неправильно выбрана позиция: вы ничего не подали");

        if(p.getNode()== front) {
            return new Position(front.getNext());
        }
        else {
            if (p.getNode()== rear)
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
        return new Position(front);
    }



    @Override
    public void printList() {
        if (front ==null || rear ==null)
            System.out.println("Список пуст.");
        else{
            Node curr = front;
            while(curr!=null){
                System.out.print("\n Name: "+curr.getListElement().addressToString()+" Address: "+curr.getListElement().nameToString()+"");
                curr=curr.getNext();
            }
        }
    }

    @Override
    public void enQueue(char[] name, char[] address) {

        Node x = new Node(new ListElement(name,address));
        x.setNext(null);

        /*Очередь не пуста*/
        if(front != null){
            x.setPrevious(rear);
            rear.setNext(x);
            rear=x;
        }else{ /*Очередь была пуста*/
            x.setPrevious(null);
            front=rear=x;
        }

    }

    @Override
    public Node deQueue() {
        /*Если очередь пуста*/
        if(empty())
            return null;

        /*Если очередь имеет только один элемент*/
        if(front==end().getNode()){
            Node x = new Node(rear.getListElement());
            rear=null;
            return x;
        }
        /*Если в очереди есть несколько элементов*/
        Node x = new Node(front.getListElement());
        front = front.getNext();
        return x;
    }

    @Override
    public ListElement front() {
        return null;
    }

    @Override
    public void makeNull() {
        front =null;
        rear =null;
    }

    @Override
    public boolean full() {
        return false;
    }

    @Override
    public boolean empty() {
        return (front==null);
    }
}
