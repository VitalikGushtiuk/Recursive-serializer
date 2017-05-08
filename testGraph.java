

import java.io.*;
public class testGraph {
	private int V;
	private int V_max;
	private Node_Graph[] nodes;
	
	
	
	public testGraph() {
		V = 0;
		V_max = 10;
		nodes = new Node_Graph[10];
	}
	/**
	 * Deletes node with reference given as a parameter
	 * @param node2del reference to node to be deleted
	 */
	public void addNode(Node_Graph node2add, String []adj_nodes){
		if(V == 0){
			this.nodes[0] = node2add;
			this.V++;
			return;
		}
		if(V == V_max){
			Node_Graph []buffer = new Node_Graph[V_max * 2];
			buffer = this.nodes.clone();
			this.nodes = buffer;
			this.V_max = V * 2;
		}
		nodes[V] = node2add;
		this.V++;
		
		if(adj_nodes == null)
			return;
		
		for(int i = 0; i < V; i++){
			for(int j = 0; j < adj_nodes.length; j++){
				if( this.nodes[i].getName().equals(adj_nodes[j]) ){
					node2add.adjacency.add(this.nodes[i]);
					this.nodes[i].adjacency.add(node2add);
				}
			}
		}
	}
	/**
	 * Deletes node with reference given as a parameter
	 * @param node2del reference to node to be deleted
	 */
	public void deleteNode(Node_Graph node2del){
		for(int i = 0; i < V; i++){
			if( this.nodes[i].adjacency.search(node2del)){
				this.nodes[i].adjacency.delete(node2del);
			}
		}
		for(int i = 0; i < V; i++){
			if(this.nodes[i].getName().equals(node2del.getName())){
				for(int j = i; j < V; j++){
					this.nodes[j] = this.nodes[j+1] == null ? null : this.nodes[j+1];
				}
				V--;
			}
		}
		
	}
	/**
	 * Adds edge between two nodes
	 * @param name1 specifier "name" of a first node
	 * @param name2 specifier "name" of a second node
	 */
	public void addEdge(String name1, String name2){
		Node_Graph n1 = null;
		Node_Graph n2 = null;
		for(int i = 0; i < V; i++){
			if(this.nodes[i].getName().equals(name1)){
				n1 = this.nodes[i];
			}
			else if(this.nodes[i].getName().equals(name2)){
				n2 = this.nodes[i];
			}
		}
		
		if(n1 != null && n2 != null){
			n1.adjacency.add(n2);
			n2.adjacency.add(n1);
		}
	}
	/**
	 * Deletes edge between two nodes
	 * @param name1 specifier "name" of a first node
	 * @param name2 specifier "name" of a second node
	 */
	public void deleteEdge(String name1, String name2){
		Node_Graph n1 = null;
		Node_Graph n2 = null;
		
		for(int i = 0; i < V; i++){
			if(this.nodes[i].getName().equals(name1)){
				n1 = this.nodes[i];
			}
			if(this.nodes[i].getName().equals(name1)){
				n2 = this.nodes[i];
			}
		}
		if(n1 != null && n2 != null){
			n1.adjacency.delete(n2);
			n2.adjacency.delete(n1);
		}
	}
	/**
	 * Serialize graph from a given name
	 * @param name name from which serialization will be started
	 * @param writer write information to a file
	 */
	public void serializeName(String name, BufferedWriter writer){
		String tabs = new String();
		int pos = this.search(name);
		Node_Graph n = this.nodes[pos];
		serialize(n, tabs, writer);
	}
	
