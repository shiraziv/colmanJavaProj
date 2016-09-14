package model;
import mazeGenerator.*;
import search.*;

/**
 * @file Model.java
 * 
 * @author Shira Ziv
 * 
 * @description Defines an interface to the model - what every Model can do.
 * 				
 * @date    02/09/2016
 */
public interface Model 
{	
	/**
	 * This function returns all files and directories under the path we are getting as a parameter.
	 * 
	 * @param path- users' path choice.
	 *
	 */
	public String[] getDir(String path);
	/**
	 * This function is responsible on generating 3d maze.
	 * 
	 * @param args- users' command.
	 *
	 */
	public void generateMaze(String[] args);

	/**
	 * This function is responsible to return a 3d maze according to its name.
	 * 
	 * @param args- users' command.
	 * 
	 * @return the maze3d instance.
	 */
	public Maze3d getMaze(String string);
	
	/**
	 * This function returns a specific cross section of specific maze.
	 * 
	 * @param args- users' command.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public int[][] getCross(String[] args);
	/**
	 * This function is responsible to compress an request maze to specific outfile
	 * 
	 * @param args- users' command.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
 
	public void saveMaze(String[] args);
	/**
	 * This function is responsible to de-compress a maze from input file, and save it
	 * 
	 * @param args- users' command.
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void loadMaze(String[] args);

	/**
	 * This function is responsible to solve specific maze with specific algorithm.
	 * 
	 * @param args- users' command.
	 */
	public void solve(String[] args);
	/**
	 * This function is responsible to get the solution of specific maze
	 * 
	 * @param name - maze's name
	 * @return - the solution instance
	 */
	public Solution getSolution(String name);
}
