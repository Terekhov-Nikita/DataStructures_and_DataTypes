package cursor_atd;

import main.IncorrectElementPosition;
import main.ListElement;

public interface Representable {
    void insert(Position p, ListElement x) throws IncorrectElementPosition;
    void delete(Position p) throws IncorrectElementPosition;
    Position end();
    Position locate(ListElement x) throws IncorrectElementPosition;
    ListElement retrieve(Position p) throws IncorrectElementPosition;
    Position next(Position p) throws IncorrectElementPosition;
    Position previous(Position p) throws IncorrectElementPosition;
    Position first();
    void makeNull();
    void printList();
}
