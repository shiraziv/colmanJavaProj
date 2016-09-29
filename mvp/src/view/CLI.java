package view;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;



public class CLI extends Observable {
	private BufferedReader in;
	private PrintWriter out;	
	
	public CLI(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;		
	}
	/**
	 * The method represents a menu to the user.
	 * return an arrayList which contains a command the user ask to execute.
	 */
	public ArrayList<String> getMenu() {
		ArrayList<String> menu = new ArrayList<String>();
		menu.add("Please enter command:");
		menu.add("1. dir <path>");
		menu.add("2. generate_maze <name> <xAxis> <yAxis> <zAxis>");
		menu.add("3. display <name>");
		menu.add("4. display_cross_section <index{X/Y/Z}> <name>");
		menu.add("5. save_maze <name> <file name>");
		menu.add("6. load_maze <file name> <name>");
		menu.add("7. solve_maze <name> <algorithm>");
		menu.add("8. display_solution <name>");
		menu.add("9. exit");
		return menu;
	}
	
	/**
	 * This function is running in a parallel thread and is responsible 
	 * on getting commands from the user and mannage them.
	 * 
	 * @throws IOException
	 */
	public void start() {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
				
					getMenu();
					try {
						String commandLine = in.readLine();
						setChanged();
						notifyObservers(commandLine);
						
						if (commandLine.equals("exit"))
							break;
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}			
		});
		thread.start();		
	}
	
}


