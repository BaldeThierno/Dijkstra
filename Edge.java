package Graph;

public class Edge {

    final public Node start, target;
    final public int length;

    private Edge(){
        start = null;
        target = null;
        length = 0;
    }

    public Edge(Node start, Node target, int length){
        this.start = start;
        this.target = target;
        this.length = length;
        start.edges.add(this);
        target.edges.add(this);
    }

    public Node otherNode(Node node){
        if(this.start != node){
            return start;
        }
        return target;
    }
    public String toString(){
        return start.name + ":" + target.name + ":" + length;
    }
}
