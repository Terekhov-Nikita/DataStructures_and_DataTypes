package doubly_linked_atd;

import main.IncorrectElementPosition;
import main.ListElement;

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
}
