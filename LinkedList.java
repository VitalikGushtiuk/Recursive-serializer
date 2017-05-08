

public class LinkedList extends data_type{
	private Node_List head;
	
	public LinkedList() {
		head = null;
	}
	/**
	 * Get first element from LinkedList
	 * @return first element of current representation
	 */
	public Node_Graph getFirst(){
		return head == null ? null : head.info;
	}
	/**
	 * Insert node in LinkedLsit
	 * @param node add node to current representation
	 */
	public void add(Node_Graph node){
		if( size == 0){
			head = new Node_List(node);
			size++;
			return;
		}
		else if(this.search(node))
			return;
		Node_List aux = head;
		while( aux.getNext() != null )
			aux = aux.getNext();
		aux.nxt = new Node_List(node);
		size++;
	}
	/**
	 * Search node with given reference in LinkedlList
	 * @param node reference to node to find
	 * @return true if searched node was found, false otherwise
	 */
	public boolean search(Node_Graph node){
		Node_List aux = head;
		for(int i = 0;i < size; i++){
			if( aux.getInfo().getName().equals(node.getName()) )
				return true;
			aux = aux.nxt;
		}
		return false;
	}
	/**
	 * Delete node with reference given as a parameter from LinkedList
	 * @param reference to node to be deleted
	 * @return was node deleted or no
	 */
	public Boolean delete(Node_Graph node){
		Boolean result = false;
		Node_List rem = head, aux = head;
		while(rem != null){
			if( node.getName().equals(rem.info.getName()) ){
				if( rem == head )
					this.head = rem.nxt;
				else
					aux.nxt = rem.nxt;
				size--;
				result = true;
			}
			aux = rem;
			rem = rem.nxt;
		}
		return result;
	}
	public void destroy_data(){
		this.head = null;
		size = 0;
	}
}