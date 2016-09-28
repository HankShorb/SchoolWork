public class Edge {
	
	Point p1;
	Point p2;
	double weight;
	
	public Edge(Point point1, Point point2){
		p1 = point1;
		p2 = point2;
		weight = Math.sqrt(Math.pow(p1.x-p2.x, 2) + Math.pow(p1.y-p2.y,2));
	}

}
