package doubly_linked_atd;

public class Position {
    private Node node;

    public Position(Node node) { this.node =node; }

    public Node getNode() { return this.node; }

    public void setNode(Node node) { this.node = node; }

    @Override
    public boolean equals(Object obj) {
        if (obj == null && this.node == null)
            return true;
        if(this == obj)
            return true;
        if (obj == null || obj.getClass() != getClass())
            return false;
        Position p = (Position) obj;
        return(p.node == null && this.node == null);
    }
}
