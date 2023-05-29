import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Stack;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

public class Pacman {

	/** representation of the graph as a 2D array */
	private Node[][] maze;
	/** input file name */
	private String inputFileName;
	/** output file name */
	private String outputFileName;
	/** height and width of the maze */
	private int height;
	private int width;
	/** starting (X,Y) position of Pacman */
	private int startX;
	private int startY;

	/** A Node is the building block for a Graph. */
	private static class Node {
		/** the content at this location */
	    private char content;
	    /** the row where this location occurs */
	    private int row;
	    /** the column where this location occurs */
	    private int col;
		private boolean visited;
		private Node parent;

	    public Node(int x, int y, char c) {
	        visited = false;
	        content = c;
	        parent =  null;
	        this.row = x;
	        this.col = y;
	    }

	    public boolean isWall() { return content == 'X'; }
	    public boolean isVisited() { return visited; }
	}

	/** constructor */
	public Pacman(String inputFileName, String outputFileName) {
		this.inputFileName = inputFileName;
		this.outputFileName = outputFileName;
		buildGraph();
	}

	private boolean inMaze(int index, int bound){
		return index < bound && index >= 0;
	}

	/** helper method to construct the solution path from S to G
	 *  NOTE this path is built in reverse order, starting with the goal G.
	*/
	
	private void backtrack(Node end){
		Node curNode = end;
		while (curNode.content != 'S') {
			curNode.content = '.';
			curNode = curNode.parent;
		}
	}

	/** writes the solution to file */
	public void writeOutput() {
		try {
			PrintWriter output = new PrintWriter(new FileWriter(outputFileName));
			output.println(this.height + " " + this.width);
			for(int r = 0; r < this.maze.length; r++) {
				for(int c = 0; c < this.maze[r].length; c++) {
					output.print(this.maze[r][c].content);
				}
			}
			output.println("");
			output.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

	public String toString() {
		String s = "";
		s += height + " " + width + "\n";
		for (int i = 0; i < height; i++){
			for (int j = 0; j < width; j++){
				s += maze[i][j].content + " ";
			}
			s += "\n";
		}
		return s;
	}

	/** helper method to construct a graph from a given input file;
	 *  all member variables (e.g. maze, startX, startY) should be
	 *  initialized by the end of this method
	 */
	private void buildGraph() {
		try {
			BufferedReader input = new BufferedReader(
				new FileReader(inputFileName));
			String liner = input.readLine();
			String[] a = liner.split(" ");
			
			this.height = Integer.parseInt(a[0]);
			this.width = Integer.parseInt(a[1]);
			
			this.maze = new Node[this.height][this.width];
			
			for(int r = 0; r < this.maze.length; r++) { 
				liner = input.readLine();
				for(int c = 0; c < this.maze[r].length; c++) {
					char b = liner.charAt(c);
					maze[r][c] = new Node(r, c, b);
					if (b == 'S') {
						this.startX = c; this.startY = r;
					}
				}
			}
			input.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/** obtains all neighboring nodes that have *not* been
	 *  visited yet from a given node when looking at the four
	 *  cardinal directions: North, South, West, East (IN THIS ORDER!)
	 *
	 * @param currentNode the given node
	 * @return an ArrayList of the neighbors (i.e., successors)
	 */
	public ArrayList<Node> getNeighbors(Node currentNode) {
		Node north, south, east, west;
		// 0. Initialize an ArrayList of nodes
		ArrayList<Node> neighbors = new ArrayList<Node>();
		// 1. Inspect the north neighbor]
		if (currentNode.row > 0) {
			north = this.maze[currentNode.row-1][currentNode.col];
				if (!north.isVisited() && !north.isWall()) {
					neighbors.add(north);
					north.parent = currentNode;
				}
		}
		// 2. Inspect the south neighbor
		if (currentNode.row < this.height-1) {
			south = this.maze[currentNode.row+1][currentNode.col];
				if (!south.isVisited() && !south.isWall()) {
					neighbors.add(south);
					south.parent = currentNode;
				}
		}
		// 3. Inspect the west neighbor
		if (currentNode.col > 0) {
			west = this.maze[currentNode.row][currentNode.col-1];
				if (!west.isVisited() && !west.isWall()) {
					neighbors.add(west);
					west.parent = currentNode;
				}
		}
		// 4. Inspect the east neighbor
		if (currentNode.col < this.width-1) {
			east = this.maze[currentNode.row][currentNode.col+1];
				if (!east.isVisited() && !east.isWall()) {
					neighbors.add(east);
					east.parent = currentNode;
				}
		}
		return neighbors;
	}

	/** Pacman uses BFS strategy to solve the maze */
	public void solveBFS() {
		Queue<Node> queuetie_pie = new LinkedList<>();
		Node currNode = this.maze[this.startY][this.startX];
		
		queuetie_pie.add(currNode);
		
		while (currNode.content != 'G') {
			currNode.visited = true;
			ArrayList<Node> neighbors = getNeighbors(currNode);
			queuetie_pie.poll();
			for (int i = 0; i < neighbors.size(); i++) {
				queuetie_pie.add(neighbors.get(i));
			}
			currNode = queuetie_pie.peek();
		}
		currNode = currNode.parent;
		backtrack(currNode);
		writeOutput();
	}

	/** Pacman uses DFS strategy to solve the maze */
	public void solveDFS() {
		Node currNode = this.maze[this.startY][this.startX];
		Stack<Node> stack_YoureIt = new Stack<>();
		stack_YoureIt.push(currNode);
		
		while(stack_YoureIt.peek().content != 'G') {
			currNode = stack_YoureIt.peek();
			stack_YoureIt.pop();
			currNode.visited = true;
			ArrayList<Node> neighbors = getNeighbors(currNode);
			
			for(Node next : neighbors) {
				if (!next.isVisited()) {
					stack_YoureIt.push(next);
				}
			}
		}
		backtrack(currNode);
		writeOutput();
	}
}
