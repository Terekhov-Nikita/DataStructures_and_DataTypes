package atd_linked_map;


interface Representable {
        void insert(Position p, ListElement x);
        Position end();
        Position locate(ListElement x) throws IncorrectElementPosition;
        ListElement retrieve(Position p) throws IncorrectElementPosition;
        void delete(Position p);
        Position next(Position p) throws IncorrectElementPosition;
        Position previous(Position p) throws IncorrectElementPosition;
        Position first();
        void makeNull();
        void printList();
}
