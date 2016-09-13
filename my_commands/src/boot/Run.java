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

		Controller controller = new MyController();
		View view = new MyView(controller);
		Model model = new MyModel(controller);
		controller.SetViewModel(view, model);
		CLI cli = new CLI(new BufferedReader(new InputStreamReader(System.in)),new PrintWriter(System.out),controller);
		cli.start();

	}

}
