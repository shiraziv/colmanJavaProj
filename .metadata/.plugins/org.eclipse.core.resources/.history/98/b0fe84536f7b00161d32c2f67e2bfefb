package view;

import java.util.Arrays;


import mazeGenerator.Maze3d;
import search.Solution;
import Controller.Controller;

public class MyView implements View 
{
	private Controller controller;
	/**
	 * Constractor
	 * @param c- a controller.
	 */
	public MyView(Controller c)
	{
		this.controller = c;
	}
	@Override
	public void getUserCommand()
	{
		controller.execute("generate_maze maze 5 5 5");
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
		
		

}
