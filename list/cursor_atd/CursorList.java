package cursor_atd;

import main.IncorrectElementPosition;
import main.ListElement;

public class CursorList implements Representable {
    private int head=-1;
    private static Position space;
    private static Node[] memoryPool;
    private static final int LENGTH = 10;

    static{
        memoryPool = new Node[LENGTH];
        space = new Position(0);
        for (int i = 0; i < LENGTH; i++) {
            memoryPool[i]=new Node(i+1);
        }
        memoryPool[LENGTH-1]=new Node(0);

    }

    @Override
    public void insert(Position p, ListElement x) throws IncorrectElementPosition {

        if(space.getN()==-1)
            throw new IncorrectElementPosition("Список курсоров заполнен");

        //Если список пуст
        if(head==-1){
            head=0;
            memoryPool[head].setListElement(x);
            memoryPool[head].setNext(head+1);
            space = new Position(memoryPool[head].getNext());
            memoryPool[head].setNext(-1);
            return;
        }

        //Вставка в head элемент(начало)
        if(p.getN()==head){
            //Копирование
            int lastSpaceN = space.getN(); // запоминаем номер спейса на данный момент(чтобы туда вставить то, что было в хеде)
            space.setN(memoryPool[lastSpaceN].getNext());//Назначаем SPACe следующий пустой, т.к. в SPACE записываем то что было в head.
            //^ мы сдвигаем указатель SPACE на следующий пустой
            memoryPool[lastSpaceN].setNode(memoryPool[head].getNext(),memoryPool[head].getListElement());
            memoryPool[head].setNode(lastSpaceN, x);
            return;
        }

        //Вставка в конец
        if (p.getN() == -1) {
            int temp = space.getN();
            space.setN(memoryPool[space.getN()].getNext()); //двигаем спейс
            int pos = getLast(); //номер последней позиции в списке
            memoryPool[pos].setNext(temp); // теперь ты - не последний. ты ссылаешься на спейс
            memoryPool[temp].setNode(-1, x); //в спейс вставляем х и теперь он - последний
            return;
        }

        //Вставка в середину
        Node lastPNode = memoryPool[p.getN()];  //запоминаем предыдущий нод на нужной позиции
        int tempSpace = space.getN();  //берем номер текущего спейса
        space.setN(memoryPool[tempSpace].getNext()); //передвигаем спейс вперед
        memoryPool[tempSpace].setNode(lastPNode.getNext(), lastPNode.getListElement()); //назначаем на бывший спейс тот бывший элемент на нужной пизиции вместе со своей ссылкой на некст
        memoryPool[p.getN()].setNode(tempSpace,x); // на нужную позицию назначаем вставляемый элемент. ссылка на некст ведет на ячейку, куда мы вставили прошлый элемент(бывший спейс)

    }

    private int getLast() {
        int current = head;
        int prev = -1;

        while (current != -1){
            prev = current;
            current = memoryPool[current].getNext();
        }
        return prev;
    }

    @Override
    public void delete(Position p) throws IncorrectElementPosition {
        //Удаление из пустого списка
        if(head==-1)
            return;

        //Удаление из первого элемента списка
        if(p.getN()==head){

            //?? Что мы удаляем голову или следубщий после головы?
            //Если удаляем голову, то:

            int tmpNext= memoryPool[head].getNext();
            memoryPool[head].setListElement(null);
            memoryPool[head].setNext(space.getN());
            space.setN(head);
            head = tmpNext;
            return;

            //Если мы удаляем следующий, то:
            /*
            int tempNext = memoryPool[head].getNext(); // Находим следующий эл-т
            memoryPool[head].setNext(memoryPool[tempNext].getNext()); //Переназначаем next'у головы след-й эл-т
            memoryPool[tempNext].setNext(space.getN());//Переназначаем ук-лю сл-го от головы, ук-ль на первый свободный
            space.setN(tempNext);//Передвигаем ук-ль SPACE.
            p.setN(memoryPool[head].getNext()); //Зануляем адрес и имя.
            return;*/
        }

        //Удаление из середины/конца списка
        int tempPrev = getPrevious(p);//  Находим предыдущий эл-т.
        if(tempPrev == -1) return;//???
        int next = memoryPool[tempPrev].getNext();//Находим эл-т который удаляем.
        memoryPool[next].setListElement(null);
        if(next == -1) return;//???
        memoryPool[tempPrev].setNext(memoryPool[next].getNext());// Скопируем next[2] в next[1] (указатель на следующий эл-т удаляемого эл-та записываем в next предыдущему эл-ту).
        memoryPool[next].setNext(space.getN()); //  Записываем в next в удаленной ячекци указатель на следующий свободный.
        space.setN(next); //  Перемещаем SPACE на образовавшееся после удаления пустое место.
        p.setN(memoryPool[tempPrev].getNext());//   Зануляем адрес и имя.
    }

    private int getPrevious(Position p){
        int current = head;
        int prev = -1;

        while (current != -1) {
            if (p.getN() == current)
                return prev;

            prev = current;
            current = memoryPool[current].getNext();
        }
        return -1;
    }

    /*-----------------------------------*/

    @Override
    public Position previous(Position p) throws IncorrectElementPosition {
        if(p.getN()==head || p.getN()>memoryPool.length) throw new IncorrectElementPosition("Неверно выбрана позиция");
        int tmp = getPrevious(p);
        if(tmp ==-1) throw new IncorrectElementPosition("Неверно выбрана позиция");
        return new Position(tmp);
    }

    @Override
    public ListElement retrieve(Position p) throws IncorrectElementPosition {
        if(p.getN()<0 || p.getN() > memoryPool.length) throw new IncorrectElementPosition("Неверно выбрана позиция");
        if(p.getN()==head) return memoryPool[head].getListElement();
        return memoryPool[p.getN()].getListElement();
    }

    @Override
    public Position next(Position p) throws IncorrectElementPosition {
        if(p.getN()>memoryPool.length) throw new IncorrectElementPosition("Неверно выбрана позиция");

        if(p.getN()==head) return new Position(memoryPool[head].getNext());

        int tmpPrev = getPrevious(p);
        if(tmpPrev == -1) throw new IncorrectElementPosition("Неверно выбрана позиция");
        tmpPrev = memoryPool[tmpPrev].getNext();
        if (tmpPrev == -1) throw new IllegalCallerException("Неверно выбрана позиция");

        return new Position(memoryPool[tmpPrev].getNext());
    }

    @Override
    public Position locate(ListElement x) throws IncorrectElementPosition {
        if(head == -1 || x == null) return null;
        return findElement(x);
    }

    private Position findElement(ListElement x) {
        int current = head;

        while (current != -1){
            if (memoryPool[current].getListElement().equals(x)) return new Position(current);
            current = memoryPool[current].getNext();
        }
        return new Position(-1);
    }

    @Override
    public Position end() {
       return new Position(-1);
    }

    @Override
    public Position first() {
        return new Position(head);
    }

    @Override
    public void makeNull() {
        head = -1;
    }

    @Override
    public void printList(){
        System.out.println();
        for (int i = 0; i < memoryPool.length; i++) {
            if(memoryPool[i].getListElement()==null){
                System.out.printf(i+" "+"null"+" "+ (memoryPool[i].getNext()) +"\n");
            }
            else{
                System.out.printf(i+" "+memoryPool[i].getListElement().nameToString()+" "+memoryPool[i].getListElement().addressToString()+" "+memoryPool[i].getNext()+"\n");
            }
        }
    }
}
