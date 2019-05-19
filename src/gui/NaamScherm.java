/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import static domein.DomeinController.labels;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/**
 *
 * @author jonad
 */
public class NaamScherm extends GridPane {

    private final DomeinController controller;
    private final HoofdPaneel hoofdPaneel;

    public NaamScherm(DomeinController controller, HoofdPaneel hoofdPaneel) {
        this.controller = controller;
        this.hoofdPaneel = hoofdPaneel;

        voegComponentenToe();
    }

    private final Label lbl1 = new Label();
    private final Label lbl2 = new Label();
    private final Label lbl3 = new Label();
    private final Label lbl4 = new Label();
    private final Label lbl5 = new Label();
    private final Label lbl6 = new Label();
    private final TextField txtInput1 = new TextField();
    private final TextField txtInput2 = new TextField();
    private final TextField txtInput3 = new TextField();
    private final TextField txtInput4 = new TextField();
    private final TextField txtInput5 = new TextField();
    private final TextField txtInput6 = new TextField();
    private final Button doorgaan = new Button();
    

    private void voegComponentenToe() {
        int aantal = controller.getAantal();
        for (int i = 0; i < aantal; i++) {
            switch (i) {
                case 0:
                    NaamScherm.setConstraints(lbl1, 0, i);
                    lbl1.setText(labels.getString("vraag_naam"));
                    NaamScherm.setConstraints(txtInput1, 1, i);
                    break;
                case 1:
                    NaamScherm.setConstraints(lbl2, 0, i);
                    lbl2.setText(labels.getString("vraag_naam"));
                    NaamScherm.setConstraints(txtInput2, 1, i);
                    break;
                case 2:
                    NaamScherm.setConstraints(lbl3, 0, i);
                    lbl3.setText(labels.getString("vraag_naam"));
                    NaamScherm.setConstraints(txtInput3, 1, i);
                    break;
                case 3:
                    NaamScherm.setConstraints(lbl4, 0, i);
                    lbl4.setText(labels.getString("vraag_naam"));
                    NaamScherm.setConstraints(txtInput4, 1, i);
                    break;
                case 4:
                    NaamScherm.setConstraints(lbl5, 0, i);
                    lbl5.setText(labels.getString("vraag_naam"));
                    NaamScherm.setConstraints(txtInput5, 1, i);
                    break;
                case 5:
                    NaamScherm.setConstraints(lbl6, 0, i);
                    lbl6.setText(labels.getString("vraag_naam"));
                    NaamScherm.setConstraints(txtInput6, 1, i);
                    break;
            }
        }
        doorgaan.setText(labels.getString("doorgaan"));
        doorgaan.setLayoutX(100);
        doorgaan.setLayoutY(100);
        doorgaan.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                hoofdPaneel.namenIngegeven();
            }
        });
        this.getChildren().addAll(lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, txtInput1, txtInput2, txtInput3, txtInput4, txtInput5, txtInput6, doorgaan);
    }

}
