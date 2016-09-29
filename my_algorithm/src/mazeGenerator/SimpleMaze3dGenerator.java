package mazeGenerator;
import java.util.ArrayList;

import java.util.Random;
/**
 * @file  SimpleMaze3dGenerator.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class contains code that generates a simple 3d maze.
 * 
 * @date    02/09/2016
 */

public class SimpleMaze3dGenerator extends Generator  {

	@Override
	public Maze3d generate(int h, int r, int c) {
		Maze3d maze=new Maze3d(h,r,c);
		maze.randomEnterAndExit();
		// Initialize Random object.
		Random rand = new Random();
		int num = 0;
		
		maze.fillWall();			//creating all the walls in the maze(1 value)

		for(int i = 0 ; i < h ; i++ ){	//inserting 1 or 0 values into the maze randomly
			for(int j = 0 ; j < r ; j++){
				for(int k = 0 ; k < c ; k++){
					num = rand.nextInt(2);	
					maze.setCell(i, j, k, num);
				}
			}
		}	
		maze.erasePos(maze.getEnter());		//erase enter point
		maze.erasePos(maze.getExit());      //erase exit point
		Position pos = maze.getNextMove(maze.getEnter().getZ(),maze.getEnter().getY(),maze.getEnter().getX());
		maze.erasePos(pos);
		int direction = 0; //starting the direction we want to walk.
		while(!(pos.equals(maze.getExit())))// while the positions are not the same
		{
			direction = rand.nextInt(4)+ 1;		//choosing a randomly direction(high,row or column).
		
		switch (direction) {	//if column way is chosen
		case 1:
			if(pos.getX() >  maze.getExit().getX() && pos.getX() > 0) // exists after exit  point
				pos.setX(pos.getX() - 1);
			else if(pos.getX() < maze.getExit().getX() && pos.getX() < maze.getColumn())// exists before exit  point
				pos.setX(pos.getX() + 1);
			else
				break;
		case 2:					//if row way chosen
			if(pos.getY() > maze.getExit().getY() && pos.getY() >0)
					pos.setY(pos.getY() - 1);
			else if(pos.getY() < maze.getExit().getY() && pos.getY() < maze.getRow())
					pos.setY(pos.getY() + 1);
			else
				break;
		case 3:					//if high way chosen
			if(pos.getZ() > maze.getExit().getZ() && pos.getZ() > 0)
				pos.setZ(pos.getZ() - 1);
			else if(pos.getZ() < maze.getExit().getZ() && pos.getZ() < maze.getHigh())
				pos.setZ(pos.getZ() + 1);
			else
				break;
		default:
			break;
		}
		
		maze.erasePos(pos);
		
		}
		
		return maze;
		}


	}

