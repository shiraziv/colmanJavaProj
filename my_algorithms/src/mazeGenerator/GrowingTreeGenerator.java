package mazeGenerator;
import java.util.ArrayList;

import java.util.Random;
import java.util.Stack;
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
	
	@Override
	public Maze3d generate(int h, int r, int c) {
		int numOfVisitedCells = 0;		//counting the number of cells we have visited
		int totalCells = h*r*c;	
		Stack<Position> stack = new Stack<Position>();
		boolean[][][] visited = new boolean[h][r][c];		
		for(int i = 0 ;i <h ; i++ ){
			for(int j = 0 ; j < r ; j++){
				for(int k = 0 ; k < c ; k++){			//creating a maze with false values(unvisited cells).
					visited[i][j][k] = false;
			}
		}
	}
		Random rand = new Random();
		Maze3d maze = new Maze3d(h,r,c);
		maze.fillWall();
		Position currentCell = maze.getRandomCell();//getting a random open cell to start with
		maze.erasePos(currentCell);	//marking the cell as visited
		visited[currentCell.getZ()][currentCell.getY()][currentCell.getX()] = true;
		numOfVisitedCells++;
		
		while(numOfVisitedCells < totalCells)		//stops when we visited all the cells
		{
			ArrayList<Position> neighbors = maze.getUnvisitedNeighbors(visited, currentCell);	//getting each cell's unvisited neighbor cells
			
			if(neighbors.size() > 0)	//if there are any neighbors left to visit
			{
				Position newCell = neighbors.get(rand.nextInt(neighbors.size()));	//chose a random neighbor
				maze.erasePos(newCell); //erase the neighbor of the currentCell in order to remove the wall between them.
				stack.push(currentCell);	
				currentCell = newCell;//making the current cell the new neighbor	
				visited[currentCell.getZ()][currentCell.getY()][currentCell.getX()] = true;
				numOfVisitedCells++;
			}
			else if(!stack.isEmpty())
			{
				currentCell = stack.pop();
			}
			else
			{
				currentCell = maze.getNewUnvisitedCell(visited);		//jumping to a new random unvisited cell
				visited[currentCell.getZ()][currentCell.getY()][currentCell.getX()] = true;		//marking the cell; visited
				numOfVisitedCells++;
			}
			
		}
		maze.randomEnterAndExit();
		return maze;

			}

	}

