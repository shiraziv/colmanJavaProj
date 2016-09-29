package presenter;


import java.util.ArrayList;


import java.util.HashMap;

import javax.swing.text.Position;
import model.Model;
import search.Solution;
import view.View;
/**
 * @file CommonCommand.java

 * 
 * @author Shira Ziv
 * 
 * @description This class represents Manage commands according to the menu.
 * 				
 * @date    02/09/2016
 */
/**
 * Manage commands according to the menu.
 * @author Shira Ziv
 *
 */
public class CommandsManager
{
	
	private Model model;
	private View view;
	private ArrayList<Thread> threads = new ArrayList<Thread>();
	
	/**
	 * An Constractor, using menu commands to initialize .
	 * initialize model and view and the chosen command.
	 * @param model- manages all the data and presents algorithms.
	 * @param view- represents all the things which are presents to the user. 
	 */
	public CommandsManager(Model model, View view) 
	{
		this.model = model;
		this.view = view;
	}
	public HashMap<String, Command> getCommandsMap() {
		HashMap<String, Command> commands = new HashMap<String, Command>();	
		commands.put("dir", new DirCommand());
		commands.put("generate_maze", new GenerateMaze());
		commands.put("display", new Display());
		commands.put("display_cross_section", new DisplayCrossSection());
		commands.put("save_maze", new SaveMaze());
		commands.put("load_maze", new LoadMaze());
		commands.put("solve_maze", new Solve());
		commands.put("display_solution", new DisplaySolution());
		commands.put("exit", new Exit());
		return commands;
	}
	


	/**
	 * @file CommonCommand.java
	 * 
	 * @author Shira Ziv
	 * 
	 * @description This class represents a command that responsible to 
	 *              show the folders and files in specific dir the user typed.
	 * 				
	 * @date    02/09/2016
	 */
	class DirCommand implements Command 
	{
		
		@Override

		/**
		 * This function is responsible to show the current dir (folders and files)
		 * 
		 * @param args - the required user's dir
		 */
		public void doCommand(String[] args) 
		{
			if(args.length == 2){
				model.getDir(args[1]);
			}
			else{
				view.displayData("Invalid parameter");
			}
		}	
	}
	/**
	 * @file CommonCommand.java
	 * 
	 * @author Shira Ziv
	 * 
	 * @description This class represents a command that responsible to 
	 *              ask the model to create a maze and the view to show a message.
	 * 				
	 * @date    02/09/2016
	 */
	class GenerateMaze implements Command
	{
		@Override
		public void doCommand(String[] args)
		{
		  Thread t = new Thread(new Runnable() {
		
		    @Override
		    public void run() {
		    	try{	
		    	model.generateMaze(args);
		    	}
		    	catch(NumberFormatException e)  
				{  
					view.displayData("Invalid parameters");
				}
		    }
		  });
		  threads.add(t);
		  t.start();
		  
		}
	}
	/**
	 * @file CommonCommand.java
	 * 
	 * @author Shira Ziv
	 * 
	 * @description This class represents a command that responsible to 
	 *              ask the model to print a maze and the view to show it for the user.
	 * 				
	 * @date    02/09/2016
	 */
	class Display implements Command
	{
		@Override
		public void doCommand(String[] args) 
		{	
			if(model.getMaze(args[0])!= null)
				view.displayData(model.getMaze(args[0]));
			else
				view.displayData("There is no maze to print");
				
		}
	}
	/**
	 * @file  CommonCommand.java
	 * 
	 * @author Shira Ziv
	 * 
	 * @description This class represents a command that responsible to 
	 *              ask the model to display a specific cross section of specific maze and from the view to show it.
	 * 				
	 * @date    02/09/2016
	 */
	class DisplayCrossSection implements Command
	{
		@Override
		public void doCommand(String[] args) 
		{
			int [][] array = model.getCross(args);
			if (array != null){
				view.displayData(array);
			}
			else {
				view.displayData("you entered a wrong argument\n");
			}
		
	}
	}
	/**
	 * @file CommonCommand.java
	 * 
	 * @author Shira Ziv
	 * 
	 * @description This class represents a command that responsible to 
	 *              ask the model to compress an exist maze and save it to file
	 * 				
	 * @date    02/09/2016
	 */
	class SaveMaze implements Command
	{
		@Override
		public void doCommand(String[] args) 
		{
			model.saveMaze(args);
		}
	}
	/**
	 * @file CommonCommand.java
	 * 
	 * @author Shira Ziv
	 * 
	 * @description This class represents a command that responsible to 
	 *              ask the model to de-compress maze from file and insert to the saved mazes list
	 * 				
	 * @date    02/09/2016
	 */
	class LoadMaze implements Command
	{
		@Override
		public void doCommand(String[] args) 
		{
			model.loadMaze(args);
		 }
	}
	/**
	 * @file CommonCommand.java
	 * 
	 * @author Shira Ziv
	 * 
	 * @description This class represents a command that responsible to 
	 *              ask the model to solve a maze
	 * 				
	 * @date    02/09/2016
	 */
	class Solve implements Command
	{
	
		@Override
		public void doCommand(String[] args)
		{
			Thread t = new Thread(new Runnable() {
		
			@Override
		    public void run() {
		    	model.solve(args);
		    }
		   });
		  threads.add(t);
		  t.start();
			
		}	
	}
	/**
	 * @file CommonCommand.java
	 * 
	 * @author Shira Ziv
	 * 
	 * @description This class represents a command that responsible to 
	 *              ask the model to display a solution for specific maze
	 * 				
	 * @date   02/09/2016
	 */

	class DisplaySolution implements Command
	{
		@Override
		public void doCommand(String[] args) 
		{
			if (args.length == 2){
				Solution <Position> solution = model.getSolution(args[1]);
				if(solution!=null)
					view.displayData(solution);
			}
			else{
				view.displayData(" command must contain 2 argument\n");			
				}
	}
	}
	/**
	 * @file CommomCommand.java
	 * 
	 * @author Shira Ziv
	 * 
	 * @description This class represents a command that responsible to 
	 *             exit from the maze application
	 * 				
	 * @date    02/09/2016
	 */
	class Exit implements Command
	{
		@Override
		public void doCommand(String[] args) 
		{
			model.exit();
	}
	}
}