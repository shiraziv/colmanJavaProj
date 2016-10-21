package view;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;


import Controller.Command;
import Controller.Controller;

/**
* @file CLI.java
* 
* @author Shira Ziv
* 
* @description This class is responsible on the command line interface of the project
* 				
* @date    05/09/2016
*/
public class CLI
{
	private BufferedReader in;
	private PrintWriter out;
	private HashMap<String, Command> commands;


	/** 
	   *CLI Constructor.
	   * @param in - Input Source.
	   * @param out - Output Source.
	   * @param controller -  Controller.
	  */
	
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
	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
	}

	/**
	 * This function is running in a parallel thread and is responsible 
	 * on getting commands from the user and mannage them.
	 * 
	 * @throws IOException
	 */
	public void start() throws IOException
	{
		Thread t = new Thread(new Runnable() {
			
		    @Override
		    public void run() {
	    		ArrayList<String> menu = getMenu();
	    		String str = "";
	    		do 
				{
	    			// gets strings until the user will type "exit" string
					try 
					{
						for(String s:menu)
						{
							out.println(s);
						}
						out.flush();
						str = in.readLine();
						String arr[] = str.split(" ");
						String command = arr[0];
						if(!commands.containsKey(command)) {
							out.println("Command doesn't exist");
						}
						else {
							String[] args = null;
							if (arr.length > 1) {
								Command cmd = commands.get(command);
								cmd.doCommand(arr);	
							}
						}
					}
				
					catch (IOException e) 
					{
						getMenu();
						str = "";
					}
				} while (!str.equals("exit"));
		    	
		    }
		  });
		t.start();
		try{
		t.join();
		}
		catch (Exception e) {
			getMenu();
			String str = "";
		}{
			
		}
	}
}