package presenter;


import java.util.HashMap;

import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;

public class Presenter implements Observer {
	private Model model;
	private View view;
	private CommandsManager commandsManager;
	private HashMap<String, Command> commands;
	
	
	public Presenter(Model model, View view) {
		this.model = model;
		this.view = view;
			
		commandsManager = new CommandsManager(model, view);
		commands = commandsManager.getCommandsMap();
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if (o==view){
			String commandLine = (String)arg;

			String arr[] = commandLine.split(" ");
			String command = arr[0];

			if(!commands.containsKey(command)) {
				view.displayData("Command doesn't exist");			
			}
			else {
				if (command.equals("exit"))
				{
					Command cmd = commands.get(command);
					cmd.doCommand(null);
				}
				else{
					String[] args = null;
					if (arr.length > 1) {
						String commandArgs = commandLine.substring(
								commandLine.indexOf(" ") + 1);
						args = commandArgs.split(" ");
						Command cmd = commands.get(command);
						cmd.doCommand(args);
					}
					else {
						view.displayData("Invalid parameters");
					}	
				}
			}
		}
		else if (o==model){
			view.displayData((String)arg);
		}
	}
}
