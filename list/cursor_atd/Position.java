package cursor_atd;

public class Position {
    private int n; //Свой номер

    Position(int n){
        this.n = n;
    }

    public int getN() {
        return n;
    }

    public void setN(int next) {
        this.n = next;
    }

    public void increment(){
        this.n++;
    }

    public void decrement(){
        this.n--;
    }

    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Position p = (Position) obj;
        return (this.n == p.n);
    }

}
