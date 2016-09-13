package Controller;

import java.util.ArrayList;

import view.*;

import model.Model;

/**
 * @file Controller.java
 * 
 * @author Shira Ziv
 * 
 * @description Defines an interface to the controller-  what every Controller type must implement.
 * 				
 * @date    02/09/2016
 */
public interface Controller 
{
	/**
	 * Displaying the given string array using the View.
	 * @param string
	 */

	public void displayData(String[] str);
	/**
	 * Displaying the given string using the View.
	 * @param string
	 */
	public void display(String string);
	/**
	 * Displaying the given string using the View.
	 * @param string
	 */
	public void execute(String command);
	/**
	 * change model and view parameters.
	 * @param view  - a reference to the view object
	 * @param model - a reference to the model object
	 */
	public void SetViewModel(View view, Model m);
	/**
	 * represents a commands menu.
	 * returns a menu to the user.
	 */
	public ArrayList<String> getMenu();
}
