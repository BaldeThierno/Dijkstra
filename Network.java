import java.util.ArrayList;

public class Network {
    private ArrayList<Node> nodes;
    private ArrayList<Edge> edges;
    private static Network self;

    private Network(){
        nodes = new ArrayList<Node>();
        edges = new ArrayList<Edge>();
    }

    public static Network getInstance(){
        if(self==null){
            self= new Network();
        }
        return self;
    }

    public void addNode(String name){
        this.nodes.add(new Node(name));
    }

    public void addNode(Node node){
        this.nodes.add(node);
    }

    public void removeNode(String name){
        int index=0;
        for(Node tmp: this.nodes){
            if(tmp.name==name)
                break;
            index++;
        }
        this.nodes.remove(index);
    }

    public void removeNode(Node node){
        this.nodes.remove(node);
    }

    public Node findNode(String name){
        int index=0;
        for(Node tmp:this.nodes){
            if(tmp.name==name)
                break;
            index++;
        }
        return this.nodes.get(index);
    }

    public void addEdge(Node start, Node target, int length){
        this.edges.add(new Edge(start,target,length));
    }

    public void addEdge(String start, String target, int length){
        Node n1=this.findNode(start);
        Node n2=this.findNode(target);
        this.edges.add(new Edge(n1, n2 ,length) );
    }

    public void addEdge(Edge edge){
        this.edges.add(edge);
    }

    public void removeEdge(Edge edge){
        this.edges.remove(edge);
    }

    public ArrayList<Node> getAllNodes(){
        return this.nodes;
    }

    public ArrayList<Edge> getAllEdges(){
        return this.edges;
    }

    public void clear(){
        int index=0;
        while(nodes.size()>0){
            nodes.remove(index);
        }
        while(edges.size()>0){
            edges.remove(index);
        }

    }

    public String toString(){
        return nodes.toString() + "\n" + edges.toString();
    }
}
