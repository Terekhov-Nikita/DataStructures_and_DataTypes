package atd_linked_queue;
public interface Representable {
    void insert(Position p, ListElement x);
    Position end() throws IncorrectElementPosition;
    Position locate(ListElement x) throws IncorrectElementPosition;
    ListElement retrieve(Position p) throws IncorrectElementPosition;
    void delete(Position p);
    Position next(Position p) throws IncorrectElementPosition;
    Position previous(Position p) throws IncorrectElementPosition;
    Position first() throws IncorrectElementPosition;
    void makeNull();
    void printList();
    public void enQueue(char[] name, char[] address);

    public Node deQueue();

    public ListElement front();

    public boolean full();

    public boolean empty();

}
