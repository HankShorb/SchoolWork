/*
 Program To find a breadth-first path in a maze with obstacles
 Uses a queue to expand each cell 1 unit distance
 each cell has 4 neighbors: North, East, South, West.
 Input is 8x8 ascii matrix file, with 0 for empty cell, 1 for obstacle,
 S for Start, G for Goal, and spaces between entries.
 Example Maze File:

0 0 0 0 0 0 0 0  
0 1 1 0 0 0 0 0  
0 1 1 0 1 G 0 0  
0 0 0 0 1 1 1 0  
0 0 0 0 0 1 0 0  
0 S 0 0 0 0 0 0  
0 0 0 0 0 1 0 0 
0 0 0 0 0 0 0 0  

program has 1 argument: Maze File Name

    java maze_file_name
 */

import java.util.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.*;

public class MazeShell implements ActionListener {

	// Fields
	private JFrame frame;
	private DrawingCanvas canvas;
	private JTextArea messageArea;
	private Color color;
	private final int CELL_SIZE = 50;
	private String filename;
	private BufferedReader diskInput;
	private boolean pathfound = false;
	final int WIDTH = 8;
	final int HEIGHT = WIDTH;

	private String[][] mazeMat = new String[8][8];
	private int[][] mazeDist = new int[8][8];
	private MazePlace start;
	private MazePlace end;

	public MazeShell(String file) {
		filename = file;
		frame = new JFrame("Maze");
		frame.setSize(500, 500);

		// The graphics area
		canvas = new DrawingCanvas();
		frame.getContentPane().add(canvas, BorderLayout.CENTER);

		// The message area, mainly for debugging.
		messageArea = new JTextArea(1, 80); // one line text area for messages.
		frame.getContentPane().add(messageArea, BorderLayout.SOUTH);

		JPanel buttonPanel = new JPanel(new java.awt.GridLayout(2, 0));
		frame.getContentPane().add(buttonPanel, BorderLayout.NORTH);

		addButton(buttonPanel, "Draw Initial Grid").setForeground(Color.black);
		addButton(buttonPanel, "Calculate Distances")
				.setForeground(Color.black);
		addButton(buttonPanel, "Show Path").setForeground(Color.black);
		addButton(buttonPanel, "Quit").setForeground(Color.black);

		frame.setVisible(true);
	}

	/** Helper method for adding buttons */
	private JButton addButton(JPanel panel, String name) {
		JButton button = new JButton(name);
		button.addActionListener(this);
		panel.add(button);
		return button;
	}

