package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * @file CommonCommand.java
 * 
 * @author Shira Ziv
 * 
 * @description This class represents  the GenerateWindow of Maze3d.
 * 				
 * @date    02/09/2016
 * */
public class GenerateWindow extends DialogWindow {

	public GenerateWindow(MyView view) {
		this.view = view;
	}
	
	@Override
	protected void initWidgets() {
		Shell GenerateShell = new Shell(display);

		GenerateShell.setLayout(new  GridLayout(2, false));
		Label lblName = new Label(GenerateShell, SWT.NONE);
		lblName.setText("Maze name: ");
		Text txtName = new Text(GenerateShell, SWT.BORDER);
		txtName.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));

		Label lblFloors = new Label(GenerateShell, SWT.NONE);
		lblFloors.setText("Floors: ");
		Text txtFloors = new Text(GenerateShell, SWT.BORDER);
		txtFloors.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));


		Label lblRows = new Label(GenerateShell, SWT.NONE);
		lblRows.setText("Rows: ");
		Text txtRows = new Text(GenerateShell, SWT.BORDER);
		txtRows.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));


		Label lblColumns = new Label(GenerateShell, SWT.NONE);
		lblColumns.setText("Columns: ");
		Text txtColumns = new Text(GenerateShell, SWT.BORDER);
		txtColumns.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false));
		Button GenerateMaze = new Button(GenerateShell, SWT.PUSH);
		GenerateShell.setDefaultButton(GenerateMaze);
		GenerateMaze.setLayoutData(new GridData(SWT.CENTER, SWT.CENTER, true, false, 2, 1));
		GenerateMaze.setText("Generate maze");

		GenerateShell.setText("Generate Maze");
		GenerateShell.setSize(400,250);
		GenerateShell.open();

		GenerateMaze.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				int floors = 0;
				int rows = 0;
				int cols = 0;
				String mazeName = null;
				try {
					floors = Integer.parseInt(txtFloors.getText());
					rows = Integer.parseInt(txtRows.getText());
					cols = Integer.parseInt(txtColumns.getText());
					mazeName = txtName.getText();
				} catch (NullPointerException | NumberFormatException e) {
					view.displayMessage("Invalid arguments.");
				}
				view.displayMessage("generate_maze " + mazeName + " " + floors + " " + rows + " " + cols);
				shell.dispose();
			}

			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) { }
		});

	}

}