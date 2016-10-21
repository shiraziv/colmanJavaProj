package Presenter;


import java.util.HashMap;
import java.util.Observable;

import java.util.Observer;

import model.Model;
import view.View;

/***
 * @file MyPresenter.java
 * 
 *  @author Shira Ziv
 * 
 * @description Presenter in MVP architecture.
 * The Presenter is Observer, he waits for notifications and reacts.
 * 
 * @param model Model Type (Observable)
 * @param view View Type (Observable)
 * @param commandsManager CommandsManager Type
 * @param commands HashMap<String, Command> Type
 * 
 * @date    02/09/2016
 *
 */
public class MyPresenter implements Observer {
	private Model model;
	private View view;
	private CommandsManager commandsManager;
	private HashMap<String, Command> commands;
	
	// Ctor
	public MyPresenter(Model model, View view) {
		this.model = model;
		this.view = view;
		commandsManager = new CommandsManager(model, view);
	}

	
	/**
	 * @file MyPresenter.java
	 * 
	 *  @author Shira Ziv
	 *  
	 * @description This method is called whenever the observed object is changed (hasChanged flag changed state or SetChanged() was made).
	 * An application calls an Observable object's notifyObservers method to have all the object's observers notified of the change.
	 * @param o Observable Type
	 * @param arg Object Generic Type
	 * 
	 * @date    02/09/2016
	 */
	@Override
	public void update(Observable o, Object arg) {
		String cmd = (String)arg;
		if(o == view)
			commandsManager.executeCommand(cmd);
		if(o == model)
			view.displayMessage(cmd);
	}
}
