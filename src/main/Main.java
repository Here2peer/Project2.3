package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import controller.*;
import model.*;
import view.*;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        /*primaryStage.setTitle("2G2 Geld Wissel App");
        GeldWisselModel model = new GeldWisselModel();
        GeldWisselController controller = new GeldWisselController(model);
        GeldWisselView view = new GeldWisselView(controller, model);

        Scene s = new Scene(view);
        primaryStage.setScene(s);
        primaryStage.show();*/

        primaryStage.setTitle("2G2 Grid Games");
        ReversiModel model = new ReversiModel();
        ReversiController controller = new ReversiController(model);
        GameView view = new GameView(controller, model);

        Scene s = new Scene(view);
        primaryStage.setScene(s);
        primaryStage.show();
    }
}
