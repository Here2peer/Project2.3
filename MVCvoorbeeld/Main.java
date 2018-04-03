import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.mijnbedrijf.geldwisselapp.controller.ControllerGeldwisselapp;
import nl.mijnbedrijf.geldwisselapp.model.ModelGeldwisselapp;
import nl.mijnbedrijf.geldwisselapp.view.ViewGeldwisselapp;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Geld wissel app");

		ModelGeldwisselapp model = new ModelGeldwisselapp();
		ControllerGeldwisselapp controller = new ControllerGeldwisselapp(model);
		ViewGeldwisselapp view = new ViewGeldwisselapp(controller, model);

		Scene s=new Scene(view);
		primaryStage.setScene(s);
		primaryStage.show();
	}
}
