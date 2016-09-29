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


}
