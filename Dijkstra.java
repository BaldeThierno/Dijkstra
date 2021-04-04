import java.util.ArrayList;
import java.util.HashMap;

public class Dijkstra {
    private Route road;
    private Network network;
    private Node startNode;
    private HashMap<Node, Predecessor> predecessors;
    private ArrayList<Node> unsettled, settled;

    private Dijkstra(){}

    public Dijkstra(Network network){
        road=new Route();
        this.network = network;
        predecessors = new HashMap<Node, Predecessor>();
        unsettled = network.getAllNodes();
        settled = new ArrayList<Node>();
        startNode = null;
    }

    public void initializeFrom(Node node){
        startNode=node;
    }

    public void initializeFrom(String node){
        predecessors=new HashMap<Node, Predecessor>();
        startNode=network.findNode(node);
        settled.add(new Node(node));
    }

    private Node getLowestUnsettled(){
        throw new UnsupportedOperationException();
    }
    private void settleNode(Node node){
        if(unsettled.contains(node))
            unsettled.remove(node);
        settled.add(node);
    }

    //I check the last value of the node
    public int getPredecessorLengthValue(Node node){
        int res=0;
        res=predecessors.get(node).length;
        return res;
    }
    public  Node nodeInNetwork(Node node){
        for(Node n:unsettled){
            if(node.name ==n.name)
                return n;
        }
        return null;
    }
    public void calculatePaths(){
        startNode=nodeInNetwork(startNode);
        ArrayList<Node> alternative=new ArrayList<Node>();
        Node tmp=startNode;
        ArrayList<Node> neighbor;
        int length=0;


        for(Node node: unsettled){
            //if it is the Startnode i record this one in predecessor with Length=0
            if( tmp==startNode ){
                predecessors.put(startNode,new Predecessor(null,0) );
                neighbor=nodeInNetwork(startNode).getNeighbors();
                neighbor.forEach(n->
                        predecessors.put(n,new Predecessor(startNode,startNode.getEdgeSize(startNode,n) ) )
                );
                alternative.addAll(neighbor);
            }
            for(int i=0;i<network.getAllNodes().size();i++){
                neighbor=alternative.get(i).getNeighbors();
                for (Node check : neighbor) {
                    if(!alternative.contains(check))
                        alternative.add(check);
                }
            }
            for(Node iteratorNode:alternative){
                tmp =  iteratorNode;
                //I get the neighbor of each Node
                neighbor = tmp.getNeighbors();
                //then I check with a loop the list of these nodes to know if they are registered in predecessor
                for (Node check : neighbor) {
                    /** I check if the node in question is not already in my predecessor
                     * if it is in then I compare its size with the new size
                     *if the new one is smaller I save it with the iterator
                     * which is its predecessor. tmp is the predecessor here
                     * */
                    if(predecessors.containsKey(check)){
                        length= getPredecessorLengthValue( tmp ) + check.getEdgeSize( tmp,check );
                        if(length<predecessors.get(check).length)
                            predecessors.replace(check,new Predecessor(tmp,length));
                    }
                    else{
                        length=getPredecessorLengthValue(tmp)+check.getEdgeSize(tmp,check);
                        predecessors.put(check,new Predecessor(tmp,length));
                    }
                    /**if it's not in I'll register it with its predecessor
                     * and the length will be equal to the length that its predecessor already has
                     *   + the length of the edge between the 2 Nodes
                     **/

                }
            }
        }

    }

    public Route generateRoute(Node target){

        ArrayList<Node> fifo=new ArrayList<Node>();
        startNode=nodeInNetwork(startNode);
        target=nodeInNetwork(target);
        Node precedent=target;

        while(precedent!=null){
            fifo.add(precedent);
            precedent=predecessors.get(precedent).predecessor;
        }
        int length=0;
        length=predecessors.get(target).length;

        road.clear();
        for(Node node:fifo){
            road.addNode(node);
        }
        road.setLength(length);
        return road;
    }

    public Route generateRoute(String target){
        Node node=network.findNode(target);
        return this.generateRoute( this.nodeInNetwork(node));
    }

    public Route generateRoute(Node start, Node target){
        this.startNode=start;
        target=this.nodeInNetwork(target);
        return this.generateRoute(target);
    }

    public Route generateRoute(String start, String target){
        this.startNode=network.findNode(start);
        Node targetNode=network.findNode(target);
        return this.generateRoute(network.findNode(start),this.nodeInNetwork(targetNode));
    }

    public void printPredecessors(){
        System.out.println(predecessors);
    }


    private class Predecessor{
        public Node predecessor;
        public int length;

        public Predecessor(Node predecessor, int length){
            this.predecessor = predecessor;
            this.length = length;
        }

        public String toString(){
            if(predecessor==null)
                return "null:" + length;
            return predecessor.name + ":" + length;
        }
    }
}