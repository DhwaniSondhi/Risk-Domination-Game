package model;

/**
 * Graph node representation of country for BFS
 */
public class Node {
    public int color;
    public int distance;
    public Node parent;
    public int id;

    /**
     * Initializes node with given id
     *
     * @param id id of country
     */
    public Node(int id) {
        this.color = 0;
        this.distance = Integer.MAX_VALUE;
        this.id = id;
        this.parent = null;
    }
}
