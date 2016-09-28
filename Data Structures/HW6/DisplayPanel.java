import java.awt.*;

import javax.swing.JPanel;

public class DisplayPanel extends JPanel {
	Point[] points;
	TSP tsp;
	int xs;
	int ys;
	boolean hull;
	boolean tour;
	boolean optimal;

	public DisplayPanel(TSP tsp0, boolean h, boolean t, boolean o) {
		hull = h;
		tour = t;
		optimal = o;
		this.tsp=tsp0;
		this.points = tsp0.points; // allows dispay routines to access the tree
		setBackground(Color.white);
		setForeground(Color.black);
	}

	protected void paintComponent(Graphics g) {
		g.setColor(getBackground()); // colors the window
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(getForeground()); // set color and fonts
		Font MyFont = new Font("SansSerif", Font.PLAIN, 12);
		g.setFont(MyFont);
		xs = 50; // where to start printing on the panel
		ys = 50;
		g.drawString("This shit right hurr", 15, 15);
		int start = 0;
		// print input string on panel, 150 chars per line
		// if string longer than 23 lines don't print
		MyFont = new Font("SansSerif", Font.BOLD, 20); // bigger font for tree
		g.setFont(MyFont);
		((Graphics2D) g).setStroke(new BasicStroke(3));
		this.drawTree(g, this.points); // draw the tree
		if(hull){
			((Graphics2D) g).setStroke(new BasicStroke(1));
			g.setColor(Color.red);
			this.drawConHull(g);
		}
		if(tour){
			((Graphics2D) g).setStroke(new BasicStroke(1));
			g.setColor(Color.red);
			this.drawConHull(g);
			g.setColor(Color.blue);
			this.drawTour(g);
		}
		if(optimal){
			((Graphics2D) g).setStroke(new BasicStroke(1));
			g.setColor(Color.green);
			this.drawOptimal(g);
		}
		revalidate(); // update the component panel
	}

	public void drawTree(Graphics g, Point[] points) {// actually draws the tree
		for (int i=0 ; i<points.length ; i++){
			g.drawLine(points[i].x+xs, points[i].y+ys, points[i].x+xs, points[i].y+ys);
		}
	}
	
	public void drawConHull(Graphics g){
		Point[] graham = tsp.grahams();
		for(int i=1 ; i<graham.length ; i++){
			drawEdge(new Edge(graham[i-1],graham[i]),g);
		}
		drawEdge(new Edge(graham[graham.length-1],graham[0]),g);
	}
	
	public void drawTour(Graphics g){
		Edge[] tour = tsp.tour();
		for(int i=0 ; i<tour.length ; i++){
			drawEdge(tour[i],g);
		}
	}
	
	public void drawOptimal(Graphics g){
		Point[] optimal = tsp.optimal();
		for(int i=1 ; i<optimal.length ; i++){
			drawEdge(new Edge(optimal[i-1],optimal[i]),g);
		}
		double length = length(optimal);
		g.drawString("The optimal path is of length: " + length, 15, 35);
	}
	
	private double length(Point[] path){
		double len = 0.0;
		
		for(int i=1 ; i<path.length ; i++){
			len += new Edge(path[i-1],path[i]).weight;
		}
		return len;
	}
	
	private void drawEdge(Edge e, Graphics g){
		g.drawLine(e.p1.x+xs, e.p1.y+ys, e.p2.x+xs, e.p2.y+ys);
	}
}