	private void serialize(Node_Graph node, String tabs, BufferedWriter writer){
		if( !node.isSerialized ){
			printSerialize(node, tabs, writer);
		}
		else {
			printReference(node, tabs, writer);
			return;
		}
		tabs+="\t";
		tabs+="\t";
		while(node.adjacency.size != 0){
			serialize(node.adjacency.getFirst(), tabs, writer);
			node.adjacency.delete(node.adjacency.getFirst());
		}
		try {
			writer.write(tabs.substring(0, tabs.length() - 1));
			writer.write("<" + node.adjacency.getClass().getSimpleName() + ">");
			writer.newLine();
			writer.write(tabs.substring(0, tabs.length() - 2) + "Object");
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	/**
	 * Prints information about a node to file.
	 * @param node node to be serialized
	 * @param tabs tabs string of a tab characters for representation
	 * @param writer writer write information to a file
	 */
	public void printSerialize(Node_Graph node, String tabs, BufferedWriter writer){
		int s = 0;
		if(node.adjacency instanceof LinkedList) s = 1;
		if(node.adjacency instanceof Array) s = 2;
		if(node.adjacency instanceof Set) s = 3;
			try {
				writer.write(tabs + "<");
				writer.write("Object class=" + " " + '"' + node.getClass().getSimpleName() + '"' + " ");
				writer.write("Version=" + ' ' +  s + ' ' + "id=" + " " + node.getId());
				writer.write(" >");
				writer.newLine();
				tabs += "\t";
				writer.write(tabs + "<Name>" + " " + node.getName() + " " +"</Name>" );
				writer.newLine();
				writer.write(tabs + "<" + node.adjacency.getClass().getSimpleName() + ">");
				writer.newLine();
				node.isSerialized = true;
			} catch (IOException e) {
				e.printStackTrace();
			}
	}
	/**
	 * Prints information about a node which has already been serialized to file
	 * @param node node to be described
	 * @param tabs string of a tab characters for representation
	 * @param writer write information to a file
	 */
	public void printReference(Node_Graph node, String tabs, BufferedWriter writer){
		int s = 0;
		if(node.adjacency instanceof LinkedList) s = 1;
		if(node.adjacency instanceof Array) s = 2;
		if(node.adjacency instanceof Set) s = 3;
		try {
			writer.write(tabs +  "<Reference class= " + '"' + node.getClass().getSimpleName() + '"');
			writer.write(" Version=" + " " + s + " " + "id=" + ' ' + node.getId() + ' ' + ">");
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	/**
	 * Search Node with given identifier "name" 
	 * @param name name to search
	 * @return index of Node if is found, -1 otherwise
	 */
	public int search(String name){
		for(int i = 0; i < V; i++){
			if(this.nodes[i].getName().equals(name))
				return i;
		}
		return -1;
	}
	public Node_Graph search_ID(int id){
		for(int i = 0; i < V; i++){
			if(this.nodes[i].getId() == id)
				return this.nodes[i];
		}
		return null;
	}
	/**
	 *Set settings for node types NodeA NodeB NodeC
	 *usage:
	 *set 1 - represent adjacency of a node as LinkedList
	 *set 2 - represent adjacency of a node as Array
	 *set 3 - represent adjacency of a node as Set 
	 * @param setA settings for NodeA
	 * @param setB settings for NodeB
	 * @param setC settings for NodeC
	 */
	@SuppressWarnings("unused")
	public void addSetings(int setA, int setB, int setC){
		NodeA a = new NodeA(setA);
		NodeB b = new NodeB(setB);
		NodeC c = new NodeC(setC);
	}
	/**
	 * Deserialize data from a file with name given as a parameter.Creates new graph
	 * based on information from file.
	 * @param filename file to be deserialized
	 */
	public void deserialize(String filename){
		BufferedWriter log_writer = null;
		BufferedReader d_reader = null;
		FileInputStream dis = null;
		try {
			dis = new FileInputStream(filename);
			d_reader = new BufferedReader(new InputStreamReader(dis));
			log_writer = new BufferedWriter(new FileWriter("Deserialize_" + filename + "CAST.log"));
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Interrupted I/O operation");
		}
		this.deserializeFile(d_reader, log_writer);
		try {
			d_reader.close();
			log_writer.close();
		} catch (IOException e) {
			System.out.println("Unable to close a file");
		}
	}
	private void deserializeFile(BufferedReader reader, BufferedWriter log_writer){
		String name = null, line = null;
		String []buffer = null;
		int id = 0, version = 0, ind = 0;
		try {
			while( (line = reader.readLine()) != null){
				buffer = line.substring(line.lastIndexOf('\t') + 1).split("\\s+");
				if(buffer[0].contains("<Object")){
					if(buffer[2].equals('"'+ "NodeA" + '"')){
						version = Integer.parseInt(buffer[4]);
						id = Integer.parseInt(buffer[6]);
						line = reader.readLine();
						buffer = line.substring(line.lastIndexOf('\t') + 1).split("\\s+");
						name = buffer[1];
						this.addNode(new NodeA(id, name, version, log_writer), null);
						line = reader.readLine();
						ind++;
					}
					else if(buffer[2].equals('"'+ "NodeB" + '"')){
						version = Integer.parseInt(buffer[4]);
						id = Integer.parseInt(buffer[6]);
						line = reader.readLine();
						buffer = line.substring(line.lastIndexOf('\t') + 1).split("\\s+");
						name = buffer[1];
						this.addNode(new NodeB(id, name, version, log_writer), null);
						line = reader.readLine();
						ind++;
					}
					else if(buffer[2].equals('"'+ "NodeC" + '"')){
						version = Integer.parseInt(buffer[4]);
						id = Integer.parseInt(buffer[6]);
						line = reader.readLine();
						buffer = line.substring(line.lastIndexOf('\t') + 1).split("\\s+");
						name = buffer[1];
						this.addNode(new NodeC(id, name, version, log_writer), null);
						line = reader.readLine();
						ind++;
					}
				}
				else if(buffer[0].equals("<Reference")){
					this.addEdge(this.nodes[ind - 1].getName(), this.search_ID(Integer.parseInt(buffer[6])).getName() );
				}
				else if(buffer[0].equals("LinkedList") || buffer[0].equals("Array") || buffer[0].equals("Set")){
					line = reader.readLine();
				}
				else if (buffer[0].equals("Object")){
					ind--;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}