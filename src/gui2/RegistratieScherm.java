package gui2;

import domein.DomeinController;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author flors
 */
public class RegistratieScherm extends Pane{
    
    private final DomeinController controller;
    private final HoofdPaneel hoofdPaneel;

    public RegistratieScherm(DomeinController controller, HoofdPaneel hoofdPaneel) {
        this.controller = controller;
        this.hoofdPaneel = hoofdPaneel;
        
        voegComponentenToe();
    }
    
    private final Label label = new Label();

    private void voegComponentenToe() {
        label.setText("Fuck yeah, fuck scenebuilder");
        label.setLayoutX(100);
        label.setLayoutY(10);
        this.getChildren().addAll(label);
    }
    
}
