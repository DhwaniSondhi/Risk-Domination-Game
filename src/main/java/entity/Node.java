package entity;

public class Node {
    public int color;
    public int distance;
    public Node parent;
    public int id;

    public Node(int id) {
        this.color = 0;
        this.distance = Integer.MAX_VALUE;
        this.id = id;
        this.parent = null;
    }

    @Override
    public String toString() {
        return id + " -> " + color;
    }
}
