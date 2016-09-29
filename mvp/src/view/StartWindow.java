package view;


import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import java.awt.event.MouseWheelListener;
import java.util.Observable;
import java.util.Properties;

import javax.sound.sampled.Clip;

import org.eclipse.swt.widgets.Button;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

import mazeGenerator.Maze3d;
import properties.PropertiesLoder;
import properties.PropertiesLoder;


public class StartWindow extends BasicWindow implements Runnable {
	

	private Image exit, musicPic, hint;
	private Properties p;
	private Maze3d maze;
	private MazeDisplay mazeDisplay;
	MouseWheelListener mouseZoomlListener;
	String nameMaze = null;
	Clip music;
	@Override
	protected void initWidgets() {
		this.shell = new Shell(display, SWT.TITLE | SWT.CLOSE | SWT.RESIZE);
		this.shell.setText("Start Menu");

		this.shell.setLayout(new GridLayout(7, false));
		this.shell.setSize(1500, 1500);
		
		Menu menuButton = new Menu(shell, SWT.BAR);
		shell.setMenuBar(menuButton);

		// File button in the bar
		MenuItem fileItem = new MenuItem(menuButton, SWT.CASCADE);
		fileItem.setText("Menu");
		
		// Drop down functions for file button
		Menu subMenu = new Menu(shell, SWT.DROP_DOWN);
		fileItem.setMenu(subMenu);

		MenuItem properties = new MenuItem(subMenu, SWT.PUSH);
		properties.setText("properties");		
		
		
		// Header Titles
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		
		
		Label lblHead = new Label(shell, SWT.BOLD);
		FontData fontData = lblHead.getFont().getFontData()[0];
		lblHead.setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+15, SWT.BOLD)));
		lblHead.setText("Welcome to Pockemon game");
		lblHead.setForeground(new Color(display, 255,255,255));
		lblHead.setLayoutData(new GridData(SWT.NULL, SWT.NULL, false, false, 4, 1));
		
		
		Label labelMiniHeader = new Label(shell, SWT.BOLD);
		fontData = labelMiniHeader.getFont().getFontData()[0];
		labelMiniHeader.setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+7, SWT.BOLD)));
		labelMiniHeader.setText("Please Choose a view type:");
		labelMiniHeader.setForeground(new Color(display, 255,255,255));
		labelMiniHeader.setLayoutData(new GridData(SWT.NULL, SWT.NULL, false, false));
		
		
		shell.setLayout(new GridLayout(2, false));

		Color blue = display.getSystemColor(SWT.COLOR_CYAN);
		Color yellow = display.getSystemColor(SWT.COLOR_YELLOW);
		shell.setBackground(yellow);


		//backgroand gui 
		
		mazeDisplay = new MazeDisplay(shell, SWT.NONE|SWT.DOUBLE_BUFFERED);		
		//mazeDisplay.setMazeData(maze);
		mazeDisplay.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true,50,50));
		final Image image=new Image(display,"lib/images/background.jpg");
		mazeDisplay.setBackgroundImage(image);
		mazeDisplay.setFocus();
		
		
		Rectangle rect = shell.getBounds();
		Rectangle boundries = display.getPrimaryMonitor().getBounds();
		int x = boundries.x + (boundries.width - rect.width) / 2;
		int y = boundries.y + (boundries.height - rect.height) / 2;
		shell.setLocation(x, y);
		
		// Listener for the closing X
		shell.addListener(SWT.Close, new Listener() {
			
			@Override
			public void handleEvent(Event arg0) {
				System.exit(0);
			}

		});
		
		
		Image imageGUI = new Image(display, "lib/images/gui.png");
		Image imageCLI = new Image(display, "lib/images/cli.png");
		Image imagePoc = new Image(display, "lib/images/pocImage.jpg");
		exit = new Image (null, "lib/images/EXIT_NEW.png");
		musicPic = new Image (null, "lib/images/MUSIC.jpg");
		hint = new Image (null, "lib/images/question.jpg");
		
		Button pockemonButton = new Button(shell, SWT.PUSH);
		pockemonButton.setImage(imagePoc);
		pockemonButton.setLayoutData(new GridData(SWT.None,SWT.NONE,false,false, 1, 1));
	
		Button cliButton = new Button(shell, SWT.PUSH);
		cliButton.setImage(imageCLI);
		cliButton.setLayoutData(new GridData(SWT.None,SWT.NONE,false,false, 1, 1));
		
		Button guiButton = new Button(shell, SWT.PUSH);
		guiButton.setImage(imageGUI);
		guiButton.setLayoutData(new GridData(SWT.None,SWT.NONE,false,false, 1, 1));
		

		Button musicButton = new Button(shell, SWT.PUSH);
		musicButton.setImage(musicPic);
		musicButton.setLayoutData(new GridData(SWT.None,SWT.NONE,false,false, 1, 1));
		

		Button hintButton = new Button(shell, SWT.PUSH);
		hintButton.setImage(hint);
		hintButton.setLayoutData(new GridData(SWT.None,SWT.NONE,false,false, 1, 1));
		
		Button exitButton = new Button(shell, SWT.PUSH);
		//exitButton.setText("EXIT");
		exitButton.setLayoutData(new GridData(SWT.None,SWT.NONE,false,false, 1, 1));
		exitButton.setBackground(blue);
		exitButton.setImage(exit);
		
		guiButton.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) { }
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				PropertiesLoder.getInstance().writeXml("GUI");
				shell.dispose();
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) { }
		});

		
		Label labelCLI = new Label(shell, SWT.PUSH);
		labelCLI.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, true, false));
		labelCLI.setImage(imageCLI);
		labelCLI.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseUp(MouseEvent arg0) { }
			
			@Override
			public void mouseDown(MouseEvent arg0) {
				PropertiesLoder.getInstance().writeXml("CLI");
				shell.dispose();
			}
			
			@Override
			public void mouseDoubleClick(MouseEvent arg0) { }
		});
	}


}
