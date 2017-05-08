

/**
 * Node to build graf
 * @author Vitalik
 *settings: current settings of a node
 *id: "id" specifier of a node
 *name: "name" specifier of a node 
 *adjacency: adjacent nodes storage data type
 *isSerialized: specifies if node has already been serialized
 */
public class Node_Graph {
	static int settings;
	private int id;
	private String name;
	public data_type adjacency;
	public boolean isSerialized;
	
	public void setSettings(int type){};

	public Node_Graph() {
		this.id = -1;
		this.name = null;
		Node_Graph.settings = -1;
		isSerialized = false;
	}
	
	public Node_Graph(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSettings() {
		return settings;
	}
}