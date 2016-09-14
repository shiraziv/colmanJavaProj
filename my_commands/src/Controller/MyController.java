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
	public void SetViewModel(View v, Model m)
	{
		this.view = v;
		this.model = m;
		commonCommand = new CommonCommand(model, view);
	}
	@Override
	public void displayData(String[] str) 
	{
		view.displayData(str);
	}
	
	@Override
	public void execute(String command) 
	{
		try
		{
			commonCommand.executeCommand(command);	
		}
		catch (IllegalArgumentException e) 
		{
			view.displayData("Wrong command");
		}

	}
	public ArrayList<String> getMenu()
	{
		return commonCommand.getMenu();
	}
	@Override
	public void display(String string) {
		view.displayData(string);
		
	}
}




