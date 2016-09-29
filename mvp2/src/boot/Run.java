package boot;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presenter.Presenter;
import properties.Properties;
import properties.PropertiesLoader;
import view.GeneralWindow;
import view.MyView;

public class Run {

	public static void main(String[] args) throws IOException {	

		Properties prop = PropertiesLoader.getInstance().getProperties();
		String viewString = prop.getViewForm();
		if (viewString.compareTo("GUI")==0)
		{
			GeneralWindow win = new GeneralWindow (1700,950);
			MyModel model = new MyModel();	
			Presenter presenter = new Presenter(model, win);
			model.addObserver(presenter);
			win.addObserver(presenter);
			win.start();	
		}
		else{
			BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(System.out);

			MyView view = new MyView(in, out);
			MyModel model = new MyModel();
			Presenter presenter = new Presenter(model, view);
			model.addObserver(presenter);
			view.addObserver(presenter);
			view.start();
		}
	}
}
