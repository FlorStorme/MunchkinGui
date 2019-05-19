package gui2;

import domein.DomeinController;
import static domein.DomeinController.labels;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

/**
 *
 * @author flors
 */
public class RegistratieScherm extends Pane {

    private final DomeinController controller;
    private final HoofdPaneel hoofdPaneel;

    public RegistratieScherm(DomeinController controller, HoofdPaneel hoofdPaneel) {
        this.controller = controller;
        this.hoofdPaneel = hoofdPaneel;

        voegComponentenToe();
    }

    private final Label label = new Label();
    private final ComboBox aantal = new ComboBox();
    private final Button doorgaan = new Button();

    private void voegComponentenToe() {
       //aantal
        String[] keuzeAantal = {"3", "4", "5", "6"};
        aantal.setItems(FXCollections.observableArrayList(keuzeAantal));
        aantal.setPromptText(labels.getString("invoer_spelers"));
        aantal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int keuze = aantal.getSelectionModel().getSelectedIndex();
                controller.kiesAantal(keuze);

            }
        });
        aantal.setLayoutX(100);
        aantal.setLayoutY(10);
        
        // buton
        doorgaan.setLayoutX(100);
        doorgaan.setLayoutY(100);
        doorgaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hoofdPaneel.aantalGekozen();
            }
        });
        
        this.getChildren().addAll(aantal,doorgaan);
    }

}
