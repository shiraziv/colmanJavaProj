package view;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import Presenter.Command;
import mazeGenerator.Maze3d;
import search.Solution;

/**
* @file CLI.java
* 
* @author Shira Ziv
* 
* @description This class is responsible on the command line interface of the project
* 				
* @date    05/09/2016
*/
public class CLI extends Observable implements View  {
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> commands;
	
	public CLI(){
		
	}
	
	public CLI(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
	}
	
	/**
	 * The method represents a menu to the user.
	 * return an arrayList which contains a command the user ask to execute.
	 */

	private void printMenu() {
		out.print("Choose command: \n");
		displayMessage("\tdir <path>\n");
		displayMessage("\tgenerate_maze <name> <params>\n");
		displayMessage("\tdisplay <name>\n");
		displayMessage("\tdisplay_cross_section <name> <axis> <index>\n");
		displayMessage("\tsave_maze <name> <file_name>\n");
		displayMessage("\tload_maze <file_name> <name>\n");
		displayMessage("\tsolve <name> <algorithm>\n");
		displayMessage("\tdisplay_solution <name>\n");
		displayMessage("\texit\n");
		out.flush();
	}
	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
	}

	
	/**
	 * This function is running in a parallel thread and is responsible 
	 * on getting commands from the user and mannage them.
	 */
	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
				
					printMenu();
					try {
						String commandLine = in.readLine();
						String arr[] = commandLine.split(" ");
						
						sendCommand(arr);
						
						if (commandLine.equals("exit"))
							System.exit(0);
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}			
		});
		thread.start();		
	}


	@Override
	public void notifyMazeIsReady(String name) {
		out.write("Maze " + name + " is ready.");
		out.flush();
	}

	@Override
	public void displayMaze(String name, Maze3d maze) {
		out.write(name + "\n");
		out.write(maze.toString());
		out.flush();
	}

	@Override
	public void displayMessage(String msg) {
		out.write(msg);
		out.flush();
	}

	@Override
	public void sendCommand(String [] arg) {
		setChanged();
		notifyObservers(arg);
	}
	
}