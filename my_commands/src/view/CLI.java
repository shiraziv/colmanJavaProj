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
	private Controller controller;

	/** 
	   *CLI Constructor.
	   * @param in - Input Source.
	   * @param out - Output Source.
	   * @param controller -  Controller.
	  */
	public CLI (BufferedReader in, PrintWriter out, Controller controller)
	{
		this.in = in;
		this.out = out;
		this.controller = controller;
	}
	
	/**
	 * This function is running in a parallel thread and is responsible 
	 * on getting commands from the user and mannage them.
	 * 
	 * @throws IOException
	 */
	public void start () throws IOException
	{
		Thread t = new Thread(new Runnable() {
			
		    @Override
		    public void run() {
	    		ArrayList<String> menu = controller.getMenu();
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
						controller.execute(str);			
					} 
					catch (IOException e) 
					{
						controller.getMenu();
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
			controller.getMenu();
			String str = "";
		}{
			
		}
	}
}