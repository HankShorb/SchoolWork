public class Edge implements Comparable<Edge>{
	
	int city1;
	int city2;
	double dist;
	
	public Edge(int c1, int c2, double d){
		city1 = c1;
		city2 = c2;
		dist = d;
	}
	
	public int compareTo(Edge edge){
		return Double.compare(dist, edge.dist);
	}
	
	public String toString(){
		return dist + "";
	}

}
