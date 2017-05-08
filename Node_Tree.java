

/**
 * Node to build binary search tree data structure
 */
public class Node_Tree{
	public Node_Graph data;
	public Node_Tree left;
	public Node_Tree right;
	
	public Node_Tree(Node_Graph data){
		this.data = data;
		left = null;
		right = null;
	}
}
