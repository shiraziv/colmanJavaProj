package view;

import java.io.IOException;


import java.util.HashMap;
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
public interface View {
	public void notifyMazeIsReady(String name);
	public void displayMaze(String name, Maze3d maze);
	public void displayMessage(String msg);	
	public void start();
	public void sendCommand(String [] arg);
}



