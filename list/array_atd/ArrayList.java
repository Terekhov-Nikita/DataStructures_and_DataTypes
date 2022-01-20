package array_atd;


import main.IncorrectElementPosition;
import main.ListElement;
import java.util.Arrays;

public class ArrayList implements Representable {

    private static final int DEFAULT_CAPACITY = 10;
    private Position last;//first empty

    private ListElement[] listElements;

    public ArrayList(){
        listElements = new ListElement[10];
        last  = new Position(0);
    }

    @Override
    public void insert(Position p, ListElement x) {
        if (p.getX() > last.getX() || p.getX() > DEFAULT_CAPACITY)//Add if with default_capacity
            return;
        for (int i = last.getX(); i > p.getX(); i--) {
            this.listElements[i] = this.listElements[i - 1];
        }
        this.listElements[p.getX()] = x;
        last.increment();
    }

    @Override
    public Position end() {
        return  new Position(last.getX());
    }

    @Override
    public Position locate(ListElement x){
        for (int i = 0; i < last.getX() ; i++) {
            if(listElements[i].equals(x)){
                return new Position(i);
            }
        }
        return new Position(last.getX());
    }

    @Override
    public ListElement retrieve(Position p) throws IncorrectElementPosition {
        if (p.getX() > last.getX() || p == null)
            throw new IncorrectElementPosition("Неправильно выбрана позиция : объекта в позиции " + p.getX() + " нет!");
        return listElements[p.getX()];
    }

    @Override
    public void delete(Position p) {
        if (p == null || end().getX() == 0)
            return;
        for (int i = p.getX(); i < last.getX(); i++) {
            listElements[i] = listElements[i + 1];
        }
        last.decrement();
    }

    @Override
    public Position next(Position p) throws IncorrectElementPosition {
        if (p == null || p.getX() == last.getX())
            throw new IncorrectElementPosition("Неправильно выбрана позиция : следующего после позиции " + p.getX() + " нет");
        return new Position(p.getX() + 1);
    }

    @Override
    public Position previous(Position p) throws IncorrectElementPosition {
        if( p == null || p==end() || p.getX() == 1)
            throw new IncorrectElementPosition("Неправильно выбрана позиция");
        return new Position(p.getX()-1);
    }

    @Override
    public Position first() {
        return new Position(0);
    }

    @Override
    public void makeNull() {
        for(int i=0;i<last.getX();i++)
            listElements[i]=null;
        last.setX(0);
    }

    @Override
    public void printList() {
        System.out.println("\tFormat: Name | Address");
        //System.out.println("  1, 2, 3, 4, 5, 6, 7,  8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20  ");
        for(int i=0; i < last.getX(); i++) {

            System.out.println(" "+ Arrays.toString(this.listElements[i].getName()) +" | "+ Arrays.toString(this.listElements[i].getAddress()) +" ");
        }
    }
}