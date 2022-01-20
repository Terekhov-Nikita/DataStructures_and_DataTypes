package interfaces;

public interface Stack {
    public void push(int x); //поместить на вершину

    public int pop(); //берет из вершины первый и возвращает его

    public int top(); //возвращает копию вершины

    public boolean full(); //проверка заполненности

    public boolean empty(); //проверка пустотности
}
