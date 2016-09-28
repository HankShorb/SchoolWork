public class Point {
	
	int x;
	int y;
	double angle;
	double weight;
	
	public Point(int x0, int y0){
		x = x0;
		y = y0;
	}
	
	public void setX(int x0){
		x = x0;
	}
	
	public void setY(int y0){
		y = y0;
	}
	
	public void setRef(Point p){
		angle = Math.atan2((double)(p.y-y),(double)(p.x-x));
		weight = Math.sqrt(Math.pow(x-p.x, 2) + Math.pow(y-p.y,2));
	}
	
	public boolean lessThan(Point p){
		if(angle<p.angle)
			return true;
		else if (angle == p.angle){
			if(weight < p.weight)
				return true;
		}
		return false;
	}
	
	public String toString(){
		return "(" + x + ", " + y + ")";
	}

}
