
public class MazePlace {
	private int row;
	private int col;
	private int dist;
	
	MazePlace(int r,int c, int d){
		row = r;
		col = c;
		dist = d;
	}
	
	public int row() { return row; }
	
	public int col() { return col; }
	
	public int dist() { return dist; }
	
	public void setDist(int d){ dist = d; }
	
}