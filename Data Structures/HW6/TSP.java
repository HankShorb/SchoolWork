import java.util.ArrayList;
import java.util.Stack;

public class TSP {
	
	Point[] points;
	Point[] graham;
	
	public TSP(int n){
		genPoints(n);
	}
	
	private void genPoints(int n){
		points = new Point[n];
		for (int i=0 ; i<n ; i++){
			points[i] = new Point((int)(Math.random()*500), (int)(Math.random()*500));
		}
	}
	
	public Point[] grahams(){
		
		if(points.length<4){
			return points;
		}
		
		Point corner = findCorner();
		Point [] sortPoints = quickSort(corner);
		Stack<Point> s = new Stack<Point>();
		s.push(sortPoints[sortPoints.length - 1]);
		s.push(corner);
		int i = 0;
		int size = 2;
		while (i<sortPoints.length-1){
			Point last = s.pop();
			if(s.isEmpty()){
				s.push(last);
				s.push(sortPoints[i]);
				i++;
				size++;
				
			} else {
				Point secondLast = s.peek();
				if(isLeft(sortPoints[i], last, secondLast)){
					s.push(last);
					s.push(sortPoints[i]);
					size++;
					i++;
				} else {
					size--;
				}
			}
		}
		Point[] grahams = new Point[size];
		int index = 0;
		while(!s.isEmpty()){
			grahams[index] = s.pop();
			index++;
		}
		graham = grahams;
		return grahams;
	}
	private Point findCorner(){
		Point corner = points[0];
		for (int i=0 ; i <points.length ; i++){
			if(points[i].y < corner.y)
				corner = points[i];
			else if (points[i].y == corner.y) {
				if(points[i].x < corner.x)
					corner = points[i];
			}
		}
		return corner;
	}
	private Point[] quickSort(Point corner){
		Point[] sortPoints = new Point[points.length];
		int index = 0;
		for (int i=0 ; i<points.length ; i++){
			points[i].setRef(corner);
			sortPoints[i] = points[i];
		}
		quickS(sortPoints,0,sortPoints.length-1);
		return sortPoints;
	}
	private void quickS(Point[] points, int p, int r){
		if(p<r){
			int q = partition(points,p,r);
			quickS(points,p,q-1);
			quickS(points,q+1,r);
		}
	}
	private int partition(Point[] points, int p, int r){
		Point x = points[r];
		int i = p-1;
		for (int j=p ; j<r ; j++){
			if(points[j].lessThan(x)){
				i = i+1;
				Point temp = points[i];
				points[i] = points[j];
				points[j] = temp;
			}
		}
		Point temp = points[i+1];
		points[i+1] = points[r];
		points[r] = temp;
		return i+1;
	}
	
	private boolean isLeft(Point p3, Point p2, Point p1){
		return (p2.x - p1.x)*(p3.y - p1.y) - (p2.y - p1.y)*(p3.x - p1.x) > 0;
	}
	
	public Edge[] tour(){
		Edge[] tour = new Edge[points.length-1];
		ArrayList<Edge> edgeList = new ArrayList<Edge>();
		ArrayList<Point> unvisited = new ArrayList<Point>();
		
		for(int i=0 ; i<points.length ; i++){
			unvisited.add(points[i]);
		}
		
		for(int i=1 ; i<graham.length ; i++){
			Edge e = new Edge(graham[i-1],graham[i]);
			edgeList.add(e);
			unvisited.remove(graham[i]);
		}
		edgeList.add(new Edge(graham[0],graham[graham.length-1]));
		unvisited.remove(graham[0]);
		
		while(unvisited.size()>0){
			Point minP = unvisited.get(0);
			Edge minE = edgeList.get(0);
			double minC = distChange(minE, minP);
			for(int i=0 ; i<edgeList.size() ; i++){
				for(int j=0 ; j<unvisited.size() ; j++){
					double dist = distChange(edgeList.get(i),unvisited.get(j));
					if(dist < minC){
						minC = dist;
						minE = edgeList.get(i);
						minP = unvisited.get(j);
					}
				}
			}
			unvisited.remove(minP);
			edgeList.remove(minE);
			edgeList.add(new Edge(minE.p1, minP));
			edgeList.add(new Edge(minE.p2, minP));
		}
		
		
		Edge max=edgeList.get(0);
		for(int i=1 ; i<edgeList.size() ; i++){
			if(edgeList.get(i).weight > max.weight)
				max = edgeList.get(i);
		}
		edgeList.remove(max);
		
		
		for(int i=0 ; i<tour.length ; i++){
			tour[i] = edgeList.get(i);
		}
		
		return tour;
	}
	private double distChange(Edge e, Point p){
		Edge e1 = new Edge(e.p1, p);
		Edge e2 = new Edge(e.p2, p);
		return e1.weight + e2.weight - e.weight;
	}
	
	
	public Point[] optimal(){
		Point[] minPath = new Point[points.length];
		Point[] path = new Point[points.length];
		for(int i=0 ; i<points.length ; i++){
			minPath[i] = points[i];
			path[i] = points[i];
		}
		
		optRecur(path, 0, minPath);
		
		return minPath;
	}
	
	public void optRecur(Point[] path, int startindex, Point[] minPath) {
        int size = path.length;

        if (size == startindex + 1) {
            if(length(path) < length(minPath)){
            	for (int i=0 ; i<path.length ; i++){
            		minPath[i] = path[i];
            	}
            }
        } else {
            for (int i = startindex; i < size; i++) {
                Point temp = path[i];
                path[i] = path[startindex];
                path[startindex] = temp;
                optRecur(path, startindex + 1, minPath);
                path[startindex] = path[i];
                path[i] = temp;
            }
        }
    }
	private double length(Point[] path){
		double len = 0.0;
		
		for(int i=1 ; i<path.length ; i++){
			len += new Edge(path[i-1],path[i]).weight;
		}
		return len;
	}
	
}
