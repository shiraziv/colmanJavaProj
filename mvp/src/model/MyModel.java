package model;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import demo.Demo;
import mazeGenerator.*;
import search.*;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import properties.*;
import properties.PropertiesLoder;


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
	
	/**
	 * Constractor
	 * @param c- a model
	 */
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
			setChanged();
			notifyObservers(new File(path).list());
		}
		else
		{
			setChanged();
			notifyObservers("This dir is not exist");
		}
	}
	
	@Override
	public void generateMaze(String[] args)
	{
			String name = args[0];
			int x,y,z;
			z = Integer.parseInt(args[1]);
			y = Integer.parseInt(args[2]);
			x = Integer.parseInt(args[3]);
			executor.submit(new Callable<Maze3d>() {

				@Override
				public Maze3d call() throws Exception {
					GrowingTreeGenerator generator = new GrowingTreeGenerator();
					Maze3d maze = generator.generate(z,y,x);
					keeperMazes.put(name, maze);
					
					setChanged();
					notifyObservers("maze_ready " + name);		
					return maze;
				}
			});
	}
	@Override
	public Maze3d getMaze(String string)
	{	
	 return keeperMazes.get(string);
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
				setChanged();
				notifyObservers("Invalid parameter");
		
			}
			
			}catch (NumberFormatException e) {
				
				setChanged();
				notifyObservers("Invalid parameters.");
				
			}
				
			
		}
		else
			setChanged();
			notifyObservers("generate_3d_maze command must contain 3 argument\n");	
		setChanged();
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
						setChanged();
					}catch (Exception e){ 
						setChanged();
						notifyObservers("general error");
					}
					
					FileOutputStream fos = new FileOutputStream(fileName);
					fos.write(tempMaze.toByteArray());
					fos.close();							//compressing the maze into and writing it to the file.
					setChanged();
					notifyObservers(name + " maze saved to -> " + fileName + ".");
					} catch (FileNotFoundException e) {
						setChanged();
						notifyObservers("wrong file path");
					} catch (IOException e){
						setChanged();
						notifyObservers("general error");
					}
			}
			else{
				setChanged();
				notifyObservers("you entered wrong maze or file");
			}
		}
		else{
			setChanged();
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
			setChanged();
			notifyObservers(fileName + " loaded ");
			in.close();
			} catch (Exception e) {
				setChanged();
				notifyObservers("you entered wrong file or name");
				
			}
		}

		else{
			setChanged();
			notifyObservers("load_maze command must contain 3 argument ");
			
		}
	}

	@Override
	public void solve(String[] args)
	{	
		executor.submit(new Callable<Solution>() {

			@Override
			public Solution call() throws Exception {
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
				solutions.put(name,searcher.search(search));	
				return solutions.get(name);
				}
				else
				{
					setChanged();
					notifyObservers(name+ " maze is unavailable!");
					return null;
				}
				
			}
			
			else{
				setChanged();
				notifyObservers("Missing parameters.");
				return null;
			}
			
		}	
			
		});
	}

	@Override
	public Solution getSolution(String name) 
	{
		if(name != null)
		{
			if (keeperMazes.containsKey(name)){
				Solution<Position> sol= solutions.get(name);
				if(sol!=null)
					return sol;
				else{
					setChanged();
					notifyObservers("ERROR: there is no solution yet");
					return null;
				}
			}
		else{
			setChanged();
			notifyObservers("ERROR: the maze doesn't exits");
			return null;
		}
	}
	else{
		setChanged();
		notifyObservers("Error: the maze name is empty");
		return null;
	}
}
	@Override
	public void exit() {
		executor.shutdownNow();
		saveSolutions();
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
	@SuppressWarnings("unchecked")
	private void loadSolutions() {
		File file = new File("solutions.dat");
		if (!file.exists())
			return;
		
		ObjectInputStream ois = null;
		
		try {
			ois = new ObjectInputStream(new GZIPInputStream(new FileInputStream("solutions.dat")));
			keeperMazes = (HashMap<String, Maze3d>)ois.readObject();
			solutions = (Map<String, Solution<Position>>)ois.readObject();			
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
	
	
			
}

