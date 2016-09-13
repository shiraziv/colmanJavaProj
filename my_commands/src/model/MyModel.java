package model;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.HashMap;

import Controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import mazeGenerator.Maze3d;
import mazeGenerator.Maze3dGenerator;
import mazeGenerator.SimpleMaze3dGenerator;
import search.BFS;
import search.DFS;
import search.Maze3dSearchable;
import search.Searchable;
import search.Searcher;
import search.Solution;


/**
 * @file MyModel.java
 * 
 * @author Shira Ziv
 * 
 * @description This class implements the model interface.
 * 				
 * @date    02/09/2016
 */
public class MyModel implements Model
{
	private Controller controller;
	private HashMap<String, Maze3d> keeperMazes = new HashMap<String,Maze3d>();
	private HashMap<String, Solution> solutions = new HashMap<String,Solution>();
	/**
	 * Constractor
	 * @param c- a controller.
	 */
	public MyModel(Controller c)
	{
		this.controller = c;
		
	}
	@Override
	public String[] getDir(String path)
	{
		File f = new File(path);
		return f.list();
	}
	@Override
	public void generateMaze(String[] args)
	{
		if(args.length == 5)
		{
			String name = args[1];
			int x,y,z;
			z = Integer.parseInt(args[2]);
			y = Integer.parseInt(args[3]);
			x = Integer.parseInt(args[4]);
			Maze3dGenerator m3dg= new SimpleMaze3dGenerator();
			Maze3d maze = m3dg.generate(z, y, x);
			this.keeperMazes.put(name, maze);
		}
			else
				controller.display("generate_3d_maze command must contain 4 argument\n");	
		}
	
	@Override
	public Maze3d getMaze(String string)
	{	
	 return keeperMazes.get(string);
	}
	@Override
	public int[][] getCross(String[] args)
	{	
		String index = args[1];
		String name = args[2];
		int[][] maze = null;
		if(args.length == 3)
		{
			try{
			switch (index) 
			{
			case "x":
			case "X":
				maze = keeperMazes.get(name).getCrossSectionByX(0);
				break;
			case "y":
			case "Y":
				maze = keeperMazes.get(name).getCrossSectionByY(0);	
				break;
			case "z":
			case "Z":
				maze = keeperMazes.get(name).getCrossSectionByZ(0);
				break;
			default:
				controller.display("Invalid parameter");
		
			}
			
			}catch (NumberFormatException e) {
				
				controller.display("Invalid parameters.");
				
			}
		}
		else
			controller.display("generate_3d_maze command must contain 3 argument\n");		
		return maze;
	}
	@Override
	public void saveMaze(String[] args)
	{
		if(args.length == 3)
		{
			String name = args[1];
			String fileName = args[2];
			
			Maze3d  tempMaze = this.keeperMazes.get(name);
			
			if(tempMaze != null)
			{
				try {
					
					byte[] mazeBytes = tempMaze.toByteArray();
					OutputStream out;
					try 
					{
						out = new MyCompressorOutputStream(new FileOutputStream(fileName));
						out.write(mazeBytes);
						out.flush();
						out.close();
					}
					catch (Exception e) 
					{
					}
					
					
					FileOutputStream fos = new FileOutputStream(fileName);
					fos.write(tempMaze.toByteArray());
					fos.close();							//compressing the maze into and writing it to the file.
				
					controller.display(name + " maze saved to -> " + fileName + ".");
					} catch (FileNotFoundException e) {
						controller.display("wrong file path");
					} catch (IOException e){
						controller.display("general error");
					}
			}
			else
				controller.display("Unavailable maze!");	
		}
	}
	@Override
	public void loadMaze(String[] args)
	{
		InputStream in;
	try {
		in = new MyDecompressorInputStream(new FileInputStream(args[1]));
		byte b[]=new byte[in.available()];
		in.read(b);
		in.close();
		Maze3d loaded=new Maze3d(b);
		keeperMazes.put(args[2], loaded);
	} catch (Exception e) 
	{
		//return false;
	}
//return true;
		
	}
//		if(args.length == 3)
//		{
//			String fileName = args[1];
//			String mazeName = args[2];
//			try{
//				FileInputStream fos = new FileInputStream(fileName);
//				byte [] buffer = new byte[fos.available()];
//				
////				int buffer = fos.read();
//				
//				//InputStream s = 
////				if (fos.read(buffer)==-1)
////				{
//					Maze3d  tempMaze = new Maze3d(buffer);
//					this.keeperMazes.put(fileName, tempMaze);
//					controller.display(fileName + " maze loaded.");
//					fos.close();
////				}	
////				else
////				{
////					controller.display("the requsted maze is too big!");
////				}
//			
//			}
//			catch (FileNotFoundException e) 
//			{
//				controller.display("wrong file path");
//			} 
//			catch (IOException e)
//			{
//				controller.display("general error");
//			}
//		}
//		else
//			controller.display("Missing parameters.");
//		
//		}
	@Override
	public void solve(String[] args)
	{		
		if(args.length == 3)
		{
			String name = args[1];
			String algorithm = args[2];
			Searcher searcher = null;
			Maze3d tempMaze = keeperMazes.get(name);
			if(tempMaze !=null) 
			{
				Searchable search = new Maze3dSearchable(keeperMazes.get(name));
				switch (algorithm) 
				{
				case "BFS":
					searcher =new BFS();
					break;
				case "DFS":
					searcher =new DFS();
					break;
				}
			this.solutions.put(name,searcher.search(search));	
			}
			else
			{
				controller.display(name+ " maze is unavailable!");
			}
		}
		else
			controller.display("Missing parameters.");
			
	}


	@Override
	public Solution getSolution(String name) 
	{
		return this.solutions.get(name);
	}
}