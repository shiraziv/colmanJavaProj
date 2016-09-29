package view;

/**
 * @file View.java
 * 
 * @author Shira Ziv
 * 
 * @description This class represents an interface to the view.
 * 				
 * @date    02/09/2016
 */
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Observable;
import java.util.Observer;

import mazeGenerator.Maze3d;
import properties.PropertiesLoader;
import properties.*;
import search.Solution;


public class MyView extends Observable implements View, Observer {
	
	private BufferedReader in;
	private PrintWriter out;
	private CLI cli;
	//private GeneralWindow win;

	public MyView(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
				
		cli = new CLI(in, out);
		cli.addObserver(this);
		
//		win = new GeneralWindow (1700,950);
//		win.addObserver(this);
		
	}	
	/**
	 * choose which UI from the properties: CLI/GUI
	 * @return the choose 
	 */
	private String chooseViewTypeFromProperties() {
		switch (PropertiesLoader.getInstance().getProperties().getViewForm().toLowerCase()) {
		case "gui":
			return "GUI";
		case "cli":
			return "CLI";

		}
		return null;
	}
	@Override
	public void displayData(String[] str)
	{
		for (String string : str) 
		{
			displayData(string);
		}
		out.flush();
	}
	
	@Override
	public void displayData(int[][] arr)
	{
		for(int[] i : arr){
			for(int j : i){
				System.out.print(j+" ");
			}
			System.out.println("");
		}
		System.out.println("");
		out.flush();
	}
	
	@Override
	public void displayData(Solution solution)
	{
		solution.print();
		out.flush();
	}
	@Override
	public void displayData(Maze3d maze) {
		if(maze != null)
		{
			System.out.println(maze);
			out.flush();

		}
		else {
			System.out.println("your data is not known maze");
			out.flush();
		}
	}


	@Override
	public void sendCommand(String arg) {
		setChanged();
		notifyObservers(arg);
	}

	@Override
	public void displayData(String str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void start() throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		// TODO Auto-generated method stub
		
	}	
		

}
