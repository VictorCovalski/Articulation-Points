import java.util.LinkedList;

/**
 *
 * @author victor
 */

public class Graph2014
{
    /* Attributes */
    private Node nodes[];
    private Node root; //attribute used in find AP method
    private int GraphDepth; //keeps track of the depth of the nodes
    
    /**
     * 
     * @param noNodes - number of nodes that are going to be allocated
     * Default constructor for the class Graph2014
     */
    public Graph2014(int noNodes)
    {
        nodes = new Node[noNodes]; //Allocating nodes to noNodes
        
        for (int i=0; i<noNodes ; i++)
        {
            nodes[i] = new Node(noNodes,i);
        }
    }
    
    /**
     * Adds and edge from id1 to id2
     * @param id1 origin of the edge
     * @param id2 destiny of the edge
     */
    public void addEdge(int id1, int id2)
    {
        nodes[id1].addEdge(nodes[id2]);
        nodes[id2].addEdge(nodes[id1]);
    }
    
    /**
     * Sets a label on the given node[id]
     * @param id of the node
     * @param label 
     */
    public void setLabel(int id, String label)
    {
        nodes[id].setLabel(label);
    }
    
   
    /**
     * Method that calls the method visitNode which finds recursively the AP on a given graph.
     * @param id this is the id of the root
     */
    public void findAP(int id)
    {
        this.resetGraph(); // resets the graph
        
        visitNode(id); //Looks for AP
    }
    
    /**
     * Recursive function used to find the AP
     * @param id of the node
     * @return only useful within the recursive calls
     */
    public int visitNode(int id)
    {
        boolean AP = false;
        boolean leaf = true;
        boolean isRoot = false;
        int countSon=0;
        Node node = nodes[id]; // the node being checked at the time
        node.setDepth(++GraphDepth);
        node.setColor(true); //Colors the node gray, which means it can't be visited again
        LinkedList<Node> neighbours = node.getNeighbours(); //gets the node's neighbours
        
        if(node.getDepth() == 1) //checks if it's the root
        {
            isRoot = true;
        }
        
        System.out.println("Depth First Index of " + node.getLabel() +" is "+ String.valueOf(node.getDepth()));
        
        int smalldepth = neighbours.get(0).getDepth();
        for(int i=1; i< neighbours.size(); i++) // looks for the smallest depth between the neighbours
        {
            Node temp = neighbours.get(i);
            if(smalldepth > temp.getDepth())
            {
                smalldepth = temp.getDepth();
            }
        }
        node.setBacktrack(smalldepth);
        
        for(int i =0; i < neighbours.size(); i++) //Iterates on neighbours of the node
        {
            Node temp = neighbours.get(i);
            if(!temp.getColor()) //if not colored
            {
                if(isRoot)
                {
                    countSon++;
                }
                
                leaf = false;
                int backtrack = visitNode(temp.getID());
                
                if(backtrack == node.getDepth())
                {
                    AP = true;
                }
                else
                {
                    if(node.getBacktrack() > backtrack)
                    {
                        node.setBacktrack(backtrack);
                    }
                }
            }
        }
        
        if(AP) //prints if found AP on this point
        {
            if(countSon > 1)
            {
                System.out.println("=====================================\nFound articulation point at ROOT: " + node.getLabel() + "\n=====================================");
            }
            else
            {
                if(!isRoot)
                {
                    System.out.println("=====================================\nFound articulation point:     " + node.getLabel() + "\n=====================================");
                }
            }
            
        }

        if(leaf) //if the node is a leaf
        {
            System.out.println("    Found a leaf on " + node.getLabel());
            System.out.println("    Setting backlink of " + node.getLabel() + " to " + smalldepth);
        }
            
        return node.getBacktrack(); //keep sending the backlink up the tree
    }
    
    /**
     * Resets the nodes in the graph, so the findAP method can be called more than once on the same graph
     */
    public void resetGraph()
    {
        GraphDepth = 0;
        for (Node node : nodes)
        {
            node.setDepth(9999);
            node.setColor(false);
            node.setBacktrack(9999);
        }
    }
    
