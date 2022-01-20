package array_atd;

import main.IncorrectElementPosition;
import main.ListElement;

interface Representable {
        void insert(Position p, ListElement x);
        Position end();
        Position locate(ListElement x);
        ListElement retrieve(Position p) throws IncorrectElementPosition;
        void delete(Position p);
        Position next(Position p) throws IncorrectElementPosition;
        Position previous(Position p) throws IncorrectElementPosition;
        Position first();
        void makeNull();
        void printList();
}
