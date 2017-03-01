import java.awt.Frame;
import java.awt.geom.FlatteningPathIterator;
import java.sql.Array;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

class buildCell {

	int value;	
	boolean wallUp;
	boolean wallRight;

	//constructor
	public buildCell (int val) {

		value = val;
		wallUp = true;
		wallRight = true;
	}

}

public class MyMaze  {

	buildCell[][] maze;
	int m,n, neigh;

	//constructor
	public MyMaze (int m, int n) {

		buildCell[][] maze = new buildCell[m][n];			//made empty cell array of m x n
		int k=0;
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++){
				buildCell cell = new buildCell(k);			//made new cells and attached it in maze[i][j]
				k++;
				maze[i][j] = cell;
			}
		}
		this.maze = maze;
		this.m = m;
		this.n = n;
	}


	//print maze
	public String printMaze () {

		String str = "";
		maze[m-1][n-1].wallRight = false;
		str = str.concat(" ");

		//top border
		for (int i=0; i<n; i++) {
			str = str.concat("__ ");
		}
		str = str.concat("\n");
		str = str.concat(" ");

		//internal lines from 2nd line
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++){
				if ( maze[i][j].wallUp == true) 
					str = str.concat("__");
				else
					str = str.concat("  ");
				if (maze[i][j].wallRight == true)
					str = str.concat("|");
				else
					str = str.concat(" ");
			}
			if (i < m-1){
				str = str.concat("\n");
				str = str.concat("|");
			}
		}

		str = str.concat("\n \n");

		System.out.println(str);
		return str;
	}

	//FAILED ATTEMPT TO PRINT IN JAVA SWING - GUI
	//PROBLEM: DISTORTION IN SPACES
	//		//fake2 append try
	//		//print maze
	//			public String printMaze () {
	//				
	//				StringBuilder sb = new StringBuilder();
	//				
	//				//String str = "";
	//				maze[m-1][n-1].wallRight = false;
	//				sb.append(" ");
	//				
	//				//top border
	//				for (int i=0; i<n; i++) {
	//					sb.append(String.format("%-3s", "__"));
	//					
	//				}
	//				sb.append("\n");
	//				sb.append(String.format("%-1s", ""));
	//
	//				//internal lines from 2nd line
	//				for (int i=0; i<m; i++) {
	//					for (int j=0; j<n; j++){
	//						if ( maze[i][j].wallUp == true) 
	//							sb.append("__");
	//						else
	//							sb.append(String.format("%-5s", ""));
	//						if (maze[i][j].wallRight == true)
	//							sb.append("|");
	//						else
	//							sb.append(String.format("%-2s", ""));
	//					}
	//					if (i < m-1){
	//						sb.append("\n");
	//						sb.append("|");
	//					}
	//				}
	//				
	//				sb.append("");
	////				str = str.concat("");
	//				
	//				String str = sb.toString();
	//				System.out.println("str");
	//				System.out.println(str);
	//				return str;
	//		}
	//		
	
	
	//random selection of cells for comparison
	public int randNum(int upLimit){				

		Random rand = new Random();		
		int  r = rand.nextInt(upLimit) + 0;
		return r;
	}

	//get i,j/position of random number in maze
	public int[] getijOfNumber (int num) {

		int i;
		int[] ij = new int[2];

		for (i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				if (maze[i][j].value == num){
					ij[0] = i;
					ij[1] = j;
					return ij;						
				}
			}
		}
		return ij;
	}


	//compareCells - keep checking until union of all sets
	public boolean compareCells (int sizeOfMaze, DisjSets disjSets) {

		//get random number and its neighbor
		int flag = 0;
		int element = randNum(sizeOfMaze);
		int[] pos = getijOfNumber(element);	
		int[] posNei = Arrays.copyOf(pos, 2);


		while (flag != 1) {
			neigh = randNum(4);

			switch (neigh) {
			case 0: if (pos[1] != 0){			//left neighbor; check for leftmost col
				posNei[1] = pos[1]-1;
				flag = 1;
			}
			break;

			case 1: if (pos[0] != 0) {			//top neighbor; check for uppermost row
				posNei[0] = pos[0]-1;	
				flag = 1;
			}
			break;

			case 2: if (pos[1] != n-1) 	{		//right neighbor; check for rightmost col
				posNei[1] = pos[1]+1;
				flag = 1;
			}
			break;

			case 3: if (pos[0] != m-1) { 		//bottom neighbor; check for bottommost row
				posNei[0] = pos[0]+1;
				flag = 1;	
			}			
			break;
			}
		}

		int neighbor = maze[posNei[0]][posNei[1]].value;

		//compare cells		
		if (disjSets.find(element) != disjSets.find(neighbor)) {

			//union in djSets
			disjSets.union(disjSets.find(element), disjSets.find(neighbor));

			//break walls
			if (neigh == 0)		//left neighbor
				maze[posNei[0]][posNei[1]].wallRight = false;
			else if (neigh == 1) 	//top neighbor
				maze[posNei[0]][posNei[1]].wallUp = false; 
			else if (neigh == 2) 	//right neighbor
				maze[pos[0]][pos[1]].wallRight = false;
			else 				 	//bottom neighbor
				maze[pos[0]][pos[1]].wallUp = false;

			printMaze();
		}

		//check for complete union condition and stop further union
		if (disjSets.isFullyUnion())
			return true;
		else
			return false;	
	}

	
	public static void main(String []args) {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter the number of rows:");
		int m = scan.nextInt();
		System.out.println("Enter the number of columns:");	
		int n = scan.nextInt();

		MyMaze maze = new MyMaze(m, n);
		DisjSets djSets = new DisjSets(m*n);


		maze.printMaze();

		//start comparing random cells and union them if path does not exist
		//i.e.union(x,y) if find(x) != find(y)
		//keep doing until all cells are in one set i.e. djSets has only one -1 value.. i.e. one root

		boolean resultBool = maze.compareCells(m*n,  djSets);
		while(!resultBool)
			resultBool = maze.compareCells(m*n, djSets);

		System.out.println("FINAL MAZE:");
		maze.printMaze();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				JFrame jFrame = new MainFrame("MAZE CREATION", maze, m, n);
				jFrame.setVisible(true);
				jFrame.setSize(800, 800);
				jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);

				try {
					MazeGui window = new MazeGui(maze);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});



	}


}
