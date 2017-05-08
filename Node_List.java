

public class Node_List {
	public Node_Graph info;
	public Node_List nxt;
	
	public Node_List(){};
	
	public Node_List(Node_Graph info){
		this.setInfo(info);
		this.nxt = null;
	}

	public Node_Graph getInfo() {
		return info;
	}

	public void setInfo(Node_Graph info) {
		this.info = info;
	}
	public Node_List getNext(){
		return nxt;
	}
}