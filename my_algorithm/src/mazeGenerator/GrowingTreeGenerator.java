package mazeGenerator;
import java.util.ArrayList;

import java.util.Random;
/**
 * @file  GrowingTreeGenerator.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class contains code that generates a 3d maze according to Growing Tree algorithm.
 * 
 * @date    02/09/2016
 */

public class GrowingTreeGenerator extends Generator{
	
	private ArrayList<Position> cells;
	@Override
	public Maze3d generate(int h, int r, int c) {
		
		boolean[][][] visited = new boolean[h][r][c];		
		for(int i = 0 ;i <h ; i++ ){
			for(int j = 0 ; j < r ; j++){
				for(int k = 0 ; k < c ; k++){			//creating a maze with false values(unvisited cells).
					visited[i][j][k] = false;
			}
		}
	}
		Maze3d maze3d = new Maze3d(h,r,c);
		ArrayList<Position> cells = new ArrayList<Position>();
		maze3d.fillWall();// filing the maze with walls
		Position currentCell = maze3d.getRandomCell();//getting a random open cell to start with
		maze3d.erasePos(currentCell);	//marking the cell as visited
		visited[currentCell.getZ()][currentCell.getY()][currentCell.getX()] = true;//marked as visited
		cells.add(currentCell);
		
		Random rand = new Random();
		int choose = rand.nextInt(2) + 1;

		while (!cells.isEmpty())
		{
			if (choose == 2)
			{
				currentCell = cells.get(cells.size()-1);// Choose the last cell in  c 
			}
			else
			{
				currentCell = cells.get(rand.nextInt(cells.size())); // choose randomly the next neighbor 
			}
			ArrayList<Position> neighbors = maze3d.getUnvisitedNeighbors(visited, currentCell);	//getting each cell's unvisited neighbor cells
			
			if(neighbors.size() > 0)	//if there are any neighbors left to visit
			{
				Position newCell = neighbors.get(rand.nextInt(neighbors.size()));	//chose a random neighbor
				maze3d.erasePos(newCell); //erase the neighbor of the currentCell in order to remove the wall between them.
				cells.add(newCell);
				currentCell = newCell;//making the current cell the new neighbor
				visited[currentCell.getZ()][currentCell.getY()][currentCell.getX()] = true;
			}
			else 
				cells.remove(currentCell);	
			choose = rand.nextInt(2) + 1;
		}
		
		maze3d.randomEnterAndExit();
		return  maze3d;
	}
	public void setDone(boolean b) {
		// TODO Auto-generated method stub
		
	}
}