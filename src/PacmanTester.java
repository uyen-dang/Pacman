public class PacmanTester {
	public static void main(String[] args) {
		
		Pacman p = new Pacman("mazes/tinyMaze.txt", "mazes/testing123.txt");
		System.out.println(p);
		p.solveBFS();
		System.out.println(p);
		
		Pacman j = new Pacman("mazes/tinyMaze.txt", "mazes/testing123.txt");
		System.out.println(j);
		j.solveDFS();
		System.out.println(j);
		
		Pacman o = new Pacman("mazes/tinyMazeDFSSol.txt", "mazes/beep.txt");
		System.out.println(o);
		
		Pacman z = new Pacman("mazes/bigMaze.txt", "mazes/testing456.txt");
		System.out.println(z);
		z.solveBFS();
		System.out.println(z);
		
		Pacman y = new Pacman("mazes/bigMaze.txt", "mazes/testing456.txt");
		System.out.println(y);
		y.solveDFS();
		System.out.println(y);
		
		Pacman x = new Pacman("mazes/bigMazeDFSSol.txt", "mazes/beep2.txt");
		System.out.println(x);
	
		Pacman aa = new Pacman("mazes/randomMaze.txt", "mazes/testing123.txt");
		System.out.println(aa);
		aa.solveBFS();
		System.out.println(aa);
		
		Pacman ab = new Pacman("mazes/randomMaze.txt", "mazes/testing123.txt");
		ab.solveDFS();
		System.out.println(ab);
		Pacman x = new Pacman("mazes/randomMazeDFSSol.txt", "mazes/beep2.txt");
		System.out.println(x);
		
	}
}
