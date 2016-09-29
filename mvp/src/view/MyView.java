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
import properties.PropertiesLoder;
import search.Solution;

public class MyView extends Observable implements View {
	
	private BufferedReader in;
	private PrintWriter out;
	private CLI cli;
	private MazeWindow mazeWindow;
	
	public MyView(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
		this.cli = null;
		this.mazeWindow = null;
		
		String chosenView = chooseViewTypeFromProperties();
		if (chosenView == "CLI")
			this.cli = new CLI(in, out);
		else
			this.mazeWindow = new MazeWindow(this);
	}	
	
	/**
	 * choose which UI from the properties: CLI/GUI
	 * @return the choose 
	 */
	private String chooseViewTypeFromProperties() {
		switch (PropertiesLoder.getInstance().getProperties().getViewType().toLowerCase()) {
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
		

}
