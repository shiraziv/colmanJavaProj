package Controller;

import java.util.ArrayList;

import model.Model;
import view.View;

/**
 * @file MyController.java
 * 
 * @author Shira Ziv
 * 
 * @description This class implements the controller interface.
 * 				
 * @date    02/09/2016
 */

public class MyController implements Controller
{
	private View view;
	private Model model;
	private CommonCommand commonCommand;
	
	public MyController(View view, Model model) {
		this.view = view;
		this.model = model;
		CommonCommand commandsManager = new CommonCommand(model, view);
		view.setCommands(commandsManager.getCommandsMap());
	}
	@Override
	public void displayData(String[] str) 
	{
		view.displayData(str);
	}
	
	@Override
	public void display(String string) {
		view.displayData(string);
		
	}
}




