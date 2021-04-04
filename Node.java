import java.util.ArrayList;

public class Node {
    public final String name;
    public ArrayList<Edge> edges;

    private Node(){
        name = null;
    }

    public Node(String name){
        this.name = name;
        edges = new ArrayList<Edge>();
    }

    public ArrayList<Node> getNeighbors(){
        ArrayList<Node> neighbors = new ArrayList<Node>();
        for(Edge edge:edges){
            if(edge.start==this)
                neighbors.add(edge.target);
            if(edge.target==this)
                neighbors.add(edge.start);
        }

        return neighbors;
    }
    //Myself
    public int getEdgeSize(Node s,Node e){
        for (Edge edge : edges) {
            if (edge.start == s && edge.target == e) {
                return edge.length;
            }
            if (edge.start == e && edge.target == s)
                return edge.length;
        }

        return 0;
    }

    public String toString(){
        return name;
    }

}
