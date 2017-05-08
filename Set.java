

public class Set extends data_type{
	private Node_Tree root;
	
	public Set(){
		this.root = null;
	}
	/**
	 * @return first element of current representation
	 */
	public Node_Graph getFirst(){
		return root == null ? null : root.data;
	}
	/**
	 * Insert node in BST
	 * @param node add node to current representation
	 */
	public void add(Node_Graph node){
		Node_Tree newNode = new Node_Tree(node);
		if(this.root == null){
			this.root = newNode;
			size++;
			return;
		}
		else if( this.root.data.getName().equals(node.getName()) )
			return;
		
		Node_Tree current = this.root;
		Node_Tree parent = null;
		
		while(true){
			parent = current;
			if(parent.data.getName().equals(node.getName()))
				return;
			else if(node.getName().hashCode() < current.data.getName().hashCode()){
				current = current.left;
				if(current == null){
					parent.left = newNode;
					size++;
					return;
				}
			}
			else{
				current = current.right;
				if(current == null){
					parent.right = newNode;
					size++;
					return;
				}
			}
		}
	}
	/**
	 * Search node with given reference in BST
	 * @param node reference to node to find
	 * @return true if searched node was found, false otherwise
	 */
	public boolean search(Node_Graph node){
		Node_Tree current = root;
		
		while(current != null){
			if(current.data.getName().equals(node.getName())){
				return true;
			}
			else if(current.data.getName().hashCode() > node.getName().hashCode()){
				current = current.left;
			}
			else{
				current = current.right;
			}
		}
		return false;
	}
	/**
	 * Delete node with reference given as a parameter from array
	 * @param reference to node to be deleted
	 * @return was node deleted or no
	 */
	public Boolean delete(Node_Graph node){
		Boolean result = false;
		Node_Tree parent = root;
		Node_Tree current = root;
		boolean isLeftChild = false;
		this.size--;
		while(current.data.getName().hashCode() != node.getName().hashCode()){
			parent = current;
			if(current.data.getName().hashCode() > node.getName().hashCode()){
				isLeftChild = true;
				current = current.left;
			}
			else{
				isLeftChild = false;
				current = current.right;
			}
			if(current == null){
				return result;
			}
		}
		if( current.left == null && current.right == null ){
			if( current == root ){
				root = null;
			}
			if(isLeftChild == true){
				parent.left = null;
			}
			else{
				parent.right = null;
			}
		}
		else if( current.right == null ){
			if( current == root ){
				root = current.left;
			}
			else if( isLeftChild ){
				parent.left = current.left;
			}
			else{
				parent.right = current.left;
			}
		}
		else if( current.left == null ){
			if( current == root ){
				root = current.right;
			}
			else if( isLeftChild ){
				parent.left = current.right;
			}
			else{
				parent.right = current.right;
			}
		}else if(current.left != null && current.right != null){
			
			Node_Tree successor = getSuccessor(current);
			if(current == root){
				root = successor;
			}
			else if( isLeftChild ){
				parent.left = successor;
			}
			else{
				parent.right = successor;
			}			
			successor.left = current.left;
		}		
		return result;
	}
	public Node_Tree getSuccessor(Node_Tree deleleNode){
		Node_Tree successor = null;
		Node_Tree successorParent = null;
		Node_Tree current = deleleNode.right;
		while(current != null){
			successorParent = successor;
			successor = current;
			current = current.left;
		}
		if(successor != deleleNode.right){
			successorParent.left = successor.right;
			successor.right = deleleNode.right;
		}
		return successor;
	}
	public void destroy_data(){
		this.root = null;
		size = 0;
	}
}