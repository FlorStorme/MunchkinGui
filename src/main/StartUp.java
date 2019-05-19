package main;

import domein.DomeinController;
import gui.UC1_MaakSpelController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author flors
 */
public class StartUp extends Application {
    
    @Override
    public void start(Stage stage) {
        DomeinController controller = new DomeinController();
        UC1_MaakSpelController uc1 = new UC1_MaakSpelController(controller);
        Scene scene = new Scene(uc1, 600, 400);
        stage.setScene(scene);
        stage.setTitle("Munchkin");
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
