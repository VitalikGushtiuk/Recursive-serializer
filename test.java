

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.FileWriter;

public class test {
	public static void main(String []args){
		int count_ID = 0;
		BufferedWriter writer = null;
		BufferedReader reader = null;
		FileInputStream is = null;
		String []aux = null;
		String []buffer = null;
		String Line = null;
		try{
			is = new FileInputStream(args[0]);
			reader = new BufferedReader(new InputStreamReader(is));
		}catch(FileNotFoundException e){
			System.out.println("File not found");
		}
		testGraph graph = new testGraph();
		
		try{
			while((Line = reader.readLine()) != null){
				buffer = Line.split("\\s+");
				if(buffer[0].equals("Add")){
					if(buffer[1].equals("NodeA")){
						NodeA n = new NodeA(count_ID++, buffer[2]);
						aux = new String[buffer.length - 3];
						for(int i = 3,j = 0; i < buffer.length; i++, j++){
							aux[j] = buffer[i];
						}
						graph.addNode(n, aux);
					}
					if(buffer[1].equals("NodeB")){
						NodeB n = new NodeB(count_ID++, buffer[2]);
						aux = new String[buffer.length - 3];
						for(int i = 3,j = 0; i < buffer.length; i++, j++){
							aux[j] = buffer[i];
						}
						graph.addNode(n, aux);
					}
					if(buffer[1].equals("NodeC")){
						NodeC n = new NodeC(count_ID++, buffer[2]);
						aux = new String[buffer.length - 3];
						for(int i = 3,j = 0; i < buffer.length; i++, j++){
							aux[j] = buffer[i];
						}
						graph.addNode(n, aux);
					}
				}
				if(buffer[0].equals("Del")){
					if(buffer[1].equals("NodeA") ){
						graph.deleteNode(new NodeA(count_ID,buffer[3]));
					}
					if(buffer[1].equals("NodeB") ){
						graph.deleteNode(new NodeB(count_ID,buffer[3]));
					}
					if(buffer[1].equals("NodeC") ){
						graph.deleteNode(new NodeC(count_ID,buffer[3]));
					}
				}
				if(buffer[0].equals("AddE")){
					graph.addEdge(buffer[1], buffer[2]);
				}
				if(buffer[0].equals("DelE")){
					graph.deleteEdge(buffer[1], buffer[2]);
				}
				if(buffer[0].equals("Settings")){
					graph.addSetings(Integer.parseInt(buffer[1]), Integer.parseInt(buffer[2]),
									 Integer.parseInt(buffer[3]));
				}
				if(buffer[0].equals("Serialize")){
					try{
						writer = new BufferedWriter(new FileWriter(buffer[2]));
						graph.serializeName(buffer[1], writer );
					}catch(FileNotFoundException e){
						System.out.println("File not found");
					}
					writer.close();
				}
				if(buffer[0].equals("Deserialize")){
					testGraph newGraph = new testGraph();
					graph = newGraph;
					graph.deserialize(buffer[1]);
				}
			}
		}catch(IOException e){
			System.out.println("Unable to read from file");
		}
		try {
			writer.close();
			reader.close();
		} catch (IOException e) {
			System.out.println("Unable to close file");
		}
	}
}
