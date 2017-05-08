

public class Array extends data_type{
	private Node_Graph a[];
	
	public Array() {
		a = new Node_Graph[10];
		size = 0;
	}
	
	public Array(int n) {
		a = new Node_Graph[n];
		size = 0;
	}
	
	/**
	 * get element from array with given index
	 * @param pos index of element
	 * @return null if index is invalid, searched node otherwise
	 */
	public Node_Graph get(int pos) {
		if (pos < 0 || pos >= a.length)
			return null;
		
		return a[pos];
	}
	/**
	 * @return first element of current representation
	 */
	
	public Node_Graph getFirst(){
		return a[0];
	}
	/**
	 * Insert node in Array
	 * @param node add node to current representation
	 */
	
	public void add(Node_Graph node){
		if( size == 0){
			a[0] = node;
			size++;
		}
		else if( this.search(node) )
			return;
		else if(size < a.length - 1){
			a[size] = node;
			size++;
		}
		else {
			Node_Graph []resized = new Node_Graph[a.length * 2];
			for(int i = 0;i < size; i++)
				resized[i] = a[i];
			resized[size] = node;
			this.a = resized;
			size++;
		}
	}
	
	/**
	 * Search node with given reference in Array
	 * @param node reference to node to find
	 * @return true if searched node is found, false otherwise
	 */
	public boolean search(Node_Graph node){
		for(int i = 0; i < size; i++){
			if( get(i).getName().equals(node.getName()) )
				return true;
		}
		return false;
	}
	
	public Boolean delete(Node_Graph node){
		Boolean result = false;
		for(int i = 0; i < size; i++){
			if( node.getName().equals(get(i).getName()) ){
				for(int j = i; j < size; j++)
					a[j] = a[j+1] == null ? null : a[j+1];
				size--;
				i--;
				result = true;
			}
		}
		return result;
	}
}
