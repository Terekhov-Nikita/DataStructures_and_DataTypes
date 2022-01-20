package cycled_array_queue;

public class Queue implements main.Representable {
    public final int SIZE=5;
    private int cycled_array[], front, rear;

    public Queue(){
        cycled_array = new int[SIZE];
        front = -1;
        rear = -1;

    }


    @Override
    public void enQueue(int x) {
        if(full())
            System.out.println("Очередь пуста!");
        else{
            if(front == -1)
                front= 0;
            rear = (rear+1) %SIZE;
            cycled_array[rear] = x;
            System.out.println("Вставлен "+x);
        }
    }


    @Override
    public int deQueue() {
        int x;
        if(empty()){
            System.out.println("Очередь пуста!");
            return(-1);
        }else{
            x = cycled_array[front];
            if(front==rear){
                front = -1;
                rear = -1;
            } /* В очереди только 1 элемент, поэтому мы удаляем очередь после удаления */
            else{
                front=(front+1)%SIZE;
            }
            return x;
        }
    }

    @Override
    public int front() {
        return front;
    }

    @Override
    public void makeNull() {
        rear=front=-1;
    }

    @Override
    public boolean full() {
        if(front ==0 && rear==SIZE+1)
            return true;
        if(front==rear+1)
            return true;
        return false;
    }

    @Override
    public boolean empty() {
        return (front == -1);
    }

    public void printQueue(){
        System.out.println("Элементы очереди: ");
        for (int i = 0; i < cycled_array.length; i++) {
            System.out.println(" "+cycled_array[i]);
        }
    }

}
