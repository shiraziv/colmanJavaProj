package view;


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

import properties.PropertiesLoder;
/**
 * @file CommonCommand.java
 * 
 * @author Shira Ziv
 * 
 * @description This class represents startWindow
 * 				
 * @date    02/09/2016
 * */
public class StartWindow extends BasicWindow implements Runnable {
	
	@Override
	protected void initWidgets() {
		
		this.shell = new Shell(display, SWT.TITLE | SWT.CLOSE | SWT.RESIZE);
		this.shell.setText("Start Menu");

		this.shell.setLayout(new GridLayout(7, false));
		this.shell.setSize(1500, 1500);
		this.shell.setBackgroundImage(new Image(null, "lib/images/background.jpg"));	
		this.shell.setBackgroundMode(SWT.INHERIT_DEFAULT | SWT.FILL);
		
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
		
		// Header Titles
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label lblHead = new Label(shell, SWT.BOLD);
		FontData fontData = lblHead.getFont().getFontData()[0];
		lblHead.setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+15, SWT.BOLD)));
		lblHead.setText("Pokemon catch Maze");
		lblHead.setForeground(new Color(display, 255,255,255));
		lblHead.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 4, 1));
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Label labelMiniHeader = new Label(shell, SWT.BOLD);
		fontData = labelMiniHeader.getFont().getFontData()[0];
		labelMiniHeader.setFont(new Font(display, new FontData(fontData.getName(), fontData.getHeight()+7, SWT.BOLD)));
		labelMiniHeader.setText("Please Choose a view type:");
		labelMiniHeader.setForeground(new Color(display, 255,255,255));
		labelMiniHeader.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false));
		
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		new Label(shell, SWT.NONE);
		
		Image imageGUI = new Image(display, "lib/images/gui.png");
		Image imageCLI = new Image(display, "lib/images/cli.png");
		Image imagePok = new Image(display, "lib/images/pocImage.jpg");
		
		Label labelPok = new Label(shell, SWT.NONE);
		labelPok.setLayoutData(new GridData(SWT.BEGINNING, SWT.CENTER, true, false));
		labelPok.setImage(imagePok);
		

		Label labelGUI = new Label(shell, SWT.PUSH);
		labelGUI.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, true, false));
		labelGUI.setImage(imageGUI);
		labelGUI.addMouseListener(new MouseListener() {
			
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