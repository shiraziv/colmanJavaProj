package mazeGenerator;

/**
 * @file  Maze3dGenerator.java
 * 
 * @author 	Shira Ziv
 * 
 * @description	This class represents an interface to generate a 3d maze.
 * 
 * @date    02/09/2016
 */

public interface Maze3dGenerator {
	
	/**
	 * Generates a 3d maze according to the dimensions size. 
	 * 
	 * @param c- Size of X dimension.
	 * @param r- Size of Y dimension.
	 * @param h- Size of Z dimension.
	 * 
	 * @return  a valid 3d maze otherwise, null.
	 */
	public abstract Maze3d generate(int h,int r,int c);
	
	/**
	 * Calculate the time that takes to generate the maze.
	 * 
	 * @param column- Size of X dimension.
	 * @param row- Size of Y dimension.
	 * @param high- Size of Z dimension.
	 * 
	 * @return A string that describes how much time took to generate the maze.
	 */
	public String measureAlgorithmTime(int high,int row,int column );

}
