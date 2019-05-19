package gui;

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
public class TaalScherm extends Pane {

    private final DomeinController controller;
    private final HoofdPaneel hoofdPaneel;

    public TaalScherm(DomeinController controller, HoofdPaneel hoofdPaneel) {
        this.controller = controller;
        this.hoofdPaneel = hoofdPaneel;
        
        voegComponentenToe();
    }

    private final ComboBox lang = new ComboBox();
    private final Label chosenLang = new Label();
    private final Button doorgaan = new Button();

    private void voegComponentenToe() {
        lang.setItems(FXCollections.observableArrayList(controller.getLanguage()));
        lang.setPromptText("Choose a language");
        doorgaan.setText("continue");
        lang.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int keuze = lang.getSelectionModel().getSelectedIndex();
                controller.kiesTaal(keuze);
                chosenLang.setText(labels.getString("toon_gekozen_taal"));
                doorgaan.setText(labels.getString("doorgaan"));
            }
        });
        lang.setLayoutX(100);
        lang.setLayoutY(10);
        chosenLang.setLayoutX(100);
        chosenLang.setLayoutY(60);
        doorgaan.setLayoutX(100);
        doorgaan.setLayoutY(100);
        doorgaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hoofdPaneel.taalGekozen();
            }
        });
        this.getChildren().addAll(lang, chosenLang, doorgaan);
    }
}
