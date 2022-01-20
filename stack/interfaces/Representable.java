package interfaces;

import atd_linked_stack.Position;
import atd_linked_stack.IncorrectElementPosition;
import atd_linked_stack.ListElement;

public interface Representable {
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

        public void push(char[] name , char[] address); //поместить на вершину

        public ListElement pop(); //удаляет элемент сверху стека, эффективно уменьшая его размер на единицу. Возвращает элементу сверху

        public ListElement top(); //возвращает копию вершины

        public boolean full(); //проверка заполненности

        public boolean empty(); //проверка пустотности
}
