package boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import Controller.*;
import mazeGenerator.Maze3d;
import mazeGenerator.SimpleMaze3dGenerator;
import model.*;
import view.*;

public class Run {

	public static void main(String[] args) throws IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		PrintWriter out = new PrintWriter(System.out);
				
		View view = new MyView(in, out);
		Model model = new MyModel();
		
		Controller controller = new MyController(view, model);
		view.setController(controller);
		model.setController(controller);
		
		view.start();

	}

}
