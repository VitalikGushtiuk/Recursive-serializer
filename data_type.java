

public abstract class data_type {
	public int size;
	/**
	 * @return first element of current representation
	 */
	public Node_Graph getFirst() { return null; }
	/**
	 * Get first element from LinkedList
	 * @param node add node to current representation
	 */
	public void add(Node_Graph node) { };
	/**
	 * 
	 * @param reference to node to be deleted
	 * @return was node deleted or no
	 */
	public Boolean delete(Node_Graph node) { return true; }
	/**
	 * Search node with given reference
	 * @param node reference to node to find
	 * @return true if searched node was found, false otherwise
	 */
	public boolean search(Node_Graph node){ return true; }
	
}