	/** Respond to button presses */

	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if (cmd.equals("Draw Initial Grid")) {
			initialize();
			return;
		} else if (cmd.equals("Calculate Distances")) {
			pathfound = calcDistances();
			return;
		} else if (cmd.equals("Show Path")) {
			if (pathfound)
				outputPath();
			else{
				messageArea.insert("                                            "+
"                                                                                ", 0);
				messageArea.insert("no Path found!", 0);
			}
			return;
		} else if (cmd.equals("Quit")) {
			frame.dispose();
			return;
		} else
			throw new RuntimeException("No such button: " + cmd);
	}

	public void initialize() {

		canvas.clear();

		/*
		 * reads in the maze file graphs it with the GUI
		 * and creates a 2D String array to store the structure of the maze
		 */
		try {
			diskInput = new BufferedReader(new InputStreamReader(
					new FileInputStream(new File(filename))));

			messageArea.insert("                                                    "+
"                                                                                ", 0);
			messageArea.insert("Maze File Name: " + filename, 0);
			String line;
			int row = 0;
			while ((line = diskInput.readLine()) != null) {
				String[] boo = line.split(" ");
				for (int i = 0; i < boo.length; i++) {

					if (boo[i].equals("0")) {
						fillRectangle(i, row, CELL_SIZE, CELL_SIZE, Color.white);
						mazeMat[row][i] = "0";
					} else if (boo[i].equals("S")) {
						fillRectangle(i, row, CELL_SIZE, CELL_SIZE, Color.blue);
						drawText("START", i, row, Color.black);
						mazeMat[row][i] = "S";
						start = new MazePlace(row, i, 0);
						mazeDist[row][i]=0;
					} else if (boo[i].equals("T")) {
						fillRectangle(i, row, CELL_SIZE, CELL_SIZE, Color.red);
						drawText("END", i, row, Color.black);
						end = new MazePlace(row, i, -1);
						mazeMat[row][i] = "T";
					} else if (boo[i].equals("1")) {
						fillRectangle(i, row, CELL_SIZE, CELL_SIZE, Color.gray);
						drawText("OBS", i, row, Color.black);
						mazeMat[row][i] = "1";
					}
				}
				row++;
			}
			drawGrid();
			/*
			 * drawText("UL",0,0,Color.black); //upper left is 0,0
			 * drawText("UR",WIDTH-1,0,Color.black); //upper right is 0,WIDTH-1
			 * drawText("LL",0,HEIGHT-1,Color.black);//lower left is HEIGHT-1,0)
			 * drawText("LR",HEIGHT-1,HEIGHT-1,Color.black);//lower right is
			 * HEIGHT-1,HEIGHT-1
			 */
		} catch (IOException e) {
			System.out.println("io exception!");
		}
		canvas.display();
	}

	public void drawText(String s, int i, int j, Color color) {
		Color col = color;
		canvas.setForeground(col);
		canvas.drawString(s, (i) * CELL_SIZE + 10, (j + 1) * CELL_SIZE - 10,
				false);
	}

	public void fillRectangle(int i, int j, int height, int width, Color color) {

		Color col = color;
		canvas.setForeground(col);
		canvas.fillRect(i * CELL_SIZE, j * CELL_SIZE, height, width, false);
	}

	public void drawGrid() {

		int k = 0;
		int m = 0;
		color = Color.black;
		canvas.setForeground(color);
		// draw horizontal grid lines
		for (int i = 0; i < 9; i++) {
			canvas.drawLine(0, i * CELL_SIZE, WIDTH * CELL_SIZE, i * CELL_SIZE,
					false);
		}
		// draw vertical grid lines
		for (int i = 0; i < 9; i++) {
			canvas.drawLine(i * CELL_SIZE, 0, i * CELL_SIZE,
					HEIGHT * CELL_SIZE, false);
		}
	}

	public boolean calcDistances() {
		// returns true and displays distances from start to goal if a path
		// exists
		// returns false if no path exists between start and goal

		// Sets all maze entries to unvisited
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				mazeDist[i][j] = -1;
			}
		}
		mazeDist[start.row()][start.col()]=0;

		CQueue searchQ = new CQueue();
		searchQ.enQueue(start);
		MazePlace current;
		MazePlace newPlace;
		int dist;
		int row;
		int col;
		//breadth first search algorithm, keeps track of distances
		while (!(searchQ.isEmpty())) {
			current = (MazePlace) searchQ.deQueue();
			dist = current.dist();
			row = current.row();
			col = current.col();
			// North, checks the space to the north, sees if it should queue it
			if (row - 1 >= 0 && !(mazeMat[row - 1][col].equals("1"))
					&& mazeDist[row - 1][col] == -1) {
				newPlace = new MazePlace(row - 1, col, dist + 1);
				mazeDist[newPlace.row()][newPlace.col()] = newPlace.dist();
				if (mazeMat[newPlace.row()][newPlace.col()].equals("T")) {
					canvas.display();
					end.setDist(dist+1);
					messageArea.insert("                                            "+
"                                                                                ", 0);
					messageArea.insert("Distance to End is " + end.dist(), 0);
					return true;
				}
				drawText(String.valueOf(dist + 1), newPlace.col(), 
						newPlace.row(), Color.black);
				searchQ.enQueue(newPlace);
			}
			// South, checks the space to the south, sees if it should queue it
			if (row + 1 < 8 && !(mazeMat[row + 1][col].equals("1"))
					&& mazeDist[row + 1][col] == -1) {
				newPlace = new MazePlace(row + 1, col, dist + 1);
				mazeDist[newPlace.row()][newPlace.col()] = newPlace.dist();
				if (mazeMat[newPlace.row()][newPlace.col()].equals("T")) {
					canvas.display();
					end.setDist(dist+1);
					messageArea.insert("                                            "+
"                                                                                ", 0);
					messageArea.insert("Distance to End is " + end.dist(), 0);
					return true;
				}
				drawText(String.valueOf(dist + 1), newPlace.col(), 
						newPlace.row(), Color.black);
				searchQ.enQueue(newPlace);
			}
			// East, checks the space to the east, sees if it should queue it
			if (col + 1 < 8 && !(mazeMat[row][col + 1].equals("1"))
					&& mazeDist[row][col + 1] == -1) {
				newPlace = new MazePlace(row, col + 1, dist + 1);
				mazeDist[newPlace.row()][newPlace.col()] = newPlace.dist();
				if (mazeMat[newPlace.row()][newPlace.col()].equals("T")) {
					canvas.display();
					end.setDist(dist+1);
					messageArea.insert("                                            "+
"                                                                                ", 0);
					messageArea.insert("Distance to End is " + end.dist(), 0);
					return true;
				}
				drawText(String.valueOf(dist + 1), newPlace.col(),
						newPlace.row(), Color.black);
				searchQ.enQueue(newPlace);
			}
			// West, checks the space to the west, sees if it should queue it
			if (col - 1 >= 0 && !(mazeMat[row][col - 1].equals("1"))
					&& mazeDist[row][col-1] == -1) {
				newPlace = new MazePlace(row, col - 1, dist + 1);
				mazeDist[newPlace.row()][newPlace.col()] = newPlace.dist();
				if (mazeMat[newPlace.row()][newPlace.col()].equals("T")) {
					canvas.display();
					end.setDist(dist+1);
					messageArea.insert("                                            "+
"                                                                                ", 0);
					messageArea.insert("Distance to End is " + end.dist(), 0);
					return true;
				}
				drawText(String.valueOf(dist + 1), newPlace.col(),
						newPlace.row(), Color.black);
				searchQ.enQueue(newPlace);
			}
		}
		canvas.display();
		return false;
	}


	
	public void outputPath() { // finds the path between goal and start

		// find and display all shortest paths between start and goal if they exist

		
		MazePlace searcher;
		int row;
		int col;
		int dist;
		CQueue searchQ = new CQueue();
		searchQ.enQueue(end);
		searcher = end;
		while(searcher.dist() != 0){
			searcher = (MazePlace) searchQ.deQueue();
			row = searcher.row();
			col = searcher.col();
			dist = searcher.dist();
			fillRectangle(col, row, CELL_SIZE, CELL_SIZE, Color.green);
			//North
			if(row-1 >=0 && mazeDist[row-1][col] == dist-1){
				searchQ.enQueue(new MazePlace(row-1,col,dist-1));
			//South	
			} 
			if(row+1 <8 && mazeDist[row+1][col] == dist-1){
				searchQ.enQueue(new MazePlace(row+1,col,dist-1));
			//East	
			} 
			if(col+1 <8 && mazeDist[row][col+1] == dist-1){
				searchQ.enQueue(new MazePlace(row,col+1,dist-1));
			//West	
			} 
			if(col-1 >=0 && mazeDist[row][col-1] == dist-1){
				searchQ.enQueue(new MazePlace(row,col-1,dist-1));
			}
		}
		fillRectangle(end.col(),end.row(),CELL_SIZE,CELL_SIZE,Color.red);
		drawText("END", end.col(), end.row(), Color.black);
		fillRectangle(start.col(), start.row(), CELL_SIZE, CELL_SIZE, Color.blue);
		drawText("START", start.col(), start.row(), Color.black);
		drawGrid();
		canvas.display();
		


		//This code finds a single path and displays it, delete the comment brackets
		//and comment out the above code, if you would like to run the program
		//while only finding a single shortest path
		/*
		MazePlace searcher = end;
		int row;
		int col;
		int dist;
		CQueue searchQ = new CQueue();
		while(searcher.dist() != 0){
			row = searcher.row();
			col = searcher.col();
			dist = searcher.dist();
			fillRectangle(col, row, CELL_SIZE, CELL_SIZE, Color.green);
			//North
			if(row-1 >=0 && mazeDist[row-1][col] == dist-1){
				searcher = new MazePlace(row-1,col,dist-1);
			//South	
			} 
			if(row+1 <8 && mazeDist[row+1][col] == dist-1){
				searcher = new MazePlace(row+1,col,dist-1);
			//East	
			} 
			if(col+1 <8 && mazeDist[row][col+1] == dist-1){
				searcher = new MazePlace(row,col+1,dist-1);
			//West	
			} 
			if(col-1 <8 && mazeDist[row][col-1] == dist-1){
				searcher = new MazePlace(row,col-1,dist-1);
			}
		}
		fillRectangle(end.col(),end.row(),CELL_SIZE,CELL_SIZE,Color.red);
		drawText("END", end.col(), end.row(), Color.black);
		drawGrid();
		canvas.display();
		*/
		

	}
}