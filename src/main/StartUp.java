package main;

import domein.DomeinController;
import gui.HoofdPaneel;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author flors
 */
public class StartUp extends Application {
    
    @Override
    public void start(Stage stage)
    {
        DomeinController controller = new DomeinController();
        Scene scene = new Scene(new HoofdPaneel(controller), 600, 400);
        stage.setTitle("Munchkin");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String... args)
    {
        Application.launch(StartUp.class, args);
    }
    
}
