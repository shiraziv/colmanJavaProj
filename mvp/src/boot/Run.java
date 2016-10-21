package boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import Presenter.*;
import view.MyView;
import view.StartWindow;

public class Run {

	public static void main(String[] args) throws IOException {		
		StartWindow startWin = new StartWindow();
		startWin.run();
		MyModel model = new MyModel();
		MyView view = new MyView(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out, true));
		MyPresenter presenter = new MyPresenter(model, view);
		view.addObserver(presenter);
		model.addObserver(presenter);
		view.start();
	}

}