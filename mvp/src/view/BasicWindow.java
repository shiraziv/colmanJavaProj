package view;

import java.util.Observable;


import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
/**
 * @file CommonCommand.java
 * 
 * @author Shira Ziv
 * 
 * @description This class represents a basic window
 * 				
 * @date    02/09/2016
 * */
public abstract class BasicWindow extends Observable implements Runnable {

	protected Display display;
	protected Shell shell;
	
 	public BasicWindow() {
 		super();
	}
 	
	/***
	 * Initialize widgets in window.
	 */
	protected abstract void initWidgets();
	
	public void start()
	{
		run();
	}
	
	@Override
	public void run() {
		display = new Display();
		shell = new Shell(display);

		initWidgets();
		
		shell.open();
		
		// Main loop, running until shell is disposed
		while(!shell.isDisposed()){
		
		   // 1. reads events and put them inside the queue.
		   // 2. dispatches the assigned listener
		   if(!display.readAndDispatch()){
		      display.sleep(); 
		   }
		
		}

		display.dispose();
	}

}