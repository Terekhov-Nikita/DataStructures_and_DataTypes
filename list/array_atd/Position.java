package array_atd;

public class Position {
    private int x;

    public Position(int x){
        this.x=x;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void increment(){
        this.x++;
    }

    public void decrement(){
        this.x--;
    }


    public boolean equals(Object obj){
        if(this == obj)
            return true;
        if(obj == null || getClass() != obj.getClass())
            return false;
        Position p = (Position) obj;
        return (this.x == p.x);
    }



}
