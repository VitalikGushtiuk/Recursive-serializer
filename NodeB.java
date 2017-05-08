

import java.io.BufferedWriter;
import java.io.IOException;

public class NodeB extends Node_Graph{
	static int settings = -1;
	
	public NodeB() { super(); }

	public NodeB(int settings) { this.setSettings(settings); }
	
	public NodeB(int id, String name) { super(id, name); setSettings(NodeB.settings); }
	
	public NodeB(int id, String name, int settings, BufferedWriter log_writer) { 
		super(id, name); this.cast(settings, log_writer); 
	}
	/**
	 *Set settings for node types NodeB
	 *usage:
	 *set 1 - represent adjacency of a node as LinkedList
	 *set 2 - represent adjacency of a node as Array
	 *set 3 - represent adjacency of a node as Set 
	 * @param type settings for NodeB
	 */
	public void setSettings(int type){
		if(type > 3 || type < 0 )
			return;
		if(NodeB.settings == type)
			NodeB.settings = -1;
		if( NodeB.settings == -1)
			switch(type){
			case 1:
				this.adjacency = new LinkedList();
				NodeB.settings = type;
				break;
			case 2:
				this.adjacency = new Array();
				NodeB.settings = type;
				break;
			case 3:
				this.adjacency = new Set();
				NodeB.settings = type;
				break;
			}
		else {
			if(type < NodeB.settings)
				return;
			else{
				switch(NodeB.settings){
				case 1:
					if(type == 2){
						this.adjacency = copy_adjacency(type);
						NodeB.settings = type;
						break;
					}
					else if(type == 3){
						this.adjacency = copy_adjacency(type);
						NodeB.settings = type;
						break;
					}
				case 2:
					this.adjacency = copy_adjacency(type);
					NodeB.settings = type;
					break;
				}
			}
		
		}
	}
	/**
	 * Set type of stacking adjacent nodes
	 * 1 - LinkedList
	 * 2 - Array
	 * 3 - Set
	 * @param type type of data
	 * @param log_writer in case in which new data type has older version than current settings error
	 * log_writer message displayed in log file
	 */
	public void cast(int type, BufferedWriter log_writer){
		if(type > 3 || type < 0)
			return;
		if(type < NodeB.settings){
			try {
				log_writer.write("Fail cast NodeB" + " " + this.getName() + " "+ "from Version=");
				log_writer.write( NodeB.settings + " " + "to Version="+ '"' + type + '"');
				log_writer.newLine();
			} catch (IOException e) {
				System.out.println("Interrupted I/O operation");
				e.printStackTrace();
			}
			type = NodeB.settings;
		}
		if(NodeB.settings == type){
			switch(type){
			case 1:
				this.adjacency = new LinkedList();
				break;
			case 2:
				this.adjacency = new Array();
				break;
			case 3:
				this.adjacency = new Set();
				break;
			}
		}
		else{
			try {
				log_writer.write("Ok cast NodeB" + " " + this.getName() + " "+ "from Version=");
				log_writer.write('"' +  NodeB.settings+ '"'+"to Version="+ '"' +type + '"');
				log_writer.newLine();
			} catch (IOException e) {
				System.out.println("Interrupted I/O operation");
				e.printStackTrace();
			}
			switch(NodeB.settings){
			case 1:
				if(type == 2){
					this.adjacency = new Array();
					NodeB.settings = type;
					break;
				}
				else if(type == 3){
					this.adjacency = new Set();
					NodeB.settings = type;
					break;
				}
			case 2:
				this.adjacency = new Set();
				NodeB.settings = type;
				break;
			}
		}
	}
	/**
	 * Moves adjacent nodes to new data type
	 * @param type data type of new node
	 * @return new node with data type given as a parameter
	 */
	public data_type copy_adjacency(int type){
		data_type buffer = null; 
		switch(type){
		case 1:
			buffer = new LinkedList();
			break;
		case 2:
			buffer = new Array();
			break;
		case 3:
			buffer = new Set();
			break;
		}
		while(this.adjacency != null && this.adjacency.size != 0){
			buffer.add(this.adjacency.getFirst());
			this.adjacency.delete(this.adjacency.getFirst());
		}
		return buffer;
	}
}