    /**
     * Main method for this program
     * @param args 
     */
    public static void main(String[] args)
    {
        System.out.println("Starting graph processing tests for biconnected "
                + "components");

        //Three test graphs
	Graph2014 myGraph = new Graph2014(9);   //Parameter to constructor must 
	Graph2014 myGraph2 = new Graph2014(11); // be number of nodes
        Graph2014 myGraph3 = new Graph2014(11);

        //**********First graph
	//From page 258 of McConnell
	System.out.println("***********************Graph 1, example on pg 258 "
                + "of McConnell");

	myGraph.addEdge(0, 1);
	myGraph.addEdge(0, 2);
	myGraph.addEdge(1, 2);
	myGraph.addEdge(1, 3);
	myGraph.addEdge(2, 3);
	myGraph.addEdge(3, 4);
	myGraph.addEdge(3, 5);
	myGraph.addEdge(4, 5);
	myGraph.addEdge(4, 6);
	myGraph.addEdge(5, 7);
	myGraph.addEdge(6, 7);
	myGraph.addEdge(7, 8);

	myGraph.setLabel(0, "A");
	myGraph.setLabel(1, "B");
	myGraph.setLabel(2, "C");
	myGraph.setLabel(3, "D");
	myGraph.setLabel(4, "E");
	myGraph.setLabel(5, "F");
	myGraph.setLabel(6, "G");
	myGraph.setLabel(7, "H");
	myGraph.setLabel(8, "I");

        System.out.println("*****Biconnected components starting at F");
	myGraph.findAP(5);  //find APs starting @ F
        System.out.println();
        System.out.println("*************************************************");
        System.out.println("*****Biconnected components starting at A");
	myGraph.findAP(0);  //find APs starting @ A
	System.out.println();
        System.out.println("*************************************************");
	System.out.println("*****Biconnected components starting at D");
        myGraph.findAP(3);  //start at D, an AP (root is an AP)

	//**********Second graph
	//From page 261 of McConnell (1a)
	System.out.println();
	System.out.println();
	System.out.println("*************************************************");
	System.out.println("***********************Graph 2, 1a on pg 261 of "
                + "McConnell");

	myGraph2.addEdge(0, 1);
	myGraph2.addEdge(0, 4);
	myGraph2.addEdge(0, 7);

	myGraph2.addEdge(1, 4);
	myGraph2.addEdge(1, 5);

	myGraph2.addEdge(2, 5);
	myGraph2.addEdge(2, 9);

	myGraph2.addEdge(3, 6);
	myGraph2.addEdge(3, 10);

	myGraph2.addEdge(4, 8);

	myGraph2.addEdge(5, 8);
	myGraph2.addEdge(5, 9);

	myGraph2.addEdge(6, 9);
	myGraph2.addEdge(6, 10);

	myGraph2.addEdge(9, 10);

	myGraph2.setLabel(0, "A");
	myGraph2.setLabel(1, "B");
	myGraph2.setLabel(2, "C");
	myGraph2.setLabel(3, "D");
	myGraph2.setLabel(4, "E");
	myGraph2.setLabel(5, "F");
	myGraph2.setLabel(6, "G");
	myGraph2.setLabel(7, "H");
	myGraph2.setLabel(8, "I");
	myGraph2.setLabel(9, "J");
	myGraph2.setLabel(10, "K");

        System.out.println("*****Biconnected components starting at A");
	myGraph2.findAP(0);  //find APs starting at A
        System.out.println();
	System.out.println("*****Biconnected components starting at F");
	myGraph2.findAP(5);  //find APs starting at F
        System.out.println();
	System.out.println("*****Biconnected components starting at H");
	myGraph2.findAP(7);  //find APs starting at H

	//***********Third graph
	//From page 261 of McConnell (1b)
	System.out.println();
	System.out.println();
        System.out.println("*************************************************");
	System.out.println("***********************Graph 3, 1b on pg 261 of "
                + "McConnell");

	myGraph3.addEdge(0, 1);

	myGraph3.addEdge(1, 5);

	myGraph3.addEdge(2, 3);
	myGraph3.addEdge(2, 6);
	myGraph3.addEdge(2, 7);

	myGraph3.addEdge(3, 7);

	myGraph3.addEdge(4, 5);
	myGraph3.addEdge(4, 8);
	myGraph3.addEdge(4, 9);

	myGraph3.addEdge(5, 6);
	myGraph3.addEdge(5, 8);
	myGraph3.addEdge(5, 9);
	myGraph3.addEdge(5, 10);

	myGraph3.addEdge(6, 10);

	myGraph3.addEdge(8, 9);

	myGraph3.setLabel(0, "A");
	myGraph3.setLabel(1, "B");
	myGraph3.setLabel(2, "C");
	myGraph3.setLabel(3, "D");
	myGraph3.setLabel(4, "E");
	myGraph3.setLabel(5, "F");
	myGraph3.setLabel(6, "G");
	myGraph3.setLabel(7, "H");
	myGraph3.setLabel(8, "I");
	myGraph3.setLabel(9, "J");
	myGraph3.setLabel(10, "K");

        System.out.println("*****Biconnected components starting at A");
	myGraph3.findAP(0);  //find APs, starting at A
	System.out.println();
	System.out.println("*************************************************");
        System.out.println("*****Biconnected components starting at G");
	myGraph3.findAP(6);  //find APs starting at G
	System.out.println();
        System.out.println("*************************************************");
        System.out.println("*****Biconnected components starting at F");
	myGraph3.findAP(5);  //find APs starting at F
    }
}
