package demo;

import mazeGenerator.*;
import search.*;
/**
 * @file  Demo.java

 * 
 * @author 	Shira Ziv
 * 
 * @description	This class is responsible to check the search algorithms (BFS, DFS)
 * 
 * @date    02/09/2016
 */

/** Demonstrates the 2 algorithms of searching: BFS and DFS. */
public class Demo 
{

	/** 
	   * Demonstrates the 2 algorithms of searching.
	   * Creats simple maze 3d, printing it and outputs how many nodes evalutes by each algorithm.
	  */
	public void run() 
	{
		Maze3d maze = new GrowingTreeGenerator().generate(7, 10, 1);		//generating a 3D maze.
		System.out.println(maze);;											//printing the maze.
		

		Maze3dSearchable m = new Maze3dSearchable(maze);

	
	
		System.out.println("The start position :");
		System.out.println(m.getStartState());
		System.out.println("The goal position :");
		System.out.println(m.getGoalState());
		
		//creating a new BFS search algorithms 
		BFS<Position> myBFSAlg =new BFS<Position>();
		// using the BFS instance to search a solution of the maze
		Solution<Position> BFSsolution = myBFSAlg.search(m);			
		System.out.println("The number of States BFS has evaluated is: "+ myBFSAlg.getNumberOfNodesEvaluated());		
		for(int i=0;i<BFSsolution.getSolution().size();i++)
			System.out.print(BFSsolution.getSolution().get(i).getState());

		//creating a new DFS search algorithms 
		DFS<Position> myDFSalg =new DFS<Position>();
		// using the BFS instance to search a solution of the maze
		Solution<Position> DFSsolution = myDFSalg.search(m);			
		System.out.println("The number of States DFS has evaluated is: "+ myDFSalg.getNumberOfNodesEvaluated());	
		for(int i=0;i<DFSsolution.getSolution().size();i++)
			System.out.print(DFSsolution.getSolution().get(i).getState());
		}

	
}