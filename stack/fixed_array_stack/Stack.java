package fixed_array_stack;

public class Stack implements interfaces.Stack {
    private int[] s;
    private int up;

    public Stack(int size) {
        s = new int[size];
        up = -1;
    }

    public void push(int x) {
        s[++up] = x;
    }

    public int pop() {
        return s[up--];
    }

    public int top() {
        return s[up];
    }

    public boolean full() {
        return up == s.length - 1;
    }

    public boolean empty() {
        return up == -1;
    }

    public void makeNull(){
        if(empty())
            return;
        else{
            up =-1;
        }
    }
}
