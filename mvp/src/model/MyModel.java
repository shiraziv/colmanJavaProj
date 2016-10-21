package model;


import java.io.ByteArrayOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import javax.swing.text.Position;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import mazeGenerator.GrowingTreeGenerator;
import mazeGenerator.Maze3d;
import mazeGenerator.Maze3dGenerator;
import mazeGenerator.SimpleMaze3dGenerator;
import properties.Properties;
import properties.PropertiesLoder;
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
public class MyModel extends Observable implements Model
{
	private ExecutorService executor;
	private HashMap<String, Maze3d> keeperMazes = new HashMap<String,Maze3d>();
	private Map<String, Solution<Position>> solutions = new ConcurrentHashMap<String, Solution<Position>>();
	private Properties properties;
	
	public MyModel() {
		properties = PropertiesLoder.getInstance().getProperties();
		executor = Executors.newFixedThreadPool(properties.getNumOfThreads());
		loadSolutions();
	}
	
	@Override
	public void getDir(String path)
	{
	File file = new File(path);
	if(file.exists())
	{
		notifyObservers(new File(path).list());
	}
	else
	{
		notifyObservers("This dir is not exist");
	}
	}
	
	@Override
	public int[][] getCross(String[] args)
	{	

		int[][] maze = null;
		if(args.length == 3)
		{
			String index = args[1];
			String name = args[2];
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
				notifyObservers("Invalid parameter");
		
			}
			
			}catch (NumberFormatException e) {
				
				notifyObservers("Invalid parameters.");
				
			}
				
			
		}
		else
			notifyObservers("generate_3d_maze command must contain 3 argument\n");		
		return maze;
	}
	@SuppressWarnings("unchecked")
	private void loadSolutions() {
		File file = new File("solutions.dat");
		if (!file.exists())
			return;
		
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new GZIPInputStream(new FileInputStream("solutions.dat")));
			keeperMazes = (HashMap<String, Maze3d>)ois.readObject();
			solutions = (HashMap<String, Solution<Position>>)ois.readObject();		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
	}
	
	@Override
	public Solution<Position> getSolution(String name){
		return this.solutions.get(name);
	}
	
	@Override
	public void solve(String[] args)
	{		
		if(args.length == 3)
		{
			executor.execute(new Runnable() {
				String name = args[1];
				String algorithm = args[2];
				@Override
				public void run() {
					Searcher<Position> searcher;
					Searchable search = new Maze3dSearchable(keeperMazes.get(name));
					if(properties.getSearchingAlgorithm().equals("BFS"))
						searcher = new BFS<Position>();
					else if(properties.getSearchingAlgorithm().equals("DFS"))
						searcher = new DFS<Position>();
					else{
						notifyObservers("Invalid searching algorithms");
						return;
					}
						
					Solution<Position> sol = searcher.search(search);
					solutions.put(name, sol);
					
					setChanged();
					notifyObservers("solved_maze " + name);	
					
				}
			
			});
		}
	}

	@Override
	public void generateMaze(String[] args)
	{
		if(args.length == 5)
		{
			try {
				String name = args[1];
				int x,y,z;
				z = Integer.parseInt(args[2]);
				y = Integer.parseInt(args[3]);
				x = Integer.parseInt(args[4]);
				executor.submit(new Callable<Maze3d>() {

					@Override
					public Maze3d call() throws Exception {
						Maze3dGenerator generator;
						if(properties.getGenerationAlgorithm().equals("GrowingTree")){
							generator = new GrowingTreeGenerator();
						}
						else {
							generator = new SimpleMaze3dGenerator();
						}
						Maze3d maze = generator.generate(z,y,x);
						keeperMazes.put(name, maze);
						System.out.println(maze);
						setChanged();
						notifyObservers("maze_ready " + name);		
						return maze;
					}
					
				});
			} catch (NumberFormatException e) {
				
				notifyObservers("Invalid parameters.");
			}
		}
			else
				notifyObservers("generate_3d_maze command must contain 4 argument\n");	
		}
	
	
	@Override
	public Maze3d getMaze(String string)
	{	
	 return keeperMazes.get(string);
	}
	
	private void saveSolutions() {
		ObjectOutputStream oos = null;
		try {
		    oos = new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("solutions.dat")));
			oos.writeObject(keeperMazes);
			oos.writeObject(solutions);			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void exit() {
		executor.shutdownNow();
		saveSolutions();	
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
					}catch (Exception e){ 
						notifyObservers("general error");
					}
					
					FileOutputStream fos = new FileOutputStream(fileName);
					fos.write(tempMaze.toByteArray());
					fos.close();							//compressing the maze into and writing it to the file.
				
					notifyObservers(name + " maze saved to -> " + fileName + ".");
					} catch (FileNotFoundException e) {
						notifyObservers("wrong file path");
					} catch (IOException e){
						notifyObservers("general error");
					}
			}
			else{
				notifyObservers("you entered wrong maze or file");
			}
		}
		else{
			notifyObservers("save_maze command must contain 3 argument");	
		}
	}
	
	@Override
	public void loadMaze(String[] args)
	{
		if(args.length == 3)
		{
			String fileName  = args[1];
			String mazeName = args[2];
			InputStream in;
		try {
			in = new MyDecompressorInputStream(new FileInputStream(fileName));
			byte b[]=new byte[9000];
			in.read(b);
			
			Maze3d loaded=new Maze3d(b);
			keeperMazes.put(mazeName, loaded);
			notifyObservers(fileName + " loaded ");
			in.close();
			} catch (Exception e) {
				notifyObservers("you entered wrong file or name");
				
			}
		}

		else{
			notifyObservers("load_maze command must contain 3 argument ");
			
		}
	}
}