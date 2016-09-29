package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;

import mazeGenerator.Maze3d;
import search.Solution;
import Controller.Command;
import Controller.Controller;

/**
 * @file View.java
 * 
 * @author Shira Ziv
 * 
 * @description This class represents an interface to the view.
 * 				
 * @date    02/09/2016
 */
public class MyView implements View 
{
	private BufferedReader in;
	private PrintWriter out;
	private CLI cli;
	private Controller controller;

	public MyView(BufferedReader in, PrintWriter out) {
		this.in = in;
		this.out = out;
				
		cli = new CLI(in, out);
	}
	
	public void setController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void displayData(String str)
	{
		System.out.println(str);
	}
	@Override
	public void displayData(String[] str)
	{
		for (String string : str) 
		{
			displayData(string);
		}
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
	}
	
	@Override
	public void displayData(Solution solution)
	{
		solution.print();
	}
	@Override
	public void displayData(Maze3d maze) {
		if(maze != null)
		{
			System.out.println(maze);

		}
		else {
			System.out.println("your data is not known maze");
		}
	}
	@Override
	public void start() throws IOException {
		// TODO Auto-generated method stub
		cli.start();
	}
	@Override
	public void setCommands(HashMap<String, Command> commands) {
		cli.setCommands(commands);
	}



		
		

}
