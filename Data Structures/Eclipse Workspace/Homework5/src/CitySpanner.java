import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

public class CitySpanner {
	
	ArrayList<City> cities;
	double[][] edges;
	PriorityQueue<Edge> pQueue;
	Edge[] mst;
	boolean[][] mstEdge;
	
	public CitySpanner(){
		cities = new ArrayList<City>();
		edges = new double[0][0];
	}
	
	public CitySpanner(String file) throws IOException{
		cities = new ArrayList<City>();
		pQueue = new PriorityQueue<Edge>();
		BufferedReader reader = new BufferedReader(new FileReader("resources/cityxy.txt"));
		String line = null;
		while((line = reader.readLine()) != null) {
			String[] data = line.split("\\s+");
			City city = new City(data[0],Integer.parseInt(data[1]),Integer.parseInt(data[2]));
			cities.add(city);
		}
		edges = new double[cities.size()][cities.size()];
		calculateEdges();
		generateMST();
	}
	
	private void calculateEdges(){
		// TODO: write shit
		for(int i=0 ; i<cities.size() ; i++){
			for(int j=i ; j<cities.size() ; j++){
				City city1 = cities.get(i);
				City city2 = cities.get(j);
				double dist = Math.sqrt(Math.pow(city1.x - city2.x, 2) + 
						Math.pow(city1.y-city2.y, 2));
				edges[i][j] = dist;
				edges[j][i] = dist;
			}
		}
	}
	
	private void generateMST(){
		mstEdge = new boolean[cities.size()][cities.size()];
		mst = new Edge[cities.size()-1];
		
		for (int i=0 ; i<cities.size() ; i++){
			for (int j=0 ; j<cities.size() ; j++){
				mstEdge[i][j] = false;
			}
		}
		for (int i=0 ; i<cities.size() ; i++){
			for(int j=i+1 ; j<cities.size(); j++){
				pQueue.add(new Edge(i, j, edges[i][j]));
			}
		}
		
		int numEdges = 0;
		while (numEdges < cities.size()-1){
			Edge edge = pQueue.remove();
			System.out.println(numEdges);
			if(!isConnected(edge.city1, edge.city2)){
				mst[numEdges] = edge;
				mstEdge[edge.city1][edge.city2] = true;
				mstEdge[edge.city2][edge.city1] = true;
				numEdges++;
			}
		}
		
	}
	//breadth first search to check if two cities are connected
	private boolean isConnected(int city1, int city2){
		boolean[] visited = new boolean[cities.size()];
		
		Stack<Integer> s = new Stack<Integer>();
		s.push(city1);
		
		int current;
		while(!s.isEmpty()){
			current = s.pop();
			visited[current] = true;
			if(current == city2)
				return true;
			else{
				for (int i=0 ; i<cities.size() ; i++){
					if(mstEdge[current][i] && !visited[i])
						s.push(i);
				}
			}
		}
		return false;
	}
	
	public void printCityData(){
		for (int i=0 ; i<cities.size(); i++){
			for (int j=i+1 ; j<cities.size(); j++){
				City city1 = cities.get(i);
				City city2 = cities.get(j);
				System.out.println(city1 + " " + city2 + " " + edges[i][j]);
			}
		}
		for (int i=0 ; i<mst.length ; i++){
			System.out.println(cities.get(mst[i].city1) + " " + cities.get(mst[i].city2));
		}
	}
	
}
