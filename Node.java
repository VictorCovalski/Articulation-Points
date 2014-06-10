
import java.util.LinkedList;

/*
 * File: Node.java
 * @author victor
 */
public class Node 
{
    private String label;
    private int id;
    private int backtrack;
    private Node edges[];
    private int depth;
    private boolean color;
    
    /**
     * 
     * @param Edges
     * @param id 
     */
    public Node(int Edges,int id)
    {
        edges = new Node[Edges];
        this.id = id;
    }
    
    /**
     * 
     * @return label of the node
     */
    public String getLabel()
    {
        return label;
    }
    
    /**
     * 
     * @return depth of the node
     */
    public int getDepth()
    {
        return depth;
    }
    
    /**
     * 
     * @return 
     */
    public int getID()
    {
        return id;
    }
    
    /**
     * 
     * @return 
     */
    public int getBacktrack()
    {
        return backtrack;
    }
    
    /**
     * 
     * @return color(if the node was visited or not)
     */
    public boolean getColor()
    {
        return color;
    }
    
    /**
     * Sets a label on the given node
     * @param newlabel 
     */
    public void setLabel(String newlabel)
    {
        this.label = newlabel;
    }
    
    
    /**
     * 
     * @param newdepth sets new depth for the node
     */
    public void setDepth(int newdepth)
    {
        this.depth = newdepth;
    }
    
    /**
     * 
     * @param node adds an edge from the node (object) to the parameter node
     */
    public void addEdge(Node node)
    {
        edges[node.getID()] = node;
    }
    
    /**
     * 
     * @param newcolor sets a color to the node (used in the DFS)
     */
    public void setColor(boolean newcolor)
    {
        this.color = newcolor;
    }
    
    /**
     * 
     * @param newbacktrack sets a new backtrack ID, to be used to identify the Articulation Points
     */
    public void setBacktrack(int newbacktrack)
    {
        this.backtrack = newbacktrack;
    }
    
    /**
     * 
     * @return list of neighbours of a designated node
     */
    public LinkedList getNeighbours()
    {
        LinkedList<Node> neighbours;
        neighbours = new LinkedList();
        for (Node edge : edges) 
        {
            if (edge != null) 
            {
                neighbours.add(edge);
            }
        }
        return neighbours;
    }
    
}
