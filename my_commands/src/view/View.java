package view;

import mazeGenerator.Maze3d;
import search.Solution;

/**
 * @file View.java
 * 
 * @author Shira Ziv
 * 
 * @description This class represents an interface to the view.
 * 				
 * @date    02/09/2016
 */
public interface View 
{
	/**
	 * This function is responsible to display a string array to the out stream 
	 * 
	 * @param str-the string array to display
	 */
	public void displayData(String[] str);
	/**
	 * This function is responsible to get user command and forward it to the controller and to display it.
	 *
	 */
	public void getUserCommand();
	/**
	 * This function is responsible to display a string to the out stream 
	 * 
	 * @param str-the string  to display
	 */
	public void displayData(String str);
	/**
	 * This function is responsible to display a 3d maze to the out stream 
	 * 
	 * @param maze - the 3d maze to display
	 */
	public void displayData(Maze3d maze);
	/**
	 * This function is responsible to display a 2d maze to the out stream 
	 * 
	 * @param arr - the 2d maze to display (in int[][] instance)
	 */
	public void displayData(int[][] arr);
	
	/**
	 * This function is responsible to display a solution to a 3d maze
	 * 
	 * @param solution - the maze solution to display
	 * 
	 */	
	public void displayData(Solution solution)
	
	/**
	 * This function is responsible to display a string value we got as a parameter.
	 * 
	 * @param string - the string to display
	 * 
	 */	;
	public void display(String string);

}
