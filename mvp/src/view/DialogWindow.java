package view;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import view.MyView;

/**
 * @file CommonCommand.java
 * 
 * @author Shira Ziv
 * 
 * @description This class represents a dialog window
 * 				
 * @date    02/09/2016
 * */
public abstract class DialogWindow {

	protected Display display;
	protected Shell shell;
	protected MyView view;

	protected abstract void initWidgets();
	
	public void start(Display display) {
		this.display = display;
		initWidgets();
		this.shell = new Shell(this.display);
		this.shell.open();
	}
	
}